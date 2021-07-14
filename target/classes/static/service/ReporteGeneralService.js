import axios from 'axios'

export default class ReporteGeneralService {

    getCitas(formattedDate, formattedDateFin, user) {
        return axios.get("/api/listarCitas?dini=" +
            formattedDate +
            "&dfin=" +
            formattedDateFin +
            "&txtusuario="+user).then(res => res.data)
    }
}