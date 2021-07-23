<template class="container">
  <Toast />
  <Panel>
    <h3>Reporte Análisis Cartera</h3>
  </Panel>

  <Panel>
    <div>
      <h5>Buscar todos</h5>
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
          placeholder="Ingrese Fecha Gestión"
          class="col-md-12"
        />
      </div>

      <div class="col-auto">
        <Button label="Enviar" @click="getAnalisis()" />
      </div>
    </div>

    <div class="card mt-3">
      <DataTable
        :value="analisis"
        dataKey="id"
        :loading="loading"
        filterDisplay="menu"
        :paginator="true"
        @page="onPage($event)"
        :rows="5"
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown "
        :rowsPerPageOptions="[5, 10, 20, 50]"
        responsiveLayout="scroll"
        currentPageReportTemplate="Del {first} al {last} de {totalRecords}"
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
          </div>
        </template>

        <template #empty> No existen datos </template>
        <template #loading> Cargando información. Por favor espere. </template>
        <!-- DOCUMENTO -->
        <Column field="DOCUMENTO" header="Documento"></Column>
        <Column field="VOPERACION" header="Nro de operación"></Column>
        <Column field="TM08NCLIID" header="Codigo de Cliente"></Column>
        <Column field="VUSUJEFE" header="Jefe de Canal"></Column>
        <Column field="VCANALACT" header="Canal"></Column>
        <Column field="VSUPERVISOR" header="Supervisor"></Column>
        <Column field="VGESTOR" header="Gestor"></Column>
        <Column field="NIVEL" header="Nivel"></Column>
        <Column field="ANOMES" header="AñoMes"></Column>
        <Column field="TM07SCOSDES" header="Cosecha"></Column>
        <Column field="TM01SENTABV" header="Entidad"></Column>
        <Column field="TM07DCOSVTA" header="Fecha de Cosecha"></Column>
        <Column field="NOMBRES" header="Nombres"></Column>
        <Column field="DIRECCION" header="Dirección"></Column>
        <Column field="DEPARTAMENTO" header="Departamento"></Column>
        <Column field="PROVINCIA" header="Provincia"></Column>
        <Column field="DISTRITO" header="Distrito"></Column>
        <Column field="MONEDA_DEUDA" header="Moneda de Deuda"></Column>
        <Column field="TM15NDEUCAPINI" header="Capital Inicial"></Column>
        <Column
          field="TM15NDEUCAPINI_SOL"
          header="Capital Inicial Soles "
        ></Column>
        <Column field="DEUCAPACT" header="Capital Actual"></Column>
        <Column field="DEUCAPACTSOL" header="Capital Actual Soles"></Column>
        <Column field="TG01SGENDES_ACC" header="Acción"></Column>
        <Column field="TG01SGENDES_REAC" header="Reacción"></Column>
        <Column field="TG01SGENDES_CONT" header="Contacto"></Column>
        <Column
          field="TG01SGENDES_TIPCONT_ABR"
          header="Tipo de Contacto"
        ></Column>
        <Column field="TT01DGESFEC" header="Fecha de Gestión"></Column>
        <Column field="TM05SUSRNAM_GEST" header="Usuario Gestión"></Column>
        <Column field="CALIFICACION" header="Calificación"></Column>
        <Column field="TOTPAGDEU_MES" header="Pagos del Mes"></Column>
        <Column field="INTENSIDAD" header="Intensidad"></Column>
        <Column field="FRECUENCIA" header="Frecuencia"></Column>
        <Column field="COBERTURA" header="Cobertura"></Column>
        <Column
          field="FECHACREACION_NEG"
          header="Fecha de Creación Negociacion"
        ></Column>
        <Column field="MONTO_NEG" header="Monto de Negociación"></Column>
        <Column
          field="SITUACION_NEG"
          header="Situación de Negociación"
        ></Column>
        <Column field="GESTOR_NEG" header="Gestor de negociación"></Column>
        <Column
          field="FECHVENCI_NEG"
          header="Fecha de vencimiento de Negociacion"
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
import ReporteAnalisisService from "../service/ReporteAnalisisService";
import Tooltip from "primevue/tooltip";
import moment from "moment";
import { PrimeIcons } from "primevue/api";
import InputSwitch from "primevue/inputswitch";
export default {
  components: {
    Calendar,
    DataTable,
    InputSwitch,
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
      analisis: null,
      search: null,
      mapByPage: new Map(),
      totalRecords: 0,
      formattedDate: null,
      formattedDateFin: null,
      searchAll: false,
      totalFilter: 0,
    };
  },
  ReporteAnalisisService: null,
  created() {
    let today = new Date();
    this.minDate = new Date(today.getFullYear(), today.getMonth() - 1, 1);
    this.maxDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
    this.ReporteAnalisisService = new ReporteAnalisisService();
  },
  mounted() {
    this.lazyParams = {
      page: 0,
      rows: this.$refs.dt.rows,
    };
    this.getAnalisis();
  },
  methods: {
    getAnalisis() {
      let $vue = this;

      let today = new Date();
      var date1 = new Date();
      var date2 = today;
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

      this.ReporteAnalisisService.getAnalisis(
        this.formattedDate,
        this.formattedDateFin,
        this.searchAll,
        this.lazyParams
      ).then((res) => {
        var data = res.data;
        this.analisis = data.data;
        this.totalRecords = data.totalRecords;
        this.loading = false;
      });
    },
    getAnalisisLazy() {
      let $vue = this;
       this.loading = true;
      this.ReporteAnalisisService.getAnalisisLazy($vue.lazyParams).then(
        (res) => {
          var data = res.data;
          this.analisis = data.data;
          this.totalRecords =
            data.totalFilter == null ? data.totalRecords : data.totalFilter;
          this.loading = false;
        }
      );
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
      this.getAnalisisLazy();
    },
    onChange() {
      this.lazyParams.search = this.search;
      this.getAnalisisLazy();
    },
    exportExcel() {
      this.ReporteAnalisisService.downloadAnalisis(this.lazyParams).then(
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
    }
  },
};
</script>
