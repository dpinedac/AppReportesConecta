import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { library } from "@fortawesome/fontawesome-svg-core"
import { faPhone } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome"
import PrimeVue from "primevue/config"
import Button from "primevue/button"
import InputText from "primevue/inputtext"
import Column from "primevue/column";
import Panel from 'primevue/panel';
import Card from 'primevue/card';
import OrganizationChart from 'primevue/organizationchart';
import "primevue/resources/themes/saga-blue/theme.css"       //theme
import "primevue/resources/primevue.min.css"                 //core css
import "primeicons/primeicons.css"                           //icons
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

library.add(faPhone);

createApp(App).use(router).use(router).use(router).use(router).use(PrimeVue)
.component("Button",Button).component("InputText",InputText)
.component("Column",Column)
.component("Panel",Panel)
.component("Card",Card)
.component("OrganizationChart", OrganizationChart)
.component("font-awesome-icon", FontAwesomeIcon).mount('#app')
