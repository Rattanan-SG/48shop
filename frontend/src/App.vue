<template>
  <div id="app">
    <nav class="navbar" id="nav-top" role="navigation" aria-label="main navigation">
      <div class="container" id="nav-box">
        <div class="navbar-brand">
          <a class="navbar-item">
            <router-link to="/home">
              <img src="./assets/logo.png" width="200" height="100">
            </router-link>
          </a>
        </div>
        <div id="navbarBasicExample" class="navbar-menu">  
          <div class="navbar-start">
            <a class="navbar-item">
                <router-link to="/home">
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
                <a class="button is-light" @click="login">    
                  {{status}}
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
              <router-link to="/category">
                เสื้อผ้าและแฟชั่น
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                สุขภาพและความงาม
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                อุปกรณ์ทำครัว
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                บ้านและไลฟ์สไตล์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                เครื่องใช้ไฟฟ้าในบ้าน
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                อุปกรณ์อิเล็กทรอนิกส์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category">
                อุปกรณ์กีฬา
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered" style=" margin-bottom:18px;">
              <router-link to="/category">
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
import facebookLogin from 'facebook-login-vuejs';

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
      firstname: '',
      lastname: '',
      picture:'',
      status:'login'
    }
  },
  methods: {
    login: function(){
      FB.login( (response)=>{
        if(response.status == 'connected'){
          FB.api('/me?fields=id,first_name,last_name,picture{url}',(userData)=>{
             this.firstname = userData.first_name;
             this.lastname = userData.last_name;
             this.picture = userData.picture.data.url;
          });
        }
      });
    },
    logout: ()=>{
      FB.getLoginStatus((res)=>{
        console.log(res.status)
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
  height: 90px;
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
