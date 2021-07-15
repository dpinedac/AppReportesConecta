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
        <Chip :label="user" image="avatar.png" />
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
export default {
  name: "MenuComponent",
  components: { TabMenu, Sidebar, Chip },
  data() {
    return {
      visibleLeft: false,
      user: "",
      items: [
        {
          label: "Reporte Citas Tel",
          icon: "pi pi-fw pi-home ",
          to: "/reporteCitasTel",
        },
      ],
    };
  },
  mounted() {
    console.log(this.userSession);
     axios.get("/api/findSession").then((response) => {
      if (response.data.status) {
        var user = response.data.data.tm05SUSRNAM;
        this.userSession = user;
        console.log(this.userSession);
        this.$router.push("/reporteCitasTel");
      } else {
        this.$router.push("/error");
      }
    });
  },
  methods: {
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