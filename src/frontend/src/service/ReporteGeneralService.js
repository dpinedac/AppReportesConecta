import axios from 'axios'


export default class ReporteGeneralService {

    getCitas(formattedDate, formattedDateFin) {
        return  axios.get("/api/listarCitas?dini=" +
            formattedDate +
            "&dfin=" +
            formattedDateFin ).then(res => res.data)
    }

   async getCitasAsync(formattedDate, formattedDateFin) {
        return  axios.get("/api/asyncListarCitas?dini=" +
            formattedDate +
            "&dfin=" +
            formattedDateFin ).then(res => res.data)
    }
}