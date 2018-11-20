<template>
  <div id="app">
    <nav class="navbar" role="navigation" aria-label="main navigation" id="nav-top">
       <div class="navbar-brand">
         <div class="navbar-start">
          <router-link to="/home">
            <img src="./assets/meepoohlogo.svg" width="220" height="100" style="margin-left:210px;margin-top:35px;">
          </router-link>
         </div>
      </div>
      <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="buttons">
              <input class='button is-rounded' v-model='keyword' @keypress.enter='onSubmit'  style=" margin-right: 260px; width:400px; margin-top:15px;" />
              <a class="button is-light" @click="login" style="margin-right:220px;margin-top:20px;background-color:#714EC9; color:white; ">
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
              <a @click="getProductsByCategory(1, 'เสื้อผ้าและแฟชั่น')">
                เสื้อผ้าและแฟชั่น
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(2, 'สุขภาพและความงาม')">
                สุขภาพและความงาม
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(3, 'มือถือและอุปกรณไอที')">
                มือถือและอุปกรณไอที
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(4, 'บ้านและไลฟ์สไตล์')">
                บ้านและไลฟ์สไตล์
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(5, 'เครื่องใช้ไฟฟ้าในบ้าน')">
                เครื่องใช้ไฟฟ้าในบ้าน
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(6, 'เครื่องใช้ไฟฟ้าในบ้าน')">
                เครื่องใช้ไฟฟ้าในบ้าน
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(7, 'อุปกรณ์กีฬา')">
                อุปกรณ์กีฬา
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered" style=" margin-bottom:18px;">
              <a @click="getProductsByCategory(8, 'อุปกรณ์ยานยนต์')">
                อุปกรณ์ยานยนต์
              </a>
            </p>
          </nav>
      </div>
    <router-view/>
  </div>

</template>

<script>
import axios from "axios";
import { mapActions } from 'vuex';

var test = "sss";
const url_login = `http://localhost:8080/login`;
const url_info = `http://localhost:8080/fbinfo`;
const url_category = 'http://localhost:8080/product?category=';
const URL_PRODUCTS = 'http://localhost:8080/products'
const config = {
  headers: {
    "Access-Control-Allow-Origin": "*",
    "Access-Control-Allow-Methods": "*",
    "Access-Control-Allow-Headers": "*"
  }
};
window.fbAsyncInit = function() {
  FB.init({
    appId: "363931931017126",
    cookie: true,
    xfbml: true,
    version: "v3.2"
  });

  FB.AppEvents.logPageView();
};

function checkLoginState() {
  FB.getLoginStatus(response => {
    statusChangCallback(response);
  });
}

(function(d, s, id) {
  var js,
    fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) {
    return;
  }
  js = d.createElement(s);
  js.id = id;
  js.src = "https://connect.facebook.net/en_US/sdk.js";
  fjs.parentNode.insertBefore(js, fjs);
})(document, "script", "facebook-jssdk");

export default {
  name: "App",
  data() {
    return {
      user: {},
      status: "",
      keyword: ''
    };
  },
  beforeMount() { // ทำก่อน render
    const userData = JSON.parse(localStorage.getItem('user'))
      if(userData){
        this.status = 'logout'
      }else
        this.status = 'login'
  },
  methods: {
    login: function() {
      FB.getLoginStatus(response => {
        if (response.status == "connected") {
          FB.logout(response => {
            localStorage.clear();
            this.status = "login";
          });
        }
        else{
          FB.login((response)=>{
            FB.api('/me?fields=id,first_name,last_name,picture{url}',(userData)=>{
              if(!userData.error){
                localStorage.setItem("user", JSON.stringify(userData));
                this.status = 'logout';
              }
            });
          });
        }
      });
    },
    getProductsByCategory: function (id, name) {
      axios.get(url_category + id)
      .then(response => {
          this.setProducts(response.data)
          this.$router.push('/product' + '?category_name=' + name)
      })
    },
    async onSubmit () {
      const { data } = await axios.get(URL_PRODUCTS)
      const searchProducts = data.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      })
      console.log(searchProducts)
      this.setProducts(searchProducts)
      this.setKeyword(this.keyword)
      this.$router.push('/search')
    },
    ...mapActions([
      'setProducts',
      'setKeyword'
    ])
  }
};
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

html {
  background: #f2f2f2;
}
#img{
  margin-left: 20px
}

#nav-box {
  width: 1000px;
}
#nav-top {
  height: 120px;
}
#nav-bot {
  margin-bottom: -30px;
}
#bar {
  font-size: 14px;
  margin-left: 30px;
  margin-top: 20px;
  background: white;

}
#CategoryBar {
  background: white;
  justify-content: center;
  border: 0.09em solid #e0e0e0;
  height: 40px;
}
</style>
