package com.appreportesconecta.controller;

import com.appreportesconecta.bean.JsonResponse;
import com.appreportesconecta.components.TT_Citas_Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;
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
import com.appreportesconecta.domain.security.DataSession;
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

	@ResponseBody
	@RequestMapping(value = "/listarCitas", method = RequestMethod.GET, produces = "application/json")
	public JsonResponse listarCitas(@RequestParam(name = "dini", required = false, defaultValue = "") String fechini,
			@RequestParam(name = "dfin", required = false, defaultValue = "") String fechfin, HttpSession session) {
		List<TT_CitasTelPre> listCitas = null;// TT_CitasTelPre
		DataSession dataSession = (DataSession) session.getAttribute("USER");
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(dataSession.getTM05SUSRNAM());
		JsonResponse response = new JsonResponse();
		if (!fechini.equals("") && !fechfin.equals("")) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
			String dateini = fechini;
			String datefin = fechfin;
			LOG.info("Entre {} {} " + fechini + fechfin);
			LocalDate localDateIni = LocalDate.parse(dateini, formatter);
			LocalDate localDateFin = LocalDate.parse(datefin, formatter);
			LOG.info("Entre {} {} " + localDateIni + localDateFin);
			if (tm_usuario.getTM04SROLDES().equals("ReporteCallJefatura")) {
				listCitas = tt_CitasTelPreService.selectByCitasTel("", localDateIni, localDateFin);
			} else {
				listCitas = tt_CitasTelPreService.selectByCitasTel(tm_usuario.getTM05SUSRNAM(), localDateIni,
						localDateFin);
			}
			LOG.info("SALIIIII {} {} {}" + localDateIni + localDateFin + listCitas.size());
		}
		response.setData(listCitas);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/findSession", method = RequestMethod.GET, produces = "application/json")
	public JsonResponse findSession(HttpSession session) {
		JsonResponse response = new JsonResponse();
		DataSession dataSession = (DataSession) session.getAttribute("USER");
		if (dataSession == null) {
			return response;
		}
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(dataSession.getTM05SUSRNAM());
		response.setData(tm_usuario);
		response.setStatus(true);
		return response;
	}

}
