<template class="container">
  <Panel>
    <h3>Reporte Mensual</h3>
  </Panel>

  <Panel>
    <div class="row text-center ml-3">
      <div class="col-md-3">
        <Calendar
          id="icon"
          v-model="date3"
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
        <Button label="Submit" @click="getCitas()" />
      </div>
    </div>

    <div class="mt-5 mb-5">
      <DataTable
        :value="citas"
        dataKey="id"
        :loading="loading"
        filterDisplay="row"
        :paginator="true"
        v-model:filters="filters"
        :rows="10"
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
        :rowsPerPageOptions="[10, 20, 50]"
        responsiveLayout="scroll"
        currentPageReportTemplate="Showing {first} to {last} of {totalRecords}"
        :globalFilterFields="[
          'DOCUMENTO',
          'TM15NDEUOPE',
          'GESTORGESTION',
          'VGESTOR',
        ]"
        sortMode="multiple"
        stripedRows
        removableSort
        ref="dt"
      >
        <template #header>
          <div class="row g-3">
            <div class="col-auto align-self-start">
              <Button
                icon="pi pi-external-link"
                label="CSV"
                @click="exportCSV($event)"
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
                  v-model="
                    filters[
                      ('global')
                    ].value
                  "
                  placeholder="Buscar"
                />
              </span>
            </div>
          </div>
        </template>
        <template #empty> No existen datos </template>
        <template #loading> Cargando información. Por favor espere. </template>
        <!-- DOCUMENTO -->
        <Column field="DOCUMENTO" header="Documento" :sortable="true"></Column>
        <Column
          field="TM15NDEUOPE"
          header="Operacion"
          :sortable="true"
        ></Column>
        <Column field="TM07SCOSDES" header="Cosecha" :sortable="true"></Column>
        <Column field="MONEDADEUDA" header="Cosecha" :sortable="true"></Column>
        <Column
          field="TM15NDEUCAPINI"
          header="Saldo Capital"
          :sortable="true"
        ></Column>
        <Column field="NOMCAMPANA" header="Campaña" :sortable="true"></Column>
        <Column
          field="TG01SGENDES_REACT"
          header="Reacción"
          :sortable="true"
        ></Column>
        <Column
          field="TG01SGENDES_CONT"
          header="Tipo Contacto"
          :sortable="true"
        ></Column>
        <Column
          field="VSUPERVISOR"
          header="Supervisor"
          :sortable="true"
        ></Column>
        <Column field="VGESTOR" header="Gestor" :sortable="true"></Column>
        <Column field="NIVEL" header="Nivel" :sortable="true"></Column>
        <Column
          field="GESTORGESTION"
          header="Usuario Gestión"
          :sortable="true"
        ></Column>
        <Column
          field="TM09SDTCVAL_TEL"
          header="Teléfono"
          :sortable="true"
        ></Column>
        <Column
          field="TT01DGESFEC"
          header="Fecha Gestión"
          :sortable="true"
        ></Column>
        <Column
          field="TT01DGESCIT"
          header="Fecha Cita"
          :sortable="true"
        ></Column>
        <Column
          field="CALIFICACION"
          header="Calificación"
          :sortable="true"
        ></Column>
        <Column
          field="TT03NMONTOTOTPAGO_MES"
          header="Pago del Mes"
          :sortable="true"
        ></Column>
        <Column
          field="TG01SGENDES_SIT_NEG"
          header="Situación de Ult. Negociación"
          :sortable="true"
        ></Column>
        <Column
          field="INTENSIDAD"
          header="Intensidad"
          :sortable="true"
        ></Column>
        <Column
          field="FRECUENCIA"
          header="Frecuencia"
          :sortable="true"
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
import { FilterMatchMode, FilterOperator } from "primevue/api";
import axios from "axios";
import moment from "moment";
export default {
  components: {
    Calendar,
    DataTable,
  },
  data() {
    return {
      date3: null,
      minDate: null,
      maxDate: null,
      loading: false,
      citas: null,
      filters: null,
    };
  },
  created() {
    let today = new Date();
    this.minDate = new Date(today.getFullYear(), today.getMonth(), 1);
    this.maxDate = new Date(today.getFullYear(), today.getMonth() + 1, 0);

    this.initFilters1();
  },
  mounted() {
  },
  methods: {
    getCitas() {
      let $vue = this;
      $vue.loading = true;
      var date1 = $vue.date3 != null ? $vue.date3[0] : $vue.minDate;
      var date2 = $vue.date3 != null ? $vue.date3[1] : $vue.maxDate;
      var formattedDate = moment(date1).format("YYYY-MM-DD");
      var formattedDateFin = moment(date2).format("YYYY-MM-DD");
      axios
        .get(
          "/api/listarCitas?dini=" +
            formattedDate +
            "&dfin=" +
            formattedDateFin +
            "&txtusuario=fnunez"
        )
        .then((data) => {
          this.citas = data.data.data;
          this.loading = false;
        });
    },
    clearFilter1() {
      this.initFilters1();
    },
    initFilters1() {
      this.filters = {
        'global': {value: null, matchMode: FilterMatchMode.CONTAINS},
        'DOCUMENTO':{operator: FilterOperator.AND, constraints: [{value: null, matchMode: FilterMatchMode.EQUALS}]},
        'TM15NDEUOPE': { value: null, matchMode: FilterMatchMode.CONTAINS },
        'GESTORGESTION': { value: null, matchMode: FilterMatchMode.CONTAINS },
        'VGESTOR': { value: null, matchMode: FilterMatchMode.CONTAINS },
      };
    },
    exportCSV() {
      this.$refs.dt.exportCSV();
    },
  },
};
</script>

<style>
</style>