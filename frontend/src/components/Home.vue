<template>
  <div class="hello">
    <div class="container" id="home-box">
      <div class="text" id="head">สินค้าทั้งหมด</div>
        <div class="field is-grouped" id="field-box" v-for="products in chunkedProducts" :key="products.id">
            <router-link :to="{name: 'ProductDetail', params: { id: product.id }}" v-for="product in products" :key="product.id"
            @click.native="scrollToTop">
                <div class="card" id="items" >
                    <div class="card-image is-4by3">
                      <img :src=product.image alt="Placeholder image" id="img">
                    </div>
                    <div class="card-content" style="height: 100px">
                        <div v-if="product.name.length <= 35">
                            <p>{{ product.name | dontCutWords}}</p>
                        </div>
                        <div v-else>
                            <p>{{ product.name | cutWords}}</p>
                        </div>
                    </div>
                    <div class="footer" id="price">
                     <strong> {{product.price}}  บาท</strong>
                    </div>
                </div>
            </router-link>
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
        return chunk(this.products, 4)
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
#head{
  padding: 30px;
  margin-left: 30px;
  color:#714EC9;  
  font-size: 35px;
}
#img{
  max-width: 150px;
  max-height: 150px ;
  margin-left: 30px;
  margin-bottom: 10%;
  width:75%; 
  height:50%;
  margin-top: 10px;
  
}
#content{
  max-width: 180px;
  max-height: 150px ;
  width: 172px;
  height: 40px ;
  font-size: 10px; 
}

.card{
  box-shadow: none;
}

#price{
    margin-inline-start: 80px;
    font: 15px sans-serif;
    color:#714EC9;
    max-width: 180px;
    max-height: 150px ;
    width: 200px;
    height:2px;
    background:none;
    margin-top: -38px;
    margin-left: 90px;
}
#Category-box{
    background:white;
    width: 1000px;
    height: auto;
}
#items{
    max-width: 200px;
    max-height: 500px;
    margin-left: 40px;
    margin-top: 43px;
    width: 200px;
    height:330px;
 
}
#items:hover{
-webkit-box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
-moz-box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
}
#field-box{
    margin-bottom: 0px;
}
</style>
