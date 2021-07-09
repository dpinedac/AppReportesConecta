package com.appreportesconecta.services;

import com.appreportesconecta.bean.TT_Citas;
import java.time.LocalDate;
import java.util.List;
import com.appreportesconecta.domain.TT_CitasTelPre;

public interface TT_CitasTelPreService {

    List<TT_CitasTelPre> listarCitas(String usuario, LocalDate fechaIniGest, LocalDate fechaFinGest);

    List<TT_CitasTelPre> listarTodosCitas(LocalDate fechaIniGest, LocalDate fechaFinGest);

    List<TT_CitasTelPre> selectByCitasTel(String usuario, LocalDate fechaIniGest, LocalDate fechaFinGest);

    List<TT_CitasTelPre> find();
}
