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
        <v-flex v-for='(product, productKey) in productList' :key='product.id' md3>
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
  name: 'addRecommendProduct',
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
      'setProductUnRecommend'
    ]),
    onSearch () {
      let temp = this.getProductUnRecommend
      this.productList = this.getProductUnRecommend.filter(product => {
        return product.name.toLowerCase().includes(this.keyword.toLowerCase())
      })
      console.log(temp)
      this.setProductUnRecommend(temp)
      this.setProducts(this.productList)
    }
  },
  computed: {
    ...mapGetters([
      'getProducts',
      'getProductUnRecommend',
      'getRecommendProducts'
    ])
  },
  mounted () {
    this.productList = this.getProductUnRecommend
  },
  beforeMount () {

  }
}
</script>
