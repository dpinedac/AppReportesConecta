import axios from 'axios'


export default class ReporteGeneralService {

    getCitas(formattedDate, formattedDateFin) {
        return axios.get("/api/listarCitas?dini=" +
            formattedDate +
            "&dfin=" +
            formattedDateFin ).then(res => res.data)
    }
}