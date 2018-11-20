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
        <div v-for="product in products" :key="product.id" style="height: 180px; margin: 5px">
          <ProductCard :product='product'/>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import ProductCard from '@/components/ProductCard'
import { mapActions, mapGetters } from 'vuex'
const URL_PRODUCTS = 'http://localhost:8080/products'
export default {
  name: 'searchProduct',
  data: () => ({
    products: [],
    keyword: 'something'
  }),
  components: {
    ProductCard
  },
  methods: {
    ...mapActions([
      'setProducts'
    ]),
    async getAllProduct () {
      const { data } = await axios.get(URL_PRODUCTS)
      console.log(data)
      this.products = data
      this.setProducts(data)
    }
  },
  computed: {
    ...mapGetters([
      'getProducts'
    ])
  },
  mounted () {
    this.getAllProduct()
  },
}
</script>
