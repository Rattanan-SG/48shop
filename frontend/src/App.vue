<template>
  <div id="app">
    <nav class="navbar" id="nav-top" role="navigation" aria-label="main navigation">
      <div class="container" id="nav-box">
            <router-link to="/home">
              <img src="./assets/meepoohlogo.svg" width="220" height="100">
            </router-link>
        <div id="navbarBasicExample" class="navbar-menu">  
          <div class="navbar-start">
            <a class="navbar-item">
                <router-link to="/home">
                  <button class="is-primary"> Home </button>
                </router-link>
            </a>
          </div>
          <div class="navbar-end">
            
              <div class="buttons" id="nav-bot">
                <a class="button is-primary">
                  <strong>Sign up</strong>
                </a>
                <a class="button is-light" @click="login">
                  {{status}}
                </a>
              </div>
            
          </div>
        </div>
      </div>
    </nav>
      <div class="field is-grouped" id="CategoryBar">
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=เสื้อผ้าและแฟชั่น">
                เสื้อผ้าและแฟชั่น
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=สุขภาพและความงาม">
                สุขภาพและความงาม
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=อุปกรณ์ทำครัว">
                อุปกรณ์ทำครัว
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=บ้านและไลฟ์สไตล์">
                บ้านและไลฟ์สไตล์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=เครื่องใช้ไฟฟ้าในบ้าน">
                เครื่องใช้ไฟฟ้าในบ้าน
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=อุปกรณ์อิเล็กทรอนิกส์">
                อุปกรณ์อิเล็กทรอนิกส์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=อุปกรณ์กีฬา">
                อุปกรณ์กีฬา
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered" style=" margin-bottom:18px;">
              <router-link to="/category?category_name=อุปกรณ์ยานยนต์">
                อุปกรณ์ยานยนต์
              </router-link>
            </p>
          </nav>
      </div>
    <router-view/>
  </div>
  
</template>

<script>
import axios from 'axios';

var test = 'sss';
const url_login = `http://localhost:8080/login`;
const url_info = `http://localhost:8080/fbinfo`;
const config = {
    headers: {'Access-Control-Allow-Origin': '*',
              'Access-Control-Allow-Methods': '*',
              'Access-Control-Allow-Headers': '*'}
};
window.fbAsyncInit = function() {
      FB.init({
        appId      : '253765508571750',
        cookie     : true,
        xfbml      : true,
        version    : 'v3.2'
      });
        
      FB.AppEvents.logPageView();   
        
    };

    function checkLoginState(){
      FB.getLoginStatus((response)=>{
        statusChangCallback(response)
      });
    }

    (function(d, s, id){
      var js, fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) {return;}
      js = d.createElement(s); js.id = id;
      js.src = "https://connect.facebook.net/en_US/sdk.js";
      fjs.parentNode.insertBefore(js, fjs);
    }(document, 'script', 'facebook-jssdk'));

export default {

  name: 'App',
  data () {
    return {
      user: {},
      status : ''
    }
  },
  beforeMount() { // ทำก่อน render
    const userData = JSON.parse(localStorage.getItem('user'))
    if (userData) {
      this.user = userData
      this.status = 'logout'
    }
    else{
      this.status = 'login'
    }
  },
  methods: {
    login: function(){
      FB.getLoginStatus((response)=>{
        if(response.status == 'connected'){
          FB.logout((response)=>{
            localStorage.clear();
            this.status = 'login';
          });
        }
        else{
          FB.login((response)=>{
            FB.api('/me?fields=id,first_name,last_name,picture{url}',(userData)=>{
              localStorage.setItem("user", JSON.stringify(userData));
              this.status = 'logout';
            });
          });
        }
      });
    }
  }
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
  height: 120px;
}
#nav-bot{
  margin-bottom: -30px;
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
