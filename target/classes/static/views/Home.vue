<template>
  <div>
    <div>User {{ $route.params.usu }}</div>
  </div>
</template>

<script>
import Cookies from "js-cookie";
import axios from "axios";
export default {
  mounted() {
    axios.get("/api/citasusuario/" + this.$route.params.usu).then((data) => {
      if (data.data.status) {
        var user = data.data.data.tm05SUSRNAM;
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
</style>