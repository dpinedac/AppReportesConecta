package com.appreportesconecta.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appreportesconecta.domain.TM_Usuario;
import com.appreportesconecta.domain.security.DataSession;
import com.appreportesconecta.services.TM_UsuarioService;

@RestController
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@Autowired
	@Qualifier("tm_UsuarioServiceImpl")
	private TM_UsuarioService tm_UsuarioService;

	@RequestMapping(value = "/citasusuario/{usu}", method = { RequestMethod.GET })
	public ModelAndView  citasUsuarioView(@PathVariable(name = "usu", required = true) String usuario, HttpSession session,
			HttpServletResponse response) throws IOException {
		
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(usuario);
		if (tm_usuario != null) {
			DataSession ds = new DataSession();
			ds.setTM04SROLDES(tm_usuario.getTM04SROLDES());
			ds.setTM05SUSRNAM(tm_usuario.getTM05SUSRNAM());
			session.setAttribute("USER", ds);
		}else {
			session.setAttribute("USER", null);
		}
		response.sendRedirect("/");
		return null ;
	}

}
