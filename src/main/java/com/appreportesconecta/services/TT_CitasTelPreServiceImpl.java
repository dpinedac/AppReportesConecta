package com.appreportesconecta.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.dao.TT_CitasTelPreMapper;
import com.appreportesconecta.domain.TT_CitasTelPre;
import java.util.ArrayList;

@Service("tt_CitasTelPreServiceImpl")
public class TT_CitasTelPreServiceImpl
        implements TT_CitasTelPreService {

    @Autowired
    private TT_CitasTelPreMapper tt_CitasTelPreMapper;

    @Override
    public List<TT_CitasTelPre> listarCitas(String usuario, LocalDate fechaIniGest, LocalDate fechaFinGest) {

        return tt_CitasTelPreMapper.listarCitas(usuario, fechaIniGest, fechaFinGest);
    }

    @Override
    public List<TT_CitasTelPre> listarTodosCitas(LocalDate fechaIniGest, LocalDate fechaFinGest) {

        return tt_CitasTelPreMapper.listarTodosCitas(fechaIniGest, fechaFinGest);
    }

    @Override
    public List<TT_CitasTelPre> selectByCitasTel(String usuario, LocalDate fechaIniGest, LocalDate fechaFinGest) {
        TT_Citas citas = new TT_Citas();
        citas.setFechaFinGest(fechaFinGest);
        citas.setFechaIniGest(fechaIniGest);
        citas.setUsuario(usuario);
        return tt_CitasTelPreMapper.selectByCitasTel(citas);
    }

    @Override
    public List<TT_CitasTelPre> find() {

        List<TT_CitasTelPre> citasTelPres = new ArrayList<>();
         LocalDate localDate = LocalDate.of(2020, 1, 8);
           
        for (int i = 0; i < 40000; i++) {
            String moneda = i % 2 == 0 ? "SOL" : "DOL";
            TT_CitasTelPre citasTelPre = new TT_CitasTelPre();
            citasTelPre.setMONEDADEUDA(moneda);
            citasTelPre.setDOCUMENTO("Doc"+i);
            citasTelPres.add(citasTelPre);
        }

        return citasTelPres;
    }

}
