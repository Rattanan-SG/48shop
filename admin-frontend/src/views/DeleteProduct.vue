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
      'setProducts'
    ]),
    onSearch () {
      this.setProducts(this.getDefaultProducts.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      }))
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
  }
}
</script>
