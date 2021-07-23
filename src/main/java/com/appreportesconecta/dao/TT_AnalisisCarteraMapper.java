package com.appreportesconecta.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.domain.TT_AnalisisCartera;

@Mapper
public interface TT_AnalisisCarteraMapper {

	List<TT_AnalisisCartera> selectByCitasTel(TT_Citas citas);
}
