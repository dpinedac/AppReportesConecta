(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["reporteMensual"],{edc2:function(e,t,a){"use strict";a.r(t);var l=a("7a23"),n=Object(l["j"])("h3",null,"Reporte Mensual",-1),i={class:"row text-center ml-3"},o={class:"col-md-3"},r={class:"col-auto"},c={class:"mt-5 mb-5"},s={class:"row g-3"},d={class:"col-auto align-self-start"},u={class:"col-auto"},b={class:"col-auto flex-right"},j={class:"p-input-icon-left"},f=Object(l["j"])("i",{class:"pi pi-search"},null,-1),O=Object(l["i"])(" No existen datos "),h=Object(l["i"])(" Cargando información. Por favor espere. ");function p(e,t,a,p,m,g){var C=Object(l["z"])("Panel"),T=Object(l["z"])("Calendar"),D=Object(l["z"])("Button"),E=Object(l["z"])("InputText"),N=Object(l["z"])("Column"),S=Object(l["z"])("DataTable");return Object(l["t"])(),Object(l["f"])(l["a"],null,[Object(l["j"])(C,null,{default:Object(l["I"])((function(){return[n]})),_:1}),Object(l["j"])(C,null,{default:Object(l["I"])((function(){return[Object(l["j"])("div",i,[Object(l["j"])("div",o,[Object(l["j"])(T,{id:"icon",modelValue:m.date3,"onUpdate:modelValue":t[1]||(t[1]=function(e){return m.date3=e}),minDate:m.minDate,maxDate:m.maxDate,showIcon:!0,selectionMode:"range",manualInput:!1,dateFormat:"dd/mm",panelClass:"p-calendar",placeholder:"Elige un rango de fecha",class:"col-md-12"},null,8,["modelValue","minDate","maxDate"])]),Object(l["j"])("div",r,[Object(l["j"])(D,{label:"Submit",onClick:t[2]||(t[2]=function(e){return g.getCitas()})})])]),Object(l["j"])("div",c,[Object(l["j"])(S,{value:m.citas,dataKey:"id",loading:m.loading,filterDisplay:"row",paginator:!0,filters:m.filters,"onUpdate:filters":t[6]||(t[6]=function(e){return m.filters=e}),rows:10,paginatorTemplate:"CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown",rowsPerPageOptions:[10,20,50],responsiveLayout:"scroll",currentPageReportTemplate:"Showing {first} to {last} of {totalRecords}",globalFilterFields:["usuario","moneda","fechaIniGest","fechaFinGest"],sortMode:"multiple",stripedRows:"",removableSort:"",ref:"dt"},{header:Object(l["I"])((function(){return[Object(l["j"])("div",s,[Object(l["j"])("div",d,[Object(l["j"])(D,{icon:"pi pi-external-link",label:"CSV",onClick:t[3]||(t[3]=function(e){return g.exportCSV(e)})})]),Object(l["j"])("div",u,[Object(l["j"])(D,{type:"button",icon:"pi pi-filter-slash",label:"Clear",class:"p-button-outlined",onClick:t[4]||(t[4]=function(e){return g.clearFilter1()})})]),Object(l["j"])("div",b,[Object(l["j"])("span",j,[f,Object(l["j"])(E,{modelValue:m.filters["moneda"].value,"onUpdate:modelValue":t[5]||(t[5]=function(e){return m.filters["moneda"].value=e}),placeholder:"Buscar"},null,8,["modelValue"])])])])]})),empty:Object(l["I"])((function(){return[O]})),loading:Object(l["I"])((function(){return[h]})),paginatorLeft:Object(l["I"])((function(){return[Object(l["j"])(D,{type:"button",icon:"pi pi-refresh",class:"p-button-text"})]})),paginatorRight:Object(l["I"])((function(){return[Object(l["j"])(D,{type:"button",icon:"pi pi-cloud",class:"p-button-text"})]})),default:Object(l["I"])((function(){return[Object(l["j"])(N,{field:"usuario",header:"Documento",sortable:!0}),Object(l["j"])(N,{field:"TM15NDEUOPE",header:"Operacion",sortable:!0}),Object(l["j"])(N,{field:"TM07SCOSDES",header:"Cosecha",sortable:!0}),Object(l["j"])(N,{field:"MONEDADEUDA",header:"Cosecha",sortable:!0}),Object(l["j"])(N,{field:"TM15NDEUCAPINI",header:"Saldo Capital",sortable:!0}),Object(l["j"])(N,{field:"NOMCAMPANA",header:"Campaña",sortable:!0}),Object(l["j"])(N,{field:"TG01SGENDES_REACT",header:"Reacción",sortable:!0}),Object(l["j"])(N,{field:"TG01SGENDES_CONT",header:"Tipo Contacto",sortable:!0}),Object(l["j"])(N,{field:"VSUPERVISOR",header:"Supervisor",sortable:!0}),Object(l["j"])(N,{field:"VGESTOR",header:"Gestor",sortable:!0}),Object(l["j"])(N,{field:"NIVEL",header:"Nivel",sortable:!0}),Object(l["j"])(N,{field:"GESTORGESTION",header:"Usuario Gestión",sortable:!0}),Object(l["j"])(N,{field:"TM09SDTCVAL_TEL",header:"Teléfono",sortable:!0}),Object(l["j"])(N,{field:"TT01DGESFEC",header:"Fecha Gestión",sortable:!0}),Object(l["j"])(N,{field:"TT01DGESCIT",header:"Fecha Cita",sortable:!0}),Object(l["j"])(N,{field:"CALIFICACION",header:"Calificación",sortable:!0}),Object(l["j"])(N,{field:"TT03NMONTOTOTPAGO_MES",header:"Pago del Mes",sortable:!0}),Object(l["j"])(N,{field:"TG01SGENDES_SIT_NEG",header:"Situación de Ult. Negociación",sortable:!0}),Object(l["j"])(N,{field:"INTENSIDAD",header:"Intensidad",sortable:!0}),Object(l["j"])(N,{field:"FRECUENCIA",header:"Frecuencia",sortable:!0})]})),_:1},8,["value","loading","filters"])])]})),_:1})],64)}var m=a("cb8b"),g=a("5b2c"),C=a("0393"),T=a("bc3a"),D=a.n(T),E=a("c1df"),N=a.n(E),S={components:{Calendar:m["a"],DataTable:g["a"]},data:function(){return{date3:null,minDate:null,maxDate:null,loading:!0,citas:null,filters:{usuario:{value:null,matchMode:C["a"].CONTAINS},moneda:{value:null,matchMode:C["a"].CONTAINS},fechaFinGest:{value:null,matchMode:C["a"].BETWEEN},fechaIniGest:{value:null,matchMode:C["a"].BETWEEN}}}},created:function(){var e=new Date;this.minDate=new Date(e.getFullYear(),e.getMonth(),1),this.maxDate=new Date(e.getFullYear(),e.getMonth()+1,0),console.log(this.minDate),console.log(this.maxDate)},mounted:function(){this.loading=!0,this.getCitas()},methods:{getCitas:function(){var e=this,t=this;t.loading=!0,console.log(t.date3);var a=null!=t.date3?t.date3[0]:t.minDate,l=null!=t.date3?t.date3[1]:t.maxDate,n=N()(a).format("YYYY-MM-DD"),i=N()(l).format("YYYY-MM-DD");console.log(n),D.a.get("/api/listarCitas?dini="+n+"&dfin="+i+"&txtusuario=fnunez").then((function(t){e.citas=t.data.data,e.loading=!1}))},clearFilter1:function(){this.initFilters1()},initFilters1:function(){this.filters={usuario:{value:null,matchMode:C["a"].CONTAINS},moneda:{value:null,matchMode:C["a"].CONTAINS},fechaFinGest:{value:null,matchMode:C["a"].BETWEEN},fechaIniGest:{value:null,matchMode:C["a"].BETWEEN}}},exportCSV:function(){this.$refs.dt.exportCSV()}}};S.render=p;t["default"]=S}}]);
//# sourceMappingURL=reporteMensual.c139cce2.js.map