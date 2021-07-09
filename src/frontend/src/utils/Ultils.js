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
        var params = Object.keys(data.get(0));
        var result = data.filter(params[0].match(value)
            || params[1].match(value)
        );
        return result;
    }
}