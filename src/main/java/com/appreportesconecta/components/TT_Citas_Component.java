package com.appreportesconecta.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.appreportesconecta.bean.DataTableLazy;
import com.appreportesconecta.domain.TT_CitasTelPre;

import lombok.Data;

@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class TT_Citas_Component {

	List<TT_CitasTelPre> citasTelPre;
	List<TT_CitasTelPre> citasTelPreFilter;
	Map<Integer, List<TT_CitasTelPre>> map = new HashMap();
	Map<Integer, List<TT_CitasTelPre>> mapFilter = new HashMap();
	int size = -1;
	int sizeFilter = -1;
	int rows;

	public TT_Citas_Component() {

	}

	public synchronized List<TT_CitasTelPre> findCitasPage(DataTableLazy dataTableLazy) {
		if (map.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_CitasTelPre> result = map.get(dataTableLazy.getPage());

		return result;
	}

	public synchronized List<TT_CitasTelPre> findCitasFilterPage(DataTableLazy dataTableLazy) {
		if (mapFilter.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_CitasTelPre> result = mapFilter.get(dataTableLazy.getPage());

		return result;
	}

	public synchronized void setCitasTelPorPagina(List<TT_CitasTelPre> citasTelPre, DataTableLazy dataTableLazy) {
		this.citasTelPre = citasTelPre;
		this.size = citasTelPre.size();
		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_CitasTelPre> citasTel = new ArrayList();
		int cant = this.rows;

		for (TT_CitasTelPre citas : citasTelPre) {
			if (cant == 0) {
				citasTel = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			citasTel.add(citas);

			this.map.put(pagina, citasTel);
			cant--;
		}

	}

	public synchronized void setCitasTelPorRows(DataTableLazy dataTableLazy) {

		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_CitasTelPre> citasTel = new ArrayList();
		int cant = this.rows;

		for (TT_CitasTelPre citas : this.citasTelPre) {
			if (cant == 0) {
				citasTel = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			citasTel.add(citas);

			this.map.put(pagina, citasTel);
			cant--;
		}

	}

	public synchronized void setCitasTelFilterPorPagina(List<TT_CitasTelPre> citasTelPre, DataTableLazy dataTableLazy) {
		this.citasTelPreFilter = citasTelPre;
		this.sizeFilter = citasTelPre.size();
		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_CitasTelPre> citasTel = new ArrayList();
		int cant = this.rows;

		for (TT_CitasTelPre citas : citasTelPre) {
			if (cant == 0) {
				citasTel = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			citasTel.add(citas);

			this.mapFilter.put(pagina, citasTel);
			cant--;
		}

	}

	public synchronized int getSize() {

		return this.size;
	}

	public synchronized void setMap(HashMap<Integer, List<TT_CitasTelPre>> Map) {
		this.map = Map;
	}

	public synchronized void reset() {
		this.citasTelPre = new ArrayList<>();
		this.map = new HashMap();
		this.size = -1;
	}

	public synchronized List<TT_CitasTelPre> find(String filter) {
		String value = filter.toLowerCase();
		return this.citasTelPre.stream().filter(x -> x.getDOCUMENTO().toString().toLowerCase().startsWith(value)
				|| x.getTM15NDEUOPE().toString().toLowerCase().startsWith(value) || x.getTM07SCOSDES().toString().toLowerCase().startsWith(value)
				|| x.getMONEDADEUDA().toString().toLowerCase().startsWith(value) || x.getVSUPERVISOR().toString().toLowerCase().startsWith(value) || x.getVUSUJEFE().toString().toLowerCase().startsWith(value)
				|| x.getVGESTOR().toString().toLowerCase().startsWith(value) || x.getGESTORGESTION().toString().toLowerCase().startsWith(value)
				|| x.getCANAL().toString().toLowerCase().startsWith(value) || x.getTT01DGESCIT().toString().toLowerCase().startsWith(value)
				|| (x.getTG01SGENDES_ULT_SIT_NEG() == null ? false
						: x.getTG01SGENDES_ULT_SIT_NEG().toString().toLowerCase().startsWith(value))
				|| x.getINTENSIDAD().toString().toLowerCase().startsWith(value) || x.getFRECUENCIA().toString().toLowerCase().startsWith(value)
				|| (x.getACCIONRESULCIT() == null ? false : x.getACCIONRESULCIT().toString().toLowerCase().startsWith(value))
				|| (x.getREACCIONRESULCIT() == null ? false : x.getREACCIONRESULCIT().toString().toLowerCase().startsWith(value))
				|| (x.getCONTACTORESULCIT() == null ? false : x.getCONTACTORESULCIT().toString().toLowerCase().startsWith(value))
				|| (x.getTELEFONORESULCIT() == null ? false : x.getTELEFONORESULCIT().toString().toLowerCase().startsWith(value)))
				.collect(Collectors.toList());
	}

	public List<TT_CitasTelPre> findCitasDownload(DataTableLazy dataTableLazy) {
		if (map.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_CitasTelPre> result = this.citasTelPre;
		if (dataTableLazy.getSearch() != null && !dataTableLazy.getSearch().isEmpty()) {
			result = this.find(dataTableLazy.getSearch());
		}
		return result;
	}
}
