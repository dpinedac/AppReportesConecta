package com.appreportesconecta.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.appreportesconecta.bean.DataTableLazy;
import com.appreportesconecta.bean.DataTableLazyResponse;
import com.appreportesconecta.bean.JsonResponse;
import com.appreportesconecta.components.TT_AnalisCartera_Component;
import com.appreportesconecta.components.TT_Citas_Component;
import com.appreportesconecta.domain.TM_Usuario;
import com.appreportesconecta.domain.TT_AnalisisCartera;
import com.appreportesconecta.domain.TT_CitasTelPre;
import com.appreportesconecta.domain.security.DataSession;
import com.appreportesconecta.reportesView.ReporteCitasView;
import com.appreportesconecta.services.ReportesService;
import com.appreportesconecta.services.TM_UsuarioService;

@RestController
@RequestMapping(value = "/api")
public class ReportesController {

	private static final Log LOG = LogFactory.getLog(ReportesController.class);

	@Autowired
	private ReportesService reportesService;

	@Autowired
	@Qualifier("tm_UsuarioServiceImpl")
	private TM_UsuarioService tm_UsuarioService;

	@Autowired
	TT_Citas_Component tt_Citas_Component;

	@Autowired
	TT_AnalisCartera_Component tt_analisis_Component;

	@Autowired
	ReporteCitasView reporteCitasView;

	@ResponseBody
	@RequestMapping(value = "/asyncListarCitas/{dini}/{dfin}/{searchFechaGestion}/{searchAll}", method = RequestMethod.POST, produces = "application/json")
	public DataTableLazyResponse asyncListarCitas(@PathVariable(name = "dini", required = true) String fechini,
			@PathVariable(name = "dfin", required = true) String fechfin,
			@PathVariable(name = "searchFechaGestion", required = true) Boolean searchFechaGestion,
			@PathVariable(name = "searchAll", required = true) Boolean searchAll,
			@RequestBody DataTableLazy dataTableLazy, HttpSession session) {

		DataTableLazyResponse response = new DataTableLazyResponse();

		DataSession dataSession = (DataSession) session.getAttribute("USER");
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(dataSession.getTM05SUSRNAM());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		LocalDate localDateIni = LocalDate.parse(fechini, formatter);
		LocalDate localDateFin = LocalDate.parse(fechfin, formatter);
		
		List<TT_CitasTelPre> listCitas = reportesService.selectByCitasTel(tm_usuario, localDateIni, localDateFin,
				searchFechaGestion, searchAll);
	
		tt_Citas_Component.setCitasTelPorPagina(listCitas, dataTableLazy);
		response.setStatus(true);
		response.setData(tt_Citas_Component.findCitasPage(dataTableLazy));
		response.setTotalRecords(listCitas.size());
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/listarCitas", method = RequestMethod.POST, produces = "application/json")
	public DataTableLazyResponse listarCitas(HttpSession session, @RequestBody DataTableLazy dataTableLazy)
			throws InterruptedException {

		DataTableLazyResponse response = new DataTableLazyResponse();
		List<TT_CitasTelPre> listCitas = new ArrayList<>();
		if (dataTableLazy.getSearch() != null && !dataTableLazy.getSearch().isEmpty()) {
			listCitas = tt_Citas_Component.find(dataTableLazy.getSearch());
			tt_Citas_Component.setCitasTelFilterPorPagina(listCitas, dataTableLazy);
			listCitas = tt_Citas_Component.findCitasFilterPage(dataTableLazy);
			response.setTotalRecords(tt_Citas_Component.getSize());
			response.setTotalFilter(tt_Citas_Component.getSizeFilter());
			response.setData(listCitas);
			response.setStatus(true);
			return response;
		} else if (tt_Citas_Component.getRows() != dataTableLazy.getRows()) {
			tt_Citas_Component.setCitasTelPorRows(dataTableLazy);
		}
		listCitas = tt_Citas_Component.findCitasPage(dataTableLazy);

		Collections.sort(listCitas, (a, b) -> a.getTT01DGESCIT().compareTo(b.getTT01DGESCIT()));
		response.setTotalRecords(tt_Citas_Component.getSize());
		response.setData(listCitas);
		response.setStatus(true);
		return response;
	}

	@RequestMapping(value = "/downloadCitas", method = RequestMethod.POST)
	public ModelAndView downloadCitas(Model model, @RequestBody DataTableLazy dataTableLazy) {
		List<TT_CitasTelPre> listCitas = new ArrayList<>();
		listCitas = tt_Citas_Component.findCitasDownload(dataTableLazy);
		model.addAttribute("citas", listCitas);
		return new ModelAndView(reporteCitasView);
	}

	@ResponseBody
	@RequestMapping(value = "/asyncListarAnalisis/{dini}/{dfin}/{searchAll}", method = RequestMethod.POST, produces = "application/json")
	public DataTableLazyResponse asyncListarAnalisis(@PathVariable(name = "dini", required = true) String fechini,
			@PathVariable(name = "dfin", required = true) String fechfin,
			@PathVariable(name = "searchAll", required = true) Boolean searchAll,
			@RequestBody DataTableLazy dataTableLazy, HttpSession session) {

		DataTableLazyResponse response = new DataTableLazyResponse();

		DataSession dataSession = (DataSession) session.getAttribute("USER");
		TM_Usuario tm_usuario = tm_UsuarioService.listarUsuario(dataSession.getTM05SUSRNAM());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");

		LocalDate localDateIni = LocalDate.parse(fechini, formatter);
		LocalDate localDateFin = LocalDate.parse(fechfin, formatter);
		LOG.info("Entre {} {} " + localDateIni + localDateFin);
		List<TT_AnalisisCartera> listCitas = reportesService.find(tm_usuario, localDateIni, localDateFin, searchAll);

		LOG.info("SALIIIII {} {} {}" + localDateIni + localDateFin + listCitas.size());
		tt_analisis_Component.setAnalisisCarteraPorPagina(listCitas, dataTableLazy);
		response.setStatus(true);
		response.setData(tt_analisis_Component.findAnalisisPage(dataTableLazy));
		response.setTotalRecords(listCitas.size());
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "/listarAnalisis", method = RequestMethod.POST, produces = "application/json")
	public DataTableLazyResponse listarAnalisis(HttpSession session, @RequestBody DataTableLazy dataTableLazy)
			throws InterruptedException {

		DataTableLazyResponse response = new DataTableLazyResponse();
		List<TT_AnalisisCartera> listCitas = new ArrayList<>();
		if (dataTableLazy.getSearch() != null && !dataTableLazy.getSearch().isEmpty()) {
			listCitas = tt_analisis_Component.find(dataTableLazy.getSearch());
			tt_analisis_Component.setCitasTelFilterPorPagina(listCitas, dataTableLazy);
			listCitas = tt_analisis_Component.findCitasFilterPage(dataTableLazy);
			response.setTotalRecords(tt_analisis_Component.getSize());
			response.setTotalFilter(tt_analisis_Component.getSizeFilter());
			response.setData(listCitas);
			response.setStatus(true);
			return response;
		} else if (tt_analisis_Component.getRows() != dataTableLazy.getRows()) {
			tt_analisis_Component.setCitasTelPorRows(dataTableLazy);
		}
		listCitas = tt_analisis_Component.findAnalisisPage(dataTableLazy);

		response.setTotalRecords(tt_analisis_Component.getSize());
		response.setData(listCitas);
		response.setStatus(true);
		return response;
	}

	@RequestMapping(value = "/downloadAnalisis", method = RequestMethod.POST)
	public ModelAndView downloadAnalisis(Model model, @RequestBody DataTableLazy dataTableLazy) {
		List<TT_AnalisisCartera> listCitas = new ArrayList<>();
		listCitas = tt_analisis_Component.findCitasDownload(dataTableLazy);
		model.addAttribute("citas", listCitas);
		return new ModelAndView(reporteCitasView);
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
