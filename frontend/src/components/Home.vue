<template>
  <div class="hello">
    <div class="container" id="home-box">
      <h1>{{ msg }}</h1>
      <div class="columns" id="tabs">
        <div class="column">
          <img src="./../assets/girl.svg" alt="Placeholder image"  width="40" height="40"
          @mouseover="activateTab=1" v-bind:class="[ activateTab === 1 ? 'active' : '' ]">
          สินค้าแนะนำ
        </div>
        <div class="column">
          <img src="./../assets/medal.svg" alt="Placeholder image"  width="40" height="40"
          @mouseover="activateTab=2" v-bind:class="[ activateTab === 2 ? 'active' : '' ]">
          สินค้ายอดนิยม
        </div>
        <div class="column">
          <img src="./../assets/wallet.svg" alt="Placeholder image"  width="40" height="40"
          @mouseover="activateTab=3" v-bind:class="[ activateTab === 3 ? 'active' : '' ]">
          สินค้าส่งฟรี
        </div>
        
      </div>
      <div class="container" v-if="activateTab===1">
        <div class="columns">
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/1.png" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name1
                </div>
              </div>
            </router-link>
          </div>
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/2.jpg" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name1
                </div>
              </div>
            </router-link>
          </div>
        </div>
      </div>
      <div class="container" v-if="activateTab===2">
        <div class="columns">
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/3.png" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name2
                </div>
              </div>
            </router-link>
          </div>
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/4.jpg" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name2
                </div>
              </div>
            </router-link>
          </div>
        </div>
      </div>
      <div class="container" v-if="activateTab===3">
        <div class="columns">
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/5.jpg" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name3
                </div>
              </div>
            </router-link>
          </div>
          <div class="column">
            <router-link to="/ProductDetail">
              <div class="card">
                <div class="card-image is-4by3">
                  <img src="./../assets/mocked/6.png" alt="Placeholder image" width="100" height="100">
                </div>
                <div class="card-content">
                  product name3
                </div>
              </div>
            </router-link>
          </div>
        </div>
      </div>
      

    </div>
  </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';
import axios from 'axios';

const url = 'http://localhost:8080/products';
export default {
  name: 'Home',
  data () {
    return {
      msg: 'หน้าแรก สำหรับแสดงสินค้า',
      activateTab: 1
    }
  },
  methods: {
    getAllProduct: function () {
      axios.get(url)
      .then(response => {
          console.log(response);
      })
    }
  },
  mounted () {
    this.getAllProduct();
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
  max-width: 1000px;
  min-height: 2000px;
  margin-top: 13px;
  box-shadow: 2px 10px grey;
}

#tabs {
  margin: auto;
}




</style>
