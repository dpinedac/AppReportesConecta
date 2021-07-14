export default class Utils {

    setMap(data, rows, map) {
        var pagina = 0;
        var cantXPage = rows;
        var citases = [];
        var cant = cantXPage;
        data.forEach((tT_CitasTelPre) => {
            if (cant == 0) {
                cant = cantXPage;
                citases = [];
                pagina++;
            }
            citases.push(tT_CitasTelPre);
            map.set(pagina, citases);
            cant--;
        });
        return map;
    }

    findByValue(data, value) {
        value = value.trim().toLowerCase();
        if (value == null || data == undefined || data.length == 0) {
            return data;
        }

        var result = data.filter(x =>
            x.DOCUMENTO.toString().match(value) ||
            x.TM15NDEUOPE.toString().match(value) ||
            x.TM07SCOSDES.toLowerCase().match(value) ||
            x.MONEDADEUDA.toLowerCase().match(value) ||
            x.VSUPERVISOR.toLowerCase().match(value) ||
            x.VGESTOR.toLowerCase().match(value) ||
            x.GESTORGESTION.toLowerCase().match(value) ||
            x.CANAL.toLowerCase().match(value)
        );
        return result;
    }
}