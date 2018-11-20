<template>
  <div class="Home">
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
      products: [],
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
  },
  computed: {
    chunkedProducts: function() {
        return chunk(this.products, 4)
    }
  },
  methods: {
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
      })
    },
    scrollToTop: function() {
        window.scrollTo(0,0);
    },
  }
}
</script>
<style lang="scss" scoped>
#home-box {
  background:white;
  width: 1000px;
  min-height: 2000px;
  height: auto;
  margin-top: 40px;
  padding-top: 50px;
}
#head{
  margin-left: 60px;
  color:#714EC9;  
  font-size: 35px;
  margin-bottom: 1px;
}
#img{
  max-width: 150px;
  max-height: 150px ;
  margin-left: 30px;
  width:75%; 
  height:50%;

  
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
    margin-top: 50px;
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
