<template >
  <header class="sticky-top bg-white flex-md-nowrap p-2 shadow">
    <div class="row align-items-center">
      <div class="pt-3 pb-3 col-md-1">
        <a class="menu-button" @click="visibleLeft = true"
          ><i class="pi pi-bars p-3"></i
        ></a>
      </div>
      <div class="pt-3 pb-3 col-md-4">
        <img src="/logo.svg" class="img-fluid" />
      </div>
      <div class="pt-3 pb-3 pl-3 col-md-7 text-end">
        <Chip :label="user.tm05SUSRNAM" image="avatar.png" />
      </div>
    </div>
  </header>
  <Sidebar v-model:visible="visibleLeft"> <TabMenu :model="items" /> </Sidebar
  ><!--  -->
</template>

<script>
import TabMenu from "primevue/tabmenu";
import Sidebar from "primevue/sidebar";
import Chip from "primevue/chip";
import axios from "axios";

import { getCurrentInstance } from "vue";
export default {
  name: "MenuComponent",
  components: { TabMenu, Sidebar, Chip },
  data() {
    return {
      visibleLeft: false,
      user:{},
      items: [
        {
          label: "Reporte Citas Tel",
          icon: "pi pi-fw pi-home ",
          to: "/reporteCitasTel",
        },
        /* {
          label: "Reporte análisis",
          icon: "pi pi-fw pi-home ",
          to: "/reporteAnalisisCartera",
        }, */
      ],
    };
  },
  mounted() {
    this.findUser();
  },
  methods: {
    findUser() {
      const app =  getCurrentInstance();
      axios.get("/api/findSession").then((response) => {
        if (response.data.status) {
          app.appContext.config.globalProperties.$userSession = response.data.data;
          this.user = app.appContext.config.globalProperties.$userSession;
          console.log(app.appContext.config.globalProperties.$userSession);
          var URLactual = window.location;
          var url = URLactual.pathname;
          if (url == "/") {
            this.$router.push("/reporteCitasTel");
          } else {
            this.$router.push(url);
          }
        } else {
          this.$router.push("/error");
        }
      });
    },
    expandAll() {
      for (let node of this.items) {
        this.expandNode(node);
      }

      this.expandedKeys = {
        ...this.expandedKeys,
      };
    },
    collapseAll() {
      this.expandedKeys = {};
    },
    expandNode(node) {
      if (node.items && node.items.length) {
        this.expandedKeys[node.key] = true;

        for (let child of node.items) {
          this.expandNode(child);
        }
      }
    },
  },
};
</script>
<style >
</style>