package com.appreportesconecta.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.dao.TT_AnalisisCarteraMapper;
import com.appreportesconecta.dao.TT_CitasTelPreMapper;
import com.appreportesconecta.domain.TM_Usuario;
import com.appreportesconecta.domain.TT_AnalisisCartera;
import com.appreportesconecta.domain.TT_CitasTelPre;
import java.util.ArrayList;

@Service
public class ReportesServiceImpl implements ReportesService {

	@Autowired
	private TT_CitasTelPreMapper citasTelPreMapper;
	
	@Autowired
	private TT_AnalisisCarteraMapper analisisCarteraMapper;

	@Override
	public List<TT_CitasTelPre> selectByCitasTel(TM_Usuario tm_usuario, LocalDate fechaIniGest, LocalDate fechaFinGest,
			Boolean searchFechaGestion,Boolean searchAll) {

		TT_Citas citas = new TT_Citas();
		citas.setFechaFinGest(fechaFinGest);
		citas.setFechaIniGest(fechaIniGest);
		citas.setUsuario(tm_usuario.isRolGeneral() || searchAll ? "" : tm_usuario.getTM05SUSRNAM());
		citas.setTipoRol(tm_usuario.getTipoRol());
		
		List<TT_CitasTelPre> citasTelPres = new ArrayList<>();
		if (searchFechaGestion) {
			citasTelPres = citasTelPreMapper.selectCitasTel(citas);
		} else {
			citasTelPres = citasTelPreMapper.selectCitasTelByFechaCita(citas);
		}
		return citasTelPres;
	}

	@Override
	public List<TT_AnalisisCartera> find(TM_Usuario tm_usuario, LocalDate fechaIniGest, LocalDate fechaFinGest,
			Boolean searchAll) {
		TT_Citas citas = new TT_Citas();
		citas.setFechaFinGest(fechaFinGest);
		citas.setFechaIniGest(fechaIniGest);
		citas.setUsuario(tm_usuario.isRolGeneral() || tm_usuario.isRolJefatura() ? "" : tm_usuario.getTM05SUSRNAM());
		return analisisCarteraMapper.selectByCitasTel(citas);
	}

	

}
