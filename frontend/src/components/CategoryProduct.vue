<template>
  <div id="CategoryProduct">
    <div class="container" id="Category-box">
        <hr>
        <p id="Category-name">{{$route.query.category_name}}</p>
        <div class="field is-grouped" id="field-box" v-for="products in chunkedProducts" :key="products.id">
            <!-- <div class="columns" v-for="products in chunkedProducts" :key="products.id">
                <div class="column" v-for="product in products" :key="product.id"> -->
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
                <!-- </div>
            </div> -->
        </div>    
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import chunk from 'chunk';

const url = 'http://localhost:8080/products';
export default {

    //filter
    filters: {
    cutWords: function (value) {
      return value.substr(0,50).toLowerCase()+"..."
    },

    dontCutWords: function (value) {
      return value.toLowerCase()
    },

  },

  data () {
    return {
      msg: 'หน้าแรก สำหรับแสดงสินค้า',
      products: []
    }
  },
  methods: {
    getAllProducts: function () {
      axios.get(url)
      .then(response => {
          // console.log(response.data);
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
    }
  },
  mounted () {
    this.getAllProducts();
    console.log(this.products);
  },
  computed: {
        chunkedProducts: function() {
            return chunk(this.products, 5)
        }
    }
}
</script>

<style>
#Category-name{
    margin-inline-start: 35px;
    font: 22px bolder;
    color: rebeccapurple;
}
#Category-box{
    background:white;
    width: 1000px;
    height: auto;
    margin-top: 35px;
}
#items{
    max-width: 172px;
    max-height: 260px;
    margin-left: 23px;
    margin-top: 70px;
    width: 172px;
    height:260px;
}
#field-box{
    margin-bottom: 0px;
}

#img{
    width:75%; 
    height:50%;
    margin-left: 20px;
    margin-right: auto;
}
</style>
