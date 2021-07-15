<template>
  <MenuComponent />
  <router-view />
</template>
<script>
import MenuComponent from "@/components/_layout/Menu.vue";

export default {
  components: {
    MenuComponent,
  },
  mounted() {
  },
};
</script>
<style>
html {
  font-size: 12px;
}
</style>
