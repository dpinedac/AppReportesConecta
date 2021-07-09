package com.appreportesconecta.components;

import com.appreportesconecta.bean.TT_Citas;
import com.appreportesconecta.domain.TT_CitasTelPre;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TT_Citas_Component {

    List<TT_CitasTelPre> citasTelPre;
    List<TT_Citas> citasPre;
    Map<Integer, List<TT_CitasTelPre>> map = new HashMap();
    Map<Integer, List<TT_Citas>> mapByPage = new HashMap();

    public TT_Citas_Component() {

    }

    public List<TT_Citas> findCitasPage(Integer page) {

        return mapByPage.get(page);
    }

    public void setCitasTelPorPagina(List<TT_CitasTelPre> citasTelPre, Integer cantXPage) { // cantXPage = Cantidad por paginas
        this.citasTelPre = citasTelPre;
        int pagina = 1;

        List<TT_CitasTelPre> citases = new ArrayList();
        int cant = cantXPage;
       
        for (TT_CitasTelPre tT_CitasTelPre : citasTelPre) {
            if (cant == 0) {
                cant = cantXPage;
                citases = new ArrayList();
                cant = cantXPage;
                pagina++;
            }
            citases.add(tT_CitasTelPre);

            this.map.put(pagina, citases);
            cant--;
        }

    }

    public void setCitasPorPagina(List<TT_Citas> citasPre, Integer cantXPage) {// cantXPage = Cantidad por paginas
        this.citasPre = citasPre;
        int pagina = 1;

        List<TT_Citas> citases = new ArrayList();
        int cant = cantXPage;
        for (TT_Citas tT_CitasTelPre : citasPre) {
            if (cant == 0) {
                cant = cantXPage;
                citases = new ArrayList();
                cant = cantXPage;
                pagina++;
            }
            citases.add(tT_CitasTelPre);

            this.mapByPage.put(pagina, citases);
            cant--;
        }
    }

    public void setMap(HashMap<Integer, List<TT_CitasTelPre>> Map) {
        this.map = Map;
    }

    public void setMapByPage(HashMap<Integer, List<TT_Citas>> MapByPage) {
        this.mapByPage = MapByPage;
    }

}
