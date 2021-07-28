import axios from 'axios'

export default class ReporteGeneralService {

    getCitasLazy(dataTableLazy) {
        return axios.post("/api/listarCitas", dataTableLazy).then(res => res)
    }

    getCitas(formattedDate, formattedDateFin, searchFechaCita,searchAll, dataTableLazy) {

        return axios.post("/api/asyncListarCitas/" + formattedDate + "/" + formattedDateFin + "/" + searchFechaCita+"/"+searchAll,
            dataTableLazy).then(res =>
                res)
    }

    downloadCitas(dataTableLazy) {
        return axios.post("/api/downloadCitas", dataTableLazy,{responseType:'blob'}).then(res => res)
    }
}