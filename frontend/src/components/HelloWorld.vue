<template>
  <div class="hello">
    <nav class="navbar" id="nav-top" role="navigation" aria-label="main navigation">
      <div class="container" id="nav-box">
        <div class="navbar-brand">
          <a class="navbar-item">
            <img src="./../assets/logo.png" width="200" height="100">
          </a>
        </div>
        <div id="navbarBasicExample" class="navbar-menu">
          <div class="navbar-start">
            <a class="navbar-item">
              Home
            </a>
          </div>

          <div class="navbar-end">
            <div class="navbar-item">
              <div class="buttons">
                <a class="button is-primary">
                  <strong>Sign up</strong>
                </a>
                <a class="button is-light">
                  Log in
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </nav>
    <div class="container" id="home-box">
      <h1>{{ msg }}</h1>
      <div class="columns">
        <div class="column">
          <div class="card">
            <div class="card-image is-4by3">
              <img src="./../assets/logo.png" alt="Placeholder image">
            </div>
            <div class="card-content">
              product name
            </div>
            <div class="card-footer">
              <router-link to="/ProductDetail"><div class="button">Buy now</div></router-link>
            </div>
          </div>
        </div>
         <div class="column">
          <div class="card">
            <div class="card-image is-4by3">
              <img src="./../assets/logo.png" alt="Placeholder image">
            </div>
            <div class="card-content">
              product name
            </div>
            <div class="card-footer">
              <router-link to="/ProductDetail"><div class="button">Buy now</div></router-link>
            </div>
          </div>
        </div>
         <div class="column">
          <div class="card">
            <div class="card-image is-4by3">
              <img src="./../assets/logo.png" alt="Placeholder image">
            </div>
            <div class="card-content">
              product name
            </div>
            <div class="card-footer">
              <router-link to="/ProductDetail"><div class="button">Buy now</div></router-link>
            </div>
          </div>
        </div>
         <div class="column">
          <div class="card">
            <div class="card-image is-4by3">
              <img src="./../assets/logo.png" alt="Placeholder image">
            </div>
            <div class="card-content">
              product name
            </div>
            <div class="card-footer">
              <router-link to="/ProductDetail"><div class="button">Buy now</div></router-link>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'หน้าแรก สำหรับแสดงสินค้า'
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
