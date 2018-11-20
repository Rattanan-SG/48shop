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
            Search result of '{{keyword}}' total {{getProducts.length}} item(s)
          </div>
        </div>
        <div v-for="product in getProducts" :key="product.id" style="height: 180px; margin: 5px">
          <router-link :to='{name: "ProductDetail", params: {id: product.id}}' >
            <ProductCard :product='product'/>
          </router-link>
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
  name: 'SearchProduct',
  data: () => ({
    keyword: 'something'
  }),
  components: {
    ProductCard
  },
  methods: {
    ...mapActions([
      'setProducts'
    ])
  },
  computed: {
    ...mapGetters([
      'getProducts'
    ])
  },
  mounted () {
  },
}
</script>
