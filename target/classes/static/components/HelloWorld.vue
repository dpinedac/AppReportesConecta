<template>
  <h1>{{ msg }}</h1>
  <div class="container  text-center mt-5 mb-5">
    <h1 class="mt-5">Bootstrap 5 is working fine with Vuejs!!</h1>
</div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";
export default {
  name: 'HelloWorld',
  data() {
    return {
      msg: []
    }
  },
  mounted() {
    
    fetch("/api/citasusuario/fnunez")
      .then((response) => response.text())
      .then((data) => {
          this.msg = data;
          console.log(this.msg);
      });
  }
}
</script>