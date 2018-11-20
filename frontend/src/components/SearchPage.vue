<template>
  <div class="container">
    <div class="columns">
      <div class="column is-offset-3 is-6">
        <div class="columns">
          <div class="column">
            Search result of '{{getKeyword}}' total {{getProducts.length}} item(s)
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
import ProductCard from '@/components/ProductCard'
import { mapActions, mapGetters } from 'vuex'

export default {
  name: 'SearchProduct',
  data: () => ({
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
      'getProducts',
      'getKeyword'
    ])
  },
  mounted () {
  },
}
</script>
