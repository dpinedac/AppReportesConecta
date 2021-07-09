package com.appreportesconecta.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.appreportesconecta.domain.TM_Usuario;


@Mapper
public interface TM_UsuarioMapper {

	TM_Usuario listarUsuario(@Param("usuario") String usuario);
}
