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
      <div id="navbarMenu" class="navbar-menu">
        <div class="navbar-item">
            <input class='button' v-model='keyword' @keypress.enter='onSubmit' 
            style="margin: 10px 50px 0 20px; min-width: 300px" />
        </div>
        <div class="navbar-item" v-if="isLogin" style="margin-top:15px;">
            <img :src=user.picture.data.url alt="รอซักครู่" class="is-rounded" style="border-radius: 50%;margin-right: 20px">
              Welcome,
              {{user.first_name}}
              {{user.last_name}}
        </div>
        <div class="navbar-item" v-else>

        </div>
      </div>
      <div class="navbar-end">
        <div class="navbar-item">
          <a class="button is-outlined is-link" @click="login" style="margin-right: 210px;margin-top:20px;">
            <span class="icon">
              <i class="fab fa-facebook-f"></i>
            </span>
            <span>{{status}}</span>
          </a>
        </div>
      </div>
    </nav>
      <div class="field is-grouped" id="CategoryBar" style="background-color:#714EC9;">
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(1, 'เสื้อผ้าและแฟชั่น')" style="color:white;">
                เสื้อผ้าและแฟชั่น
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(2, 'สุขภาพและความงาม')" style="color:white;">
                สุขภาพและความงาม
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(3, 'มือถือและอุปกรณไอที')" style="color:white;">
                มือถือและอุปกรณไอที
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(4, 'บ้านและไลฟ์สไตล์')" style="color:white;">
                บ้านและไลฟ์สไตล์
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(5, 'เครื่องใช้ไฟฟ้าในบ้าน')" style="color:white;">
                เครื่องใช้ไฟฟ้าในบ้าน
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(6, 'เครื่องใช้ไฟฟ้าในบ้าน')" style="color:white;">
                เครื่องใช้ไฟฟ้าในบ้าน
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered">
              <a @click="getProductsByCategory(7, 'อุปกรณ์กีฬา')" style="color:white;">
                อุปกรณ์กีฬา
              </a>
            </p>
          </nav>
          <nav class="level" id="bar">
            <p class="level-item has-text-centered" style=" margin-bottom:18px; " >
              <a @click="getProductsByCategory(8, 'อุปกรณ์ยานยนต์')" style="color:white;">
                อุปกรณ์ยานยนต์
              </a>
            </p>
          </nav>
      </div>
    <router-view/>
  </div>

</template>

<script>
import axios from "@/utils/axios";
import { mapActions } from 'vuex';

var test = "sss";
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
      keyword: '',
      isLogin: false
    };
  },
  beforeMount() { // ทำก่อน render
    const userData = JSON.parse(localStorage.getItem('user'))
      if(userData){
        this.status = 'ออกจากระบบ'
        this.user = userData
        this.isLogin = true
      } else {
        this.isLogin = false
        this.status = 'เข้าสู่ระบบ'
      }
  },
  methods: {
    login: function() {
      FB.getLoginStatus(response => {
        if (response.status == "connected") {
          FB.logout(response => {
            localStorage.clear();
            this.isLogin = false
            this.status = "เข้าสู่ระบบ";
            console.log('1: ' + this.isLogin)
          });
        }
        else{
          FB.login((response)=>{
            FB.api('/me?fields=id,first_name,last_name,picture{url}',(userData)=>{
              if(!userData.error){
                localStorage.setItem("user", JSON.stringify(userData));
                this.user = userData
                this.isLogin = true
                this.status = 'ออกจากระบบ';
                console.log('2: ' + this.isLogin)
              }
            });
          });
        }
      });
    },
    getProductsByCategory: function (id, name) {
      axios.get('/product', {
        params: {
          category: id
        }
      })
      .then(response => {
          this.setProducts(response.data)
          this.$router.push('/product' + '?category_name=' + name)
      })
    },
    async onSubmit () {
      const { data } = await axios.get('/products')
      const searchProducts = data.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      })
      this.setProducts(searchProducts)
      this.setKeyword(this.keyword)
      this.keyword = ''
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
  margin-left: 35px;
  margin-top: 20px;

}
#CategoryBar {
  background: white;
  justify-content: center;
  border: 0.09em solid #e0e0e0;
  height: 40px;
}
</style>
