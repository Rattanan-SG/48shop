<template>
  <div class="hello">
    <div class="container" id="home-box">
      <div class="columns" id="tabs">
        <div class="column">
          <img src="./../assets/girl.svg" alt="Placeholder image"  width="40" height="40" style=" margin-left: 20px; margin-top: 30px;" 
          @mouseover="activateTab=1" v-bind:class="[ activateTab === 1 ? 'active' : '' ]">
          <p>สินค้าแนะนำ</p>
        </div>
        <div class="column">
          <img src="./../assets/medal.svg" alt="Placeholder image"  width="40" height="40" style=" margin-left: 20px; margin-top: 30px;"
          @mouseover="activateTab=2" v-bind:class="[ activateTab === 2 ? 'active' : '' ]">
          <p>สินค้ายอดนิยม</p>
        </div>
        <div class="column">
          <img src="./../assets/wallet.svg" alt="Placeholder image"  width="40" height="40" style=" margin-left: 20px; margin-top: 30px;"
          @mouseover="activateTab=3" v-bind:class="[ activateTab === 3 ? 'active' : '' ]">
         <p> สินค้าทั้งหมด</p>
        </div>  
      </div>
       <div class="container" v-if="activateTab===1">
          <div class="card-carousel-wrapper" id="slide">
              <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>   
                <div class="card-carousel--overflow-container">
                    <div class="card-carousel-cards " :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
                        <div class="card" id="item" v-for="product in products" :key="product.id">
                          <div class="field is-grouped" id="img">
                          <div class="card-image">
                            <figure class="image " >
                              <img :src=product.image alt="Placeholder image">
                            </figure>  
                          </div>
                          </div>
                          <div class="field is-grouped" id="content" >
                          <div class="card-content" >
                            <div class="field is-grouped">
                            <div class="content">
                              <div v-if="product.name.length <= 20">
                                <p>{{ product.name | dontCutWords}}</p>
                               </div>
                              <div v-else>
                                <p>{{ product.name | cutWords}}</p>
                               </div>
                            </div>
                            </div>
                            <div class="field is-grouped" id="price" >
                            <div class="content">
                              <p>{{ product.price | currency }}</p>
                            </div>
                            </div>
                          </div>
                          </div>
                        </div>
                    </div>
                </div>
            
              <div class="card-carousel--nav__right" @click="moveCarousel(1)" :disabled="atEndOfList"></div>
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
                <div class="card-image is-4by3"  >
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
const url_product = `http://localhost:8080/product/`;
export default {
  name: 'CarouselGroup',
  name: 'Home',

  //filter
  filters: {
    cutWords: function (value) {
      return value.substr(0,20).toLowerCase()+"..."
    },

    dontCutWords: function (value) {
      return value.toLowerCase()
    },

    currency: function (value) {
      return "THB "+value
    }
  },


  
  data () {
    return {
      msg: 'หน้าแรก สำหรับแสดงสินค้า',
      activateTab: 1,
      currentOffset: 0,
      windowSize: 3,
      paginationFactor: 220,
      recProduct: [19,22,3,4,8,12,13,15,26,37,5,6,9,23,32,42],
      topProduct: [1,2,3,5,6,7,18,32,35,39,41,45],
      products: [],
      product: {
        id: 0,
        name: '',
        price: '',
        image: '',
        detail: ''
      }
    }
  },
  mounted () {
    this.getAllProduct();
    this.getRecProduct();
  },
  computed: {
    atEndOfList() {
      return this.currentOffset <= (this.paginationFactor * -1) * (this.products.length - this.windowSize);
    },
    atHeadOfList() {
      return this.currentOffset === 0;
    },
  },
  methods: {
    moveCarousel: function (direction) {
      // Find a more elegant way to express the :style. consider using props to make it truly generic
      if (direction === 1 && !this.atEndOfList) {
        this.currentOffset -= this.paginationFactor;
      } else if (direction === -1 && !this.atHeadOfList) {
        this.currentOffset += this.paginationFactor;
      } 
    },
    getAllProduct: function () {
      axios.get(url)
      .then(response => {
          console.log(response.data);
      })
    },
    getRecProduct: function() {
      this.recProduct.forEach(id => {
        axios.get(url_product + id)
        .then(response => {
          console.log(response.data.id);
          this.products.push({
              id: response.data.id,
              name: response.data.name,
              price: response.data.price,
              image: response.data.img_url 
          });
        })
      });
    }
  }
}
</script>
<style lang="scss" scoped>
#nav-box {
  width: 1000px;
}
#home-box {
  background: white;
  max-width: 1000px;
  min-height: 2000px;
  margin-top: 13px;
}
#tabs {
  margin-left: 20%;
  margin-top: 30px;
}
$vue-teal: #42b883;
body {
  font-family: 'Source Sans Pro', sans-serif;
  min-height: 1000px; 
}
#item{
  max-width: 300px;
  height: 200px;
  margin-left: 20px;
  border: none;
}
#img{
  max-width: 100px;
  max-height: 100px ;
  margin-left: 30px;
  margin-bottom: 25%;
}
#content{
  width: 172px;
  height: 50px ;
  font-size: 10px; 
}
.card-carousel-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px 0 40px;
}
.card{
  box-shadow: none;
}
.card-carousel {
  display: flex;
  justify-content: center;
  width: 800px;  
  &--overflow-container {
    overflow: hidden;
    width: 900px;
    height: 300px;
    margin-top: 200px;
  }
  &--nav__left,
  &--nav__right {
    display: inline-block;
    width: 15px;
    height: 15px;
    padding: 10px;
    box-sizing: border-box; 
    border-top: 2px solid $vue-teal;
    border-right: 2px solid $vue-teal; 
    cursor: pointer;
    margin: 0 10px;
    transition: transform 120ms linear;
  }
  &--nav__left {
    transform: rotate(-135deg);
    &:active {
      transform: rotate(-135deg) scale(0.9);
    }
  }
  &--nav__right {
    transform: rotate(45deg);
    &:active {
      transform: rotate(45deg) scale(0.9);
    }
  }
}
.card-carousel-cards {
  display: flex;
  transition: transform 150ms ease-out;
  transform: translatex(120px);
  .card-carousel--card {
    margin: 0 10px;
    cursor: pointer;
    border-radius: 4px;
    margin-bottom: 2px;
  }
}
#slide{
  margin-right: 140px;
  height: 250px;
  width: 1000px;
}
#price{
    margin-inline-start: 80px;
    font: 13px sans-serif;
}
</style>
