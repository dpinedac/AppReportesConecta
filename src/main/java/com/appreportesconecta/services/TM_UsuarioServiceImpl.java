package com.appreportesconecta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appreportesconecta.dao.TM_UsuarioMapper;
import com.appreportesconecta.domain.TM_Usuario;


@Service("tm_UsuarioServiceImpl")
public class TM_UsuarioServiceImpl implements TM_UsuarioService {

	@Autowired
	private TM_UsuarioMapper tm_UsuarioMapper;

	@Override
	public TM_Usuario listarUsuario(String usuario) {
		return tm_UsuarioMapper.listarUsuario(usuario);
	}

}
