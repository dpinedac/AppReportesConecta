<template>
  <div>
    <div>User {{ $route.params.usu }}</div>
  </div>
</template>

<script>
export default {
   mounted() {
     fetch("/api/citasusuario/"+this.$route.params.usu)
      .then((response) => response.text())
      .then((data) => {
        this.msg = data;
        console.log(this.response);
      });
   }
}
</script>

<style>

</style>