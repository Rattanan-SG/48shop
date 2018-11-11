<template>
  <div class="ProductDetail">
    <div class="container" id="home-box">
      <h1>{{ msg }}</h1>
    </div>
  </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';

export default {
  name: 'ProductDetail',
  data () {
    return {
      msg: 'This is Detail page'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
nav {
  border-bottom: 10px;
}

#nav-top {
  border-bottom: 10px;
  border-color: #E0E0E0;
}

#nav-box {
  width: 1000px;
}

#home-box {
  background: white;
  width: 1000px;
  min-height: 2000px;
  margin-top: 13px;
  box-shadow: 2px 10px grey;
}
</style>
