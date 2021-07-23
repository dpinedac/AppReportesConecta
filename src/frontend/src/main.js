import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import "primeicons/primeicons.css"                           //icons
import PrimeVue from "primevue/config"
import Button from "primevue/button"
import InputText from "primevue/inputtext"
import Column from "primevue/column";
import Panel from 'primevue/panel';
import Card from 'primevue/card';
import OrganizationChart from 'primevue/organizationchart';
import "primevue/resources/themes/saga-purple/theme.css"       //theme
import "primevue/resources/primevue.min.css"                 //core css
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
import ToastService from 'primevue/toastservice';
import Toast from 'primevue/toast';
/* library.add(faPhone); */
var app = createApp(App)


app.config.globalProperties.$userSession = {};
app.use(router).use(router).use(PrimeVue).use(ToastService)
    .component("Button", Button).component("InputText", InputText)
    .component("Column", Column)
    .component("Panel", Panel)
    .component("Card", Card)
    .component("Toast", Toast)
    .component("OrganizationChart", OrganizationChart).mount('#app')
