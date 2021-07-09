<template>
  <MenuComponent />
  <router-view />
</template>
<script>
import MenuComponent from "@/components/_layout/Menu.vue";
export default {
  name: "Home",
  components: {
    MenuComponent,
  },
};
</script>
<style>

</style>
