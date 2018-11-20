<template>
  <div class="hello">
    <div class="container" id="home-box">
      <div class="columns" id="tabs">
        <div class="column">
          <img src="./../assets/girl.svg" alt="Placeholder image"  width="40" height="40" 
          @mouseover="activateTab=1" v-bind:class="[ activateTab === 1 ? 'active' : '' ]">
          <p>สินค้าแนะนำ</p>
        </div>
        <div class="column">
          <img src="./../assets/wallet.svg" alt="Placeholder image"  width="40" height="40"
          @mouseover="activateTab=2" v-bind:class="[ activateTab === 2 ? 'active' : '' ]">
         <p> สินค้าทั้งหมด</p>
        </div>  
      </div>
       <div class="container" v-if="activateTab===1" id="Category-box">
          <div class="card-carousel-wrapper" id="slide">
              <div class="card-carousel--nav__left" @click="moveCarousel(-1)" :disabled="atHeadOfList"></div>   
                <div class="card-carousel--overflow-container">
                    <div class="card-carousel-cards " :style="{ transform: 'translateX' + '(' + currentOffset + 'px' + ')'}">
                      <div class="card" id="item" v-for="product in recProducts" :key="product.id">
                        <div class="field is-grouped" id="img">
                          <div class="card-image">
                            <figure class="image center" >
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
      <div class="container" v-if="activateTab===2" id="Category-box">
        <div class="field is-grouped" id="field-box" v-for="products in chunkedProducts" :key="products.id">
            <router-link :to="{name: 'ProductDetail', params: { id: product.id }}" v-for="product in products" :key="product.id"
            @click.native="scrollToTop">
                <div class="card" id="items" >
                    <div class="card-image is-4by3">
                    <img :src=product.image alt="Placeholder image" id="img">
                    </div>
                    <div class="card-content">
                        <div v-if="product.name.length <= 50">
                            <p>{{ product.name | dontCutWords}}</p>
                        </div>
                        <div v-else>
                            <p>{{ product.name | cutWords}}</p>
                        </div>
                    </div>
                </div>
            </router-link>
        </div>    
      </div>
    </div>
  </div>
</template>
<script>
import './../../node_modules/bulma/css/bulma.css';
import axios from 'axios';
import chunk from 'chunk';

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
      windowSize: 0,
      paginationFactor: 150, // ความเร็วการสไลด์ size * 10
      products: [],
      recProducts: [],
      recProductId: [19,22,3,4,34,17,13,1,26,37,5,49,23,32,42],
      topProducts: [],
      topProductId: [1,2,3,5,6,7,18,32,35,39,41,45],
      product: {
        id: 0,
        name: '',
        price: '',
        image: '',
      }
    }
  },
  mounted () {
    this.getAllProducts();
    this.getRecProduct();
  },
  computed: {
    atEndOfList() {
      return this.currentOffset <= (this.paginationFactor * -1) * (this.recProducts.size - this.windowSize);
    },
    atHeadOfList() {
      return this.currentOffset === 0;
    },
    chunkedProducts: function() {
        return chunk(this.products, 5)
    }
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
    getAllProducts: function () {
      axios.get(url)
      .then(response => {
          response.data.forEach(ele => {
            this.products.push({
                id: ele.id,
                name: ele.name,
                price: ele.price,
                image: ele.img_url 
            });
          });
          console.log(response);
          console.log(this.products);
      })
    },
    scrollToTop: function() {
        window.scrollTo(0,0);
    },
    getRecProduct: function() {
      this.recProductId.forEach(id => {
        axios.get(url_product + id)
        .then(response => {
          this.recProducts.push({
              id: response.data.id,
              name: response.data.name,
              price: response.data.price,
              image: response.data.img_url 
          });
        })
      })
    }
  }
}
</script>
<style lang="scss" scoped>
#home-box {
  background:white;
  width: 1000px;
  min-height: 2000px;
  height: auto;
  margin-top: 0px;
}
#tabs {
  margin-top: 30px;
  text-align: center;
  margin: 20px 200px 0 200px;
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
#field-box{
    margin-bottom: 0px;
}
#Category-box{
    background:white;
    width: 1000px;
    height: auto;
}
#items{
    max-width: 172px;
    max-height: 260px;
    margin-left: 23px;
    margin-top: 70px;
    width: 172px;
    height:260px;
 
}
#items:hover{
-webkit-box-shadow: 0px 0px 86px -42px rgba(0,0,0,0.97);
-moz-box-shadow: 0px 0px 86px -42px rgba(0,0,0,0.97);
box-shadow: 0px 0px 86px -42px rgba(0,0,0,0.97);
}
#field-box{
    margin-bottom: 0px;
}

#img{
    width:75%; 
    height:50%;
    margin-left: 35px;
    margin-top: 15px;
}
</style>
