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
import com.appreportesconecta.domain.TT_AnalisisCartera;

import lombok.Data;

@Data
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class TT_AnalisCartera_Component {

	List<TT_AnalisisCartera> analisisCartera;
	List<TT_AnalisisCartera> analisisCarteraFilter;
	Map<Integer, List<TT_AnalisisCartera>> map = new HashMap();
	Map<Integer, List<TT_AnalisisCartera>> mapFilter = new HashMap();
	int size = -1;
	int sizeFilter = -1;
	int rows;

	public synchronized List<TT_AnalisisCartera> findAnalisisPage(DataTableLazy dataTableLazy) {
		if (map.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_AnalisisCartera> result = map.get(dataTableLazy.getPage());

		return result;
	}

	public synchronized List<TT_AnalisisCartera> findCitasFilterPage(DataTableLazy dataTableLazy) {
		if (mapFilter.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_AnalisisCartera> result = mapFilter.get(dataTableLazy.getPage());

		return result;
	}

	public synchronized void setAnalisisCarteraPorPagina(List<TT_AnalisisCartera> analisisCartera, DataTableLazy dataTableLazy) {
		this.analisisCartera = analisisCartera;
		this.size = analisisCartera.size();
		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_AnalisisCartera> carteraAnalisis = new ArrayList();
		int cant = this.rows;

		for (TT_AnalisisCartera tt_carteraAnalisis : analisisCartera) {
			if (cant == 0) {
				carteraAnalisis = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			carteraAnalisis.add(tt_carteraAnalisis);

			this.map.put(pagina, carteraAnalisis);
			cant--;
		}

	}

	public synchronized void setCitasTelPorRows(DataTableLazy dataTableLazy) {

		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_AnalisisCartera> carteraAnalisis = new ArrayList();
		int cant = this.rows;

		for (TT_AnalisisCartera tt_carteraAnalisis : this.analisisCartera) {
			if (cant == 0) {
				carteraAnalisis = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			carteraAnalisis.add(tt_carteraAnalisis);

			this.map.put(pagina, carteraAnalisis);
			cant--;
		}

	}

	public synchronized void setCitasTelFilterPorPagina(List<TT_AnalisisCartera> analisisCartera, DataTableLazy dataTableLazy) {
		this.analisisCarteraFilter = analisisCartera;
		this.sizeFilter = analisisCartera.size();
		this.rows = dataTableLazy.getRows();
		int pagina = 0;

		List<TT_AnalisisCartera> carteraAnalisis = new ArrayList();
		int cant = this.rows;

		for (TT_AnalisisCartera tt_carteraAnalisis : analisisCartera) {
			if (cant == 0) {
				carteraAnalisis = new ArrayList();
				cant = this.rows;
				pagina++;
			}
			carteraAnalisis.add(tt_carteraAnalisis);

			this.mapFilter.put(pagina, carteraAnalisis);
			cant--;
		}

	}

	public synchronized int getSize() {

		return this.size;
	}

	public synchronized void setMap(HashMap<Integer, List<TT_AnalisisCartera>> Map) {
		this.map = Map;
	}
	public synchronized void reset() {
		this.analisisCartera = new ArrayList<>();
		this.map = new HashMap();
		this.size = -1;
	}

	public synchronized List<TT_AnalisisCartera> find( String value) {

		return this.analisisCartera.stream().filter(x -> x.getDOCUMENTO().toString().startsWith(value)
				|| x.getVOPERACION().toString().startsWith(value) || x.getVUSUJEFE().toString().startsWith(value)
				|| x.getVCANALACT().toString().startsWith(value) || x.getVSUPERVISOR().toString().startsWith(value)
				|| x.getVGESTOR().toString().startsWith(value) || x.getNIVEL().toString().startsWith(value)
				|| x.getANOMES().toString().startsWith(value) || x.getTM07SCOSDES().toString().startsWith(value)
				|| x.getTM01SENTABV().toString().startsWith(value) || x.getDEPARTAMENTO().toString().startsWith(value)
				|| x.getPROVINCIA().toString().startsWith(value) || x.getDISTRITO().toString().startsWith(value)
				|| x.getTG01SGENDES_ACC() == null ? true : x.getTG01SGENDES_ACC().toString().startsWith(value)
				||  x.getTG01SGENDES_REAC() == null ? true : x.getTG01SGENDES_REAC().toString().startsWith(value)
				|| x.getTG01SGENDES_CONT() == null ? true : x.getTG01SGENDES_CONT().toString().startsWith(value)
				|| x.getTG01SGENDES_TIPCONT_ABR()== null ? true :  x.getTG01SGENDES_TIPCONT_ABR().toString().startsWith(value)
				|| x.getTM05SUSRNAM_GEST() == null ? true : x.getTM05SUSRNAM_GEST().toString().startsWith(value)
				|| x.getCALIFICACION()  == null ? true : x.getCALIFICACION().toString().startsWith(value) ||
						x.getCOBERTURA().toString().startsWith(value))
				.collect(Collectors.toList());
	}

	public List<TT_AnalisisCartera> findCitasDownload(DataTableLazy dataTableLazy) {
		if (map.get(dataTableLazy.getPage()) == null) {
			return new ArrayList<>();
		}
		List<TT_AnalisisCartera> result = this.analisisCartera;
		if (dataTableLazy.getSearch() != null && !dataTableLazy.getSearch().isEmpty()) {
			result = this.find( dataTableLazy.getSearch());
		}
		return result;
	}
}
