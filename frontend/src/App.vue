<template>
  <div id="app">
    <nav class="navbar" id="nav-top" role="navigation" aria-label="main navigation">
      <div class="container" id="nav-box">
        <div class="navbar-brand">
          <a class="navbar-item">
            <img src="./assets/logo.png" width="200" height="100">
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
    
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

html {
  background: #F2F2F2;
}
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
