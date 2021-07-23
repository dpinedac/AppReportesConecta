package com.appreportesconecta.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.domain.TT_CitasTelPre;


@Mapper
public interface TT_CitasTelPreMapper {

	List<TT_CitasTelPre> selectByCitasTel(TT_Citas citas);
	List<TT_CitasTelPre> selectByCitasTelByFechaCita(TT_Citas citas);
}
