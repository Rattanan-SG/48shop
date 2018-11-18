<template>
    <v-card>
      <v-img
        v-bind:src='this.product.img_url'
        aspect-ratio="2.75"
      ></v-img>
      <v-card-title primary-title>
        <div>
          <h3 class="headline mb-0">{{this.product.name}}</h3>

        </div>
      </v-card-title>
      <v-card-actions>
        <v-flex offset-md1>
          <h2>{{product.price}} ฿</h2>
        </v-flex>
        <v-spacer></v-spacer>
        <v-btn color="primary" @click.native='addProductToRecommend(product, id)' >Add</v-btn>
      </v-card-actions>
    </v-card>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
export default {
  name: 'productCard',
  props: [
    'product',
    'id'
  ],
  methods: {
    ...mapActions([
      'setRecommendProducts',
      'setProductUnRecommend',
      'setProducts'
    ]),
    addProductToRecommend (product, index) {
      let temp = this.getProducts
      const id = temp[index].id
      temp.splice(index, 1)
      let productLeft = this.getProductUnRecommend.filter(product => {
        return product.id != id
      })
      this.setProductUnRecommend(productLeft)
      this.setRecommendProducts(product)
    }
  },
  computed: {
    ...mapGetters([
      'getRecommendProducts',
      'getProducts',
      'getProductUnRecommend'
    ])
  }

}
</script>
