<template>
<div class="container">
  <div class="columns">
    <div class="column is-2 is-offset-1">
      <div class="" style="height: 300px; background-color: white">
        sidebar
      </div>
    </div>
    <div class="column is-6">
      <div class="columns">
        <div class="column">
          Search result of '{{keyword}}' total {{products.length}} item(s)
        </div>
      </div>
      <div class="columns box" v-for="product in products" :key="product.id" style="height: 180px; margin: 5px">
        <div class="column is-3">
          <img :src='product.img_url' width="100"/>
        </div>
        <div class="column" >
          <div class="columns" style="height: 50px">
            <div class="column">
              <h2>
                {{formatDetail(product.name)}}
              </h2>
            </div>
          </div>
          <div class="columns" style="height: 50px">
            <div class="column">
              <p style="color: grey">
                {{formatDetail(product.detail)}}
              </p>
            </div>
          </div>
          <div class="columns">
            <div class="column is-offset-8 is-4">
              <h3>
                {{product.price}} ฿
              </h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</template>
<script>
import axios from 'axios'
import { mapActions, mapGetters } from 'vuex'
const URL_PRODUCTS = 'http://localhost:8080/products'
export default {
  name: 'searchProduct',
  data: () => ({
    products: [],
    keyword: 'something'
  }),
  components: {
  },
  methods: {
    ...mapActions([
      'setProducts'
    ]),
    async getAllProduct () {
      const { data } = await axios.get(URL_PRODUCTS)
      console.log(data)
      this.setProducts(data)
    },
    formatDetail (detail) {
      if (detail.length > 50) {
        return detail.substring(0, 50) + '...'
      }
      return detail
    }
  },
  computed: {
    ...mapGetters([
      'getProducts'
    ])
  },
  mounted () {
    this.getAllProduct()
    this.products = this.getProducts
  },
}
</script>
