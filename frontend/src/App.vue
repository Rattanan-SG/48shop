<template>
  <div id="app">
    <nav class="navbar" id="nav-top" role="navigation" aria-label="main navigation">
      <div class="container" id="nav-box">
        <div class="navbar-brand">
          <a class="navbar-item">
            <router-link to="/Home">
              <img src="./assets/logo.png" width="200" height="100">
            </router-link>
          </a>
        </div>
        <div id="navbarBasicExample" class="navbar-menu">  
          <div class="navbar-start">
            <a class="navbar-item">
                <router-link to="/Home">
                  Home
                </router-link>
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
      <div class="field is-grouped" id="CategoryBar">
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                เสื้อผ้าและแฟชั่น
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                สุขภาพและความงาม
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                อุปกรณ์ทำครัว
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                บ้านและไลฟ์สไตล์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                เครื่องใช้ไฟฟ้าในบ้าน
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                อุปกรณ์อิเล็กทรอนิกส์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/CategoryProduct">
                อุปกรณ์กีฬา
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered" style=" margin-bottom:18px;">
              <router-link to="/CategoryProduct">
                อุปกรณ์ยานยนต์
              </router-link>
            </p>
          </nav>
      </div>
    
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
  color: #2c3e50;
}

html {
  background: #F2F2F2;
}

#nav-box {
  width: 1000px;
}
#nav-top{
  height: 90px;
}

#home-box {
  background: white;
  width: 1000px;
  min-height: 2000px;
  margin-top: 13px;
  box-shadow: 2px 10px grey;
}
/* Category */
#bar{
  font-size: 14px;
  margin-left:30px;
  margin-top: 20px;
  background:white;
  
}
#CategoryBar{
  background:white;
  justify-content: center;
  border: 0.09em solid #E0E0E0;
  height: 40px;
}




</style>
