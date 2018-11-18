<template>
  <div id="CategoryProduct">
    <div class="container" id="Category-box">
        <div class="field is-grouped" id="field-box" v-for="products in chunkedProducts" :key="products.id">
            <!-- <div class="columns" v-for="products in chunkedProducts" :key="products.id">
                <div class="column" v-for="product in products" :key="product.id"> -->
                    <router-link :to="{name: 'ProductDetail', params: { id: product.id }}" v-for="product in products" :key="product.id">
                        <div class="card" id="items" >
                            <div class="card-image is-4by3">
                            <img :src=product.image alt="Placeholder image" width="100" height="100">
                            </div>
                            <div class="card-content">
                                {{product.name}}
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
#Category-box{
    background:white;
    width: 1000px;
    height: 700px;
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
</style>
