import axios from 'axios'

export default class ReporteAnalisisService {

    getAnalisisLazy(dataTableLazy) {
        return axios.post("/api/listarAnalisis",dataTableLazy).then(res => res)
    }

    getAnalisis(formattedDate, formattedDateFin, searchAll,dataTableLazy) {

        return axios.post("/api/asyncListarAnalisis/" +
        formattedDate + "/" + formattedDateFin + "/" + searchAll,
        dataTableLazy).then(res =>
                res)
    }

    downloadAnalisis(dataTableLazy) {
        return axios.post("/api/downloadAnalisis", dataTableLazy,{responseType:'blob'}).then(res => res)
    }
}