<template class="container">
  <Panel>
    <h3>Citas Telefónicas</h3>
  </Panel>

  <Panel>
    <div class="row text-center ml-3">
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
          placeholder="Elige un rango de fecha"
          class="col-md-12"
        />
      </div>
      <div class="col-auto">
        <Button label="Enviar" @click="eventCitas()" />
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
import Utils from "../utils/Ultils";
import Tooltip from "primevue/tooltip";
// import axios from "axios";
import moment from "moment";
import { PrimeIcons } from "primevue/api";
export default {
  components: {
    Calendar,
    DataTable,
  },
  directives: {
    tooltip: Tooltip,
    primeIcons: PrimeIcons,
  },
  data() {
    return {
      DaysSelect: null,
      minDate: null,
      maxDate: null,
      loading: false,
      citas: null,
      search: null,
      mapByPage: new Map(),
      totalRecords: 0,
      formattedDate: null,
      formattedDateFin: null,
    };
  },
  ReporteGeneralService: null,
  Utils: null,
  created() {
    let today = new Date();
    this.minDate = new Date(today.getFullYear(), today.getMonth() - 1, 1);
    this.maxDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);
    this.ReporteGeneralService = new ReporteGeneralService();
    this.Utils = new Utils();

    this.initFilters1();
  },
  mounted() {
    this.lazyParams = {
      first: 0,
      rows: this.$refs.dt.rows,
      sortField: null,
      sortOrder: null,
    };
  },
  methods: {
    eventCitas(){
      this.getCitasAsync();
      this.getCitas();
    },
    getCitasAsync() {
      let $vue = this;
      $vue.loading = true;
      var date1 = $vue.DaysSelect != null ? $vue.DaysSelect[0] : $vue.minDate;
      var date2 = $vue.DaysSelect != null ? $vue.DaysSelect[1] : $vue.maxDate;

      if (date1 == undefined || date2 == undefined) {
        return;
      }
      $vue.loading = true;
      this.formattedDate = moment(date1).format("YYYY-MM-DD");
      this.formattedDateFin = moment(date2).format("YYYY-MM-DD");
      setTimeout(() => {
        this.ReporteGeneralService.getCitasAsync(
          this.formattedDate,
          this.formattedDateFin
        ).then((data) => {
          this.allCitas = data.data;
           this.totalRecords = this.allCitas.length;
        });
      }, Math.random() * 1000 + 250);
    },
    getCitas() {
      setTimeout(() => {
        this.ReporteGeneralService.getCitas(
          this.formattedDate,
          this.formattedDateFin
        ).then((data) => {
          this.allCitas = data.data;
          this.Utils.setMap(
            this.allCitas,
            this.lazyParams.rows,
            this.mapByPage
          );
          this.getLazy(0);
          this.totalRecords = this.allCitas.length;
          this.loading = false;
        });
      }, Math.random() * 1000 + 250);
    },
    clearFilter1() {
      this.initFilters1();
    },
    initFilters1() {
      this.search = null;
    },
    exportCSV() {
      this.$refs.dt.exportCSV();
    },
    getLazy(page) {
      this.citas = this.mapByPage.get(page);
    },
    onPage(event) {
      if (this.lazyParams.rows != event.rows) {
        this.Utils.setMap(this.allCitas, event.rows, this.mapByPage);
      }
      this.lazyParams = event;
      this.getLazy(this.lazyParams.page);
    },
    onChange() {
      var result = this.Utils.findByValue(this.allCitas, this.search);
      this.citas = result;
    },
    exportExcel() {
      require.ensure([], () => {
        const {
          export_json_to_excel,
        } = require("@/assets/excel/export2Excel.js");
        const tHeader = [
          "Documento",
          "Operación",
          "Cosecha",
          "Moneda",
          "Capital Inicial",
          "Capital Inicial Soles",
          "Reacción",
          "Contacto",
          "Tipo Contacto",
          "Canal",
          "Supervisor",
          "Gestor",
          "Nivel",
          "Gestion Gestión",
          "Observación",
          "Teléfono",
          "Campaña",
          "Fecha de Gestión",
          "Fecha de Cita",
          "Calificación",
          "Monto Pago Mes",
          "Ultima Situación de Negociación",
          "Intesidad",
          "Frecuencia",
        ];
        // Set the title of the first row of the Excel table above
        const filterVal = [
          "DOCUMENTO",
          "TM15NDEUOPE",
          "TM07SCOSDES",
          "MONEDADEUDA",
          "TM15NDEUCAPINI",
          "TM15NDEUCAPINI_SOL",
          "TG01SGENDES_REACT",
          "TG01SGENDES_CONT",
          "TG01SGENDES_TIPCONT_ABR",
          "CANAL",
          "VSUPERVISOR",
          "VGESTOR",
          "NIVEL",
          "GESTORGESTION",
          "TT01SGESOBS",
          "TM09SDTCVAL_TEL",
          "NOMCAMPANA",
          "TT01DGESFEC",
          "TT01DGESCIT",
          "CALIFICACION",
          "TT03NMONTOTOTPAGO_MES",
          "TG01SGENDES_ULT_SIT_NEG",
          "INTENSIDAD",
          "FRECUENCIA",
        ];
        const list = this.allCitas; //Save tableData in data to list
        if (list == undefined || list.length == 0) {
          return;
        }
        const data = this.formatJson(filterVal, list);
        var day1 = moment(this.minDate).format("DD/MM");
        var day2 = moment(this.maxDate).format("DD/MM");
        export_json_to_excel(
          tHeader,
          data,
          "Reporte Citas Tel " + day1 + " al " + day2
        );
      });
    },
    formatJson(filterVal, jsonData) {
      return jsonData.map((v) => filterVal.map((j) => v[j]));
    },
  },
};
</script>
