<template>
  <MenuComponent />
  <router-view />
</template>
<script>
import MenuComponent from "@/components/_layout/Menu.vue";
import Cookies from "js-cookie";
import axios from "axios";
export default {
  components: {
    MenuComponent,
  },
  created() {
    Cookies.remove("user");
    axios.get("/api/findSession").then((response) => {
      if (response.data.status) {
        var user = response.data.data.tm05SUSRNAM;
        Cookies.set("user", user);
        this.$router.push("/reporteCitasTel");
      } else {
        this.$router.push("/error");
      }
    });
  },
};
</script>
<style>
html {
  font-size: 12px;
}
</style>
