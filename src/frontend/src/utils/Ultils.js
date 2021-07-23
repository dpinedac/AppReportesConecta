export default class Utils {



    findAnalisisByValue(data, value) {
        value = value.trim().toLowerCase();
        if (value == null || data == undefined || data.length == 0) {
            return data;
        }

        var result = data.filter(x =>
            x.DOCUMENTO.toString().match(value) ||
            x.VOPERACION.toString().match(value) ||
            x.VUSUJEFE.toString().match(value) ||
            x.VCANALACT.toString().match(value) ||
            x.VSUPERVISOR.toString().match(value) ||
            x.VGESTOR.toString().match(value) ||
            x.NIVEL.toString().match(value) ||
            x.ANOMES.toString().match(value) ||
            x.TM07SCOSDES.toString().match(value) ||
            x.TM01SENTABV.toString().match(value) ||
            x.DEPARTAMENTO.toString().match(value) ||
            x.PROVINCIA.toString().match(value) ||
            x.DISTRITO.toString().match(value) ||
            x.TG01SGENDES_ACC.toString().match(value) ||
            x.TG01SGENDES_REAC.toString().match(value) ||
            x.TG01SGENDES_CONT.toString().match(value) ||
            x.TG01SGENDES_TIPCONT_ABR.toString().match(value) ||
            x.TM05SUSRNAM_GEST.toString().match(value) ||
            x.CALIFICACION.toString().match(value) ||
            x.COBERTURA.toString().match(value)            
        );
        return result;
    }
}