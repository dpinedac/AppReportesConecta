package com.appreportesconecta.services;

import java.time.LocalDate;
import java.util.List;

import com.appreportesconecta.domain.TM_Usuario;
import com.appreportesconecta.domain.TT_AnalisisCartera;
import com.appreportesconecta.domain.TT_CitasTelPre;

public interface ReportesService {

	List<TT_CitasTelPre> selectByCitasTel(TM_Usuario tm_usuario, LocalDate fechaIniGest, LocalDate fechaFinGest,
			Boolean searchFechaGestion);

	List<TT_AnalisisCartera> find(TM_Usuario tm_usuario, LocalDate localDateIni, LocalDate localDateFin,
			Boolean searchAll);

}
