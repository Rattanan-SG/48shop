<template>
  <v-app>
    <v-container>
      <v-layout>
        <v-text-field
          v-model='keyword'
          @keyup='onSearch'
          label='Search for product'
          prepend-inner-icon='search'
        ></v-text-field>
      </v-layout>
      <v-layout wrap justify-center>
        <v-flex v-for='product in productList' :key='product.id' md3>
          <ProductCard :product='product'/>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>
<script>
import { mapGetters, mapActions } from 'vuex'
import ProductCard from '@/components/ProductCard'
import axios from '@/utils/axios'
export default {
  name: 'addRecommendProduct',
  components: {
    ProductCard
  },
  data: function () {
    return {
      keyword: '',
      productList: []
    }
  },
  methods: {
    ...mapActions([
      'setProducts'
    ]),
    onSearch () {
      this.productList = this.getProducts.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      })
    },
    async setAllProducts () {
      const { data } = await axios.get('/products')
      console.log(data)
      this.productList = data
      this.setProducts(data)
    }
  },
  computed: {
    ...mapGetters([
      'getProducts'
    ])
  },
  mounted () {
    this.productList = this.getProducts
    this.setAllProducts()
  }
}
</script>
