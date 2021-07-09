package com.appreportesconecta.dao;

import java.time.LocalDate;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.domain.TT_CitasTelPre;


@Mapper
public interface TT_CitasTelPreMapper {

	List<TT_CitasTelPre> listarCitas(@Param("usuario") String usuario,@Param("fechaIniGest") LocalDate fechaIniGest,@Param("fechaFinGest") LocalDate fechaFinGest);
	List<TT_CitasTelPre> listarTodosCitas(@Param("fechaIniGest") LocalDate fechaIniGest,@Param("fechaFinGest") LocalDate fechaFinGest);
	List<TT_CitasTelPre> selectByCitasTel(TT_Citas citas);
}
