<template>
  <div id="app">
    <nav class="navbar" role="navigation" aria-label="main navigation">
      <div class="navbar-brand">
        <router-link to="/home">
          <img src="./assets/meepoohlogo.svg" width="220" height="100">
        </router-link>

        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarBasicExample">
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
          <span aria-hidden="true"></span>
        </a>
      </div>
      <div id="navbarBasicExample" class="navbar-menu">
        <div class="navbar-start">
          <div class="navbar-item has-dropdown is-hoverable">
            <a class="navbar-link">
              More
            </a>
            <div class="navbar-dropdown">
              <a class="navbar-item">
                About
              </a>
              <a class="navbar-item">
                Jobs
              </a>
              <a class="navbar-item">
                Contact
              </a>
              <hr class="navbar-divider">
              <a class="navbar-item">
                Report an issue
              </a>
            </div>
          </div>
        </div>
        <div class="navbar-end">
          <div class="navbar-item">
            <div class="buttons">
              <input class='button is-rounded' v-model='keyword' @keypress.enter='onSubmit' />
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
              <router-link to="/category?category_name=เสื้อผ้าและแฟชั่น" @click="getProductsByCategory">
                เสื้อผ้าและแฟชั่น
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=สุขภาพและความงาม" @click="getProductsByCategory">
                สุขภาพและความงาม
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=มือถือและอุปกรณไอที" @click.native="getProductsByCategory">
                มือถือและอุปกรณไอที
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=บ้านและไลฟ์สไตล์" @click.native="getProductsByCategory">
                บ้านและไลฟ์สไตล์
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=เครื่องใช้ไฟฟ้าในบ้าน" @click.native="getProductsByCategory">
                เครื่องใช้ไฟฟ้าในบ้าน
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=อาหารและเครื่องดื่ม" @click.native="getProductsByCategory">
                อาหารและเครื่องดื่ม
              </router-link>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <router-link to="/category?category_name=อุปกรณ์กีฬา" @click.native="getProductsByCategory">
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
import axios from "axios";
import { mapActions } from 'vuex';

var test = "sss";
const url_login = `http://localhost:8080/login`;
const url_info = `http://localhost:8080/fbinfo`;
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
    appId: "253765508571750",
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
              localStorage.setItem("user", JSON.stringify(userData));
              this.status = 'logout';
            });
          });
        }
      });
    },
    getProductsByCategory: function() {
        console.log(this.$route.query.category_name);
        // axios.get(url_category + this.route.query.category_name)
        // .then(response => {
        //     this.product.cate_id = response.data.id
        // })
        // console.log(product.cate_id);
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

#nav-box {
  width: 1000px;
}
#nav-top {
  height: 120px;
}
#nav-bot {
  margin-bottom: -30px;
}

/* Category */
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
