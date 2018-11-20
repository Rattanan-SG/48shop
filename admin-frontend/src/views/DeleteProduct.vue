<template>
  <v-app>
    <v-container>
      <v-layout>
        <h4>{{this.getProducts.length}} product(s) left</h4>
      </v-layout>
      <v-layout>
        <v-text-field
          v-model='keyword'
          @keyup='onSearch'
          label='Search for product'
          prepend-inner-icon='search'
        ></v-text-field>
      </v-layout>
      <v-layout wrap align-center>
        <v-flex v-for='(product, productKey) in getProducts' :key='product.id' md2>
          <ProductCard :product='product' :id='productKey' />
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
  name: 'deleteProduct',
  components: {
    ProductCard
  },
  data: function () {
    return {
      keyword: '',
      productList: Array
    }
  },
  methods: {
    ...mapActions([
      'setProducts',
      'setDefaultProducts'
    ]),
    onSearch () {
      this.setProducts(this.getDefaultProducts.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      }))
    },
    async setAllProducts () {
      const { data } = await axios.get('/products')
      this.setDefaultProducts(data)
      this.setProducts(data)
    }
  },
  computed: {
    ...mapGetters([
      'getProducts',
      'getDefaultProducts'
    ])
  },
  mounted () {
    this.productList = this.getProducts
    this.setAllProducts()
  }
}
</script>
