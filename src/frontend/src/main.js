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
/* library.add(faPhone); */
var app = createApp(App)

app.config.globalProperties.userSession = '';
app.use(router).use(router).use(PrimeVue)
    .component("Button", Button).component("InputText", InputText)
    .component("Column", Column)
    .component("Panel", Panel)
    .component("Card", Card)
    .component("OrganizationChart", OrganizationChart).mount('#app')
