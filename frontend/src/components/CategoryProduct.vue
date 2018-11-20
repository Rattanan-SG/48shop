<template>
    <div class="container" id="Category-box">
        <hr>
        <p id="Category-name">{{$route.query.category_name}}</p>
        <div class="field is-grouped" id="field-box" v-for="products in chunkedProducts" :key="products.id">
            <router-link :to="{name: 'ProductDetail', params: { id: product.id }}" v-for="product in products" :key="product.id"
            @click.native="scrollToTop">
                <div class="card" id="items" >
                    <div class="card-image is-4by3">
                    <img :src=product.img_url alt="รอซักครู่" id="img">
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
</template>

<script>
import axios from 'axios';
import chunk from 'chunk';
import { mapActions, mapGetters } from 'vuex'

const url_product = 'http://localhost:8080/products';
const url_category = 'http://localhost:8080/product?category=';
export default {
    data () {
        return {
        msg: 'หน้าแรก สำหรับแสดงสินค้า',
        categoryId: ''
        }
    },
    methods: {
        scrollToTop: function() {
            window.scrollTo(0,0);
        },
        ...mapActions([
          'setProducts'
        ])
    },
    computed: {
        chunkedProducts: function() {
            console.log(this.getProducts);
            return chunk(this.getProducts, 4)
        },
        ...mapGetters([
            'getProducts'
        ])
    },
    //filter
    filters: {
        cutWords: function (value) {
        return value.substr(0,50).toLowerCase()+"..."
        },

        dontCutWords: function (value) {
        return value.toLowerCase()
        },
    },
    }
</script>

<style>
#Category-name{
    margin-inline-start: 35px;
    font: 22px bolder;
    color: rebeccapurple;
    margin-left: 60px;
    margin-top: 40px;
}
#Category-box{
    background:white;
    width: 1000px;
    height: auto;
    min-height: 300px;
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
#items{
    max-width: 200px;
    max-height: 500px;
    margin-left: 40px;
    margin-top: 70px;
    width: 200px;
    height:330px; 
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
#price{
    margin-inline-start: 80px;
    font: 13px sans-serif;
    color: #333234;
    max-width: 180px;
    max-height: 150px ;
    width: 200px;
    height:2px;
    background:none;
    margin-top: -35px;
    margin-left: 100px;
}
.card{
    box-shadow: none;
}
#items:hover{
-webkit-box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
-moz-box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
box-shadow: 0px 0px 80px -4px rgba(0,0,0,0.29);
}
</style>
