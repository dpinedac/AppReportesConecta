package com.appreportesconecta.controller;

import com.appreportesconecta.bean.JsonResponse;
import com.appreportesconecta.components.TT_Citas_Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.appreportesconecta.domain.TM_Usuario;
import com.appreportesconecta.domain.TT_CitasTelPre;
import com.appreportesconecta.services.TM_UsuarioService;
import com.appreportesconecta.services.TT_CitasTelPreService;

@RestController
@RequestMapping(value = "/api")
public class ReportesController {

	private static final Log LOG = LogFactory.getLog(ReportesController.class);

	@Autowired
	@Qualifier("tt_CitasTelPreServiceImpl")
	private TT_CitasTelPreService tt_CitasTelPreService;

	@Autowired
	@Qualifier("tm_UsuarioServiceImpl")
	private TM_UsuarioService tm_UsuarioService;

	@Autowired
	private TT_Citas_Component tt_citas_component;

	@GetMapping("/")
	public String principalTemplate() {
		return "citas";
	}

	@RequestMapping(value = "/lockview", method = RequestMethod.GET)
	public String lockscreen() {
		return "lockscreen";
	}

	@GetMapping(value = "/citas")
	public ModelAndView citasView() {
		ModelAndView mavcitas = new ModelAndView("citas");
		return mavcitas;
	}

	@GetMapping(value = "/citasusuario/{usu}")
	public JsonResponse citasUsuarioView(@PathVariable(name = "usu", required = true) String usuario) {

		JsonResponse response = new JsonResponse();
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(usuario);
		if (tm_usuario != null) {
			tm_usuario.setTM05SUSRNAM(tm_usuario.getTM05SUSRNAM());
			tm_usuario.setTM04SROLDES(tm_usuario.getTM04SROLDES());
		}
		LOG.info("Entre ");
		response.setData(tm_usuario);
		return response;
	}

	@GetMapping(value = "/obtenercitasfrm")
	public String listarReporte() {
		return "citas";
	}

	@ResponseBody
	@RequestMapping(value = "/listarCitas", method = RequestMethod.GET, produces = "application/json")
	public JsonResponse listarCitas(@RequestParam(name = "dini", required = false, defaultValue = "") String fechini,
			@RequestParam(name = "dfin", required = false, defaultValue = "") String fechfin,
			@RequestParam(name = "txtusuario") String usuario) {
		List<TT_CitasTelPre> listCitas = null;// TT_CitasTelPre

		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(usuario);
		JsonResponse response = new JsonResponse();
		if (!fechini.equals("") && !fechfin.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			String dateini = fechini;
			String datefin = fechfin;
			LOG.info("Entre {} {} " + fechini + fechfin);
			LocalDate localDateIni = LocalDate.parse(dateini, formatter);
			LocalDate localDateFin = LocalDate.parse(datefin, formatter);
			LOG.info("Entre {} {} " + localDateIni + localDateFin);
			if (tm_usuario.getTM04SROLDES().equals("Gestor (GES)")) {
				listCitas = tt_CitasTelPreService.selectByCitasTel(tm_usuario.getTM05SUSRNAM(), localDateIni,
						localDateFin);
			} else {
				listCitas = tt_CitasTelPreService.selectByCitasTel("", localDateIni, localDateFin);
			}
			LOG.info("SALIIIII {} {} " + localDateIni + localDateFin);
		}
		response.setData(listCitas);
		return response;
	}

}
