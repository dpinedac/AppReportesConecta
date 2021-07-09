<template >
 
    <header
      class="navbar navbar-white sticky-top bg-white flex-md-nowrap p-2 shadow"
    >
      <div class="pt-3 pb-3">
        <a class="menu-button" @click="visibleLeft = true"
          ><i class="pi pi-bars p-3"></i
        ></a>
        <img src="/logo.svg" />
      </div>
    </header>
    <Sidebar v-model:visible="visibleLeft">
      <TabMenu :model="items" />
    </Sidebar><!--  -->
</template>

<script>
import TabMenu from "primevue/tabmenu";
import Sidebar from "primevue/sidebar"
export default {
  name: "MenuComponent",
  components: { TabMenu,Sidebar },
  data() {
    return {
      visibleLeft: false,
      items: [
        { label: "Reporte General", icon: "pi pi-fw pi-home ", to: "/reporteGeneral" },
        {
          label: "Reporte Mensual",
          icon: "pi pi-fw pi-calendar",
          to: "/reporteMensual",
        },
        { label: "Grafico Bar", icon: "pi pi-fw pi-pencil", to: "/graficoBar" }
      ],
    };
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