<template>
  <div class="hello">
    <div class="container" id="home-box">
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
          สินค้าทั้งหมด
        </div>
        
      </div>
       <div class="container" v-if="activateTab===1">
          <div class="card-carousel-wrapper" id="slide">
              <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>
              <div class="card-carousel">
                <div class="card-carousel--overflow-container">
                    <div class="card-carousel-cards" :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
                        <div class="card" id="item" v-for="product in products" :key="product.id">
                          <div class="card-image">
                            <figure class="image is-4by3">
                              <img :src=product.image alt="Placeholder image">
                            </figure>  
                          </div>
                          <div class="card-content">
                            <div class="content">
                              <p>{{ product.name }}</p>
                            </div>
                            <div class="content">
                              <p>{{ product.price }}</p>
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
const url_product = `http://localhost:8080/product/`;
export default {
  name: 'CarouselGroup',
  name: 'Home',
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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss" scoped>
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
$vue-navy: #2c3e50;
$vue-navy-light: #3a5169;
$vue-teal: #42b883;
$vue-teal-light: #42b983;
$gray: #666a73;
$light-gray: #f8f8f8;


body {
  background: $light-gray;
  color: $vue-navy;
  font-family: 'Source Sans Pro', sans-serif;
  min-height: 1000px; 
}

.card-carousel-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  margin: 20px 0 40px;
  color: $gray;
}

.card-carousel {
  display: flex;
  justify-content: center;
  width: 800px; 
  
  &--overflow-container {
    overflow: hidden;
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
    transition: transform 150ms linear;
    &[disabled] {
      opacity: 0.2;
      border-color: black;
    }
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
  transform: translatex(0px);
 
  .card-carousel--card {
    margin: 0 10px;
    cursor: pointer;
    box-shadow: 0 4px 15px 0 rgba(40,44,53,.06), 0 2px 2px 0 rgba(40,44,53,.08);
    background-color: #fff;
    border-radius: 4px;
    z-index: 3;
    margin-bottom: 2px;
    
    &:first-child {
      margin-left: 0;
    }
    
    &:last-child {
      margin-right: 0;
    }
    
    img {
      vertical-align: bottom;
      border-top-left-radius: 4px;
      border-top-right-radius: 4px;
      transition: opacity 150ms linear;
      user-select: none;
      
      &:hover {
        opacity: 0.5;
      }
    }
    
    &--footer {
      border-top: 0;
      padding: 7px 15px;
      
      p {
        padding: 3px 0;
        margin: 0;
        margin-bottom: 2px;
        font-size: 19px;
        font-weight: 500;
        color: $vue-navy;
        user-select: none;
        
        &:nth-of-type(2) {
          font-size: 12px;
          font-weight: 300;
          padding: 6px;
          background: rgba(40,44,53,.06);
          display: inline-block;
          position: relative;
          margin-left: 4px;
          color: $gray;
          
          &:before {
            content:"";
            float:left;
            position:absolute;
            top:0;
            left: -12px;
            width:0;
            height:0;
            border-color:transparent rgba(40,44,53,.06) transparent transparent;
            border-style:solid;
            border-width:12px 12px 12px 0;
        }
        
          &:after {
            content:"";
            position:absolute;
            top:10px;
            left:-1px;
            float:left;
            width:4px;
            height:4px;
            border-radius: 2px;
            background: white;
            box-shadow:-0px -0px 0px #004977;
          }
        }
      }
    }
  }
}

h1 {
  font-size: 3.6em;
  font-weight: 100;
  text-align: center;
  margin-bottom: 0;
  color: $vue-teal;
}
#slide{
  margin-right: 140px;
}

</style>
