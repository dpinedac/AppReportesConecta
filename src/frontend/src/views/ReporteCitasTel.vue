<template class="container">
  <Toast />
  <Panel>
    <h3>Citas Telefónicas</h3>
  </Panel>

  <Panel>
    <div>
      <h5>{{ textSearch }}</h5>
      <InputSwitch v-model="searchFechaGestion" />
    </div>
    <div v-if="user.rolJefatura">
      <h5>Si desea buscar todos los registros pulsa aquí:</h5>
      <InputSwitch v-model="searchAll" />
    </div>
    <div class="row text-center ml-3 mt-3">
      <div class="col-md-3">
        <Calendar
          id="icon"
          v-model="DaysSelect"
          :minDate="minDate"
          :maxDate="maxDate"
          :showIcon="true"
          selectionMode="range"
          :manualInput="false"
          dateFormat="dd/mm"
          panelClass="p-calendar"
          :placeholder="
            searchFechaGestion
              ? 'Seleccione Fecha Cita'
              : 'Seleccione Fecha Gestión'
          "
          class="col-md-12"
        />
      </div>

      <div class="col-auto">
        <Button label="Enviar" @click="getCitas()" />
      </div>
    </div>

    <div class="card mt-3">
      <DataTable
        :value="citas"
        dataKey="id"
        :loading="loading"
        filterDisplay="menu"
        :paginator="true"
        @page="onPage($event)"
        :rows="5"
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown "
        :rowsPerPageOptions="[5, 10, 20, 50]"
        responsiveLayout="scroll"
        currentPageReportTemplate=" Del {first} al {last} de {totalRecords}"
        stripedRows
        ref="dt"
        :totalRecords="totalRecords"
        :lazy="true"
        scrollHeight="600px"
        class="p-datatable-sm"
        rowHover
        :autoLayout="true"
      >
        <template #header>
          <div class="row g-3">
            <div class="col-auto align-self-start">
              <Button
                icon="pi pi-external-link"
                label="Export excel"
                @click="exportExcel"
              />
            </div>
            <div class="col-auto">
              <Button
                type="button"
                icon="pi pi-filter-slash"
                label="Clear"
                class="p-button-outlined"
                @click="clearFilter1()"
              />
            </div>
            <div class="col-auto flex-right">
              <span class="p-input-icon-left">
                <i class="pi pi-search" />
                <InputText
                  v-model="search"
                  placeholder="Buscar"
                  @change="onChange()"
                />
              </span>
            </div>
            <div v-if="inproccess">
              <h5>En proceso</h5>
              <ProgressBar :value="proccess" />
            </div>
          </div>
        </template>

        <template #empty> No existen datos </template>
        <template #loading> Cargando información. Por favor espere. </template>
        <!-- DOCUMENTO -->
        <Column field="DOCUMENTO" header="Documento" frozen> </Column>
        <Column field="TM15NDEUOPE" header="Operacion"></Column>
        <Column field="TM07SCOSDES" header="Cosecha"></Column>
        <Column field="MONEDADEUDA" header="Moneda"></Column>
        <Column field="TM15NDEUCAPINI" header="Capital Inicial"></Column>
        <Column
          field="TM15NDEUCAPINI_SOL"
          header="Capital Inicial Soles"
        ></Column>
        <Column field="TG01SGENDES_REACT" header="Reacción"></Column>
        <Column field="TG01SGENDES_CONT" header="Contacto"></Column>
        <Column field="TG01SGENDES_TIPCONT_ABR" header="Tipo Contacto"></Column>
        <Column field="CANAL" header="Canal"></Column>
        <Column field="VUSUJEFE" header="Jefe de Canal"></Column>
        <Column field="VSUPERVISOR" header="Supervisor"></Column>
        <Column field="VGESTOR" header="Gestor"></Column>
        <Column field="NIVEL" header="Nivel"></Column>
        <Column field="GESTORGESTION" header="Gestor Gestión"></Column>
        <Column field="TT01SGESOBS" header="Observación">
          <template #body="slotProps">
            <i
              class="pi pi-info-circle"
              style="fontsize: 2rem"
              v-tooltip.top="slotProps.data.TT01SGESOBS"
            ></i>
          </template>
        </Column>
        <Column field="TM09SDTCVAL_TEL" header="Teléfono"></Column>
        <Column field="NOMCAMPANA" header="Campaña"></Column>
        <Column field="TT01DGESFEC" header="Fecha Gestión"></Column>
        <Column field="TT01DGESCIT" header="Fecha Cita"></Column>
        <Column field="CALIFICACION" header="Calificación"></Column>
        <Column field="TT03NMONTOTOTPAGO_MES" header="Monto Pago Mes"></Column>
        <Column
          field="TG01SGENDES_ULT_SIT_NEG"
          header="Ult. situación de Negociación"
        ></Column>
        <Column field="INTENSIDAD" header="Intensidad"></Column>
        <Column field="FRECUENCIA" header="Frecuencia"></Column>
        <Column field="ACCIONRESULCIT" header="Resultado Acción Cita"></Column>
        <Column
          field="REACCIONRESULCIT"
          header="Resultado Reacción Cita"
        ></Column>
        <Column
          field="CONTACTORESULTCIT"
          header="Resultado Contacto Cita"
        ></Column>
        <Column
          field="OBSERVACIONRESULCIT"
          header="Resultado Observación Cita"
        ></Column>
        <Column
          field="TELEFONORESULCIT"
          header="Resultado Telefono Cita"
        ></Column>
        <template #paginatorLeft>
          <Button type="button" icon="pi pi-refresh" class="p-button-text" />
        </template>
        <template #paginatorRight>
          <Button type="button" icon="pi pi-cloud" class="p-button-text" />
        </template>
      </DataTable>
    </div>
  </Panel>
</template>

<script>
import Calendar from "primevue/calendar";
import DataTable from "primevue/datatable";
import ReporteGeneralService from "../service/ReporteGeneralService";
import Tooltip from "primevue/tooltip";
import moment from "moment";
import { PrimeIcons } from "primevue/api";
import InputSwitch from "primevue/inputswitch";
import ProgressBar from "primevue/progressbar";
import { getCurrentInstance } from "vue";
export default {
  components: {
    Calendar,
    DataTable,
    InputSwitch,
    ProgressBar,
  },
  directives: {
    tooltip: Tooltip,
    primeIcons: PrimeIcons,
  },
  data() {
    return {
      DaysSelect: [],
      minDate: null,
      maxDate: null,
      loading: false,
      citas: null,
      search: null,
      totalRecords: 0,
      formattedDate: null,
      formattedDateFin: null,
      searchFechaGestion: false,
      searchAll: false,
      totalFilter: 0,
      user: {},
      textSearch: "",
      inproccess: false,
      proccess: 0,
      interval: null,
    };
  },
  ReporteGeneralService: null,
  created() {
    let today = new Date();
    this.minDate = new Date(today.getFullYear(), today.getMonth() - 1, 1);
    this.maxDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
    this.ReporteGeneralService = new ReporteGeneralService();

    const app = getCurrentInstance();
    this.user = app.appContext.config.globalProperties.$userSession;
    this.textSearch =
      this.user.rolJefatura || this.user.rolGeneral
        ? "Si desea buscar por Fecha de Cita pulsa aquí:"
        : "Si desea buscar por Fecha de Gestión pulsa aquí:";
  },
  mounted() {
    this.lazyParams = {
      page: 0,
      rows: this.$refs.dt.rows,
    };
    this.getCitas();
  },
  methods: {
    getCitas() {
      let $vue = this;
      let today = new Date();
      var date1 = new Date(today.getFullYear(), today.getMonth(), 1);
      var date2 = today;
      $vue.inproccess = true;
      this.startProgress();
      if ($vue.DaysSelect.length != 0) {
        date1 = $vue.DaysSelect[0];
        date2 = $vue.DaysSelect[1];
      } else {
        $vue.DaysSelect[0] = date1;
        $vue.DaysSelect[1] = date2;
      }

      if (date1 == undefined || date2 == undefined) {
        return;
      }

      $vue.loading = true;
      this.formattedDate = moment(date1).format("YYYY-MM-DD");
      this.formattedDateFin = moment(date2).format("YYYY-MM-DD");
      var searchType =
        this.user.rolJefatura || this.user.rolGeneral
          ? !this.searchFechaGestion
          : this.searchFechaGestion;
      this.ReporteGeneralService.getCitas(
        this.formattedDate,
        this.formattedDateFin,
        searchType,
        this.searchAll,
        $vue.lazyParams
      ).then((res) => {
        var data = res.data;
        this.citas = data.data;
        this.totalRecords = data.totalRecords;
        this.loading = false;
        this.endProgress();
        $vue.inproccess = false;
      });
    },
    getCitasLazy() {
      let $vue = this;
      this.loading = true;
      this.ReporteGeneralService.getCitasLazy($vue.lazyParams).then((res) => {
        var data = res.data;
        this.citas = data.data;
        this.totalRecords =
          data.totalFilter == null ? data.totalRecords : data.totalFilter;
        this.loading = false;
      });
    },
    clearFilter1() {
      this.initFilters1();
      this.getCitasLazy();
    },
    initFilters1() {
      this.search = null;
      this.lazyParams.search = null;
    },
    onPage(event) {
      this.lazyParams = event;
      this.lazyParams.search = this.search;
      this.getCitasLazy();
    },
    onChange() {
      this.lazyParams.search = this.search;
      this.getCitasLazy();
    },
    exportExcel() {
      this.ReporteGeneralService.downloadCitas(this.lazyParams).then(
        (response) => {
          console.log(response);
          let disposition = response.headers["content-disposition"]; // Obtener contenido-disposición
          let fileInfo = disposition
            ? disposition.substr(disposition.indexOf("filename"))
            : "";
          let fileName = fileInfo ? fileInfo.split("=")[1] : "";
          if (!fileName.substr(fileName.indexOf("."))) {
            this.$notify({
              título: "Prompt",
              mensaje: "No hay datos que desee",
              type: "error",
            });
            return;
          }
          // Procesando archivos de flujo binario
          var blob = new Blob([response.data], {
            type: "application/x-download;charset=utf-8",
          });
          var downloadElement = document.createElement("a");
          var href = window.URL.createObjectURL(blob); // Crear enlace de descarga
          downloadElement.href = href;
          downloadElement.download = decodeURIComponent(
            fileName.replace("_", "")
          );
          document.body.appendChild(downloadElement);
          downloadElement.click(); // haga clic para descargar
          document.body.removeChild(downloadElement); // Elimina el elemento después de descargar
          window.URL.revokeObjectURL(href);
        }
      );
    },
    startProgress() {
      this.interval = setInterval(() => {
        let newValue = 0
        if (this.proccess >= 50) {
           newValue = this.proccess + 1;
        } else {
           newValue = this.proccess + Math.floor(Math.random() * 10) + 1;
          if (newValue >= 100) {
            newValue = 100;
          }
        }

        this.proccess = newValue;
      }, 2000);
    },
    endProgress() {
      clearInterval(this.interval);
      this.interval = null;
      this.proccess = 0
    },
  },
};
</script>
