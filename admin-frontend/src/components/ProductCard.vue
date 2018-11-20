<template>
  <v-layout align-center style='margin-bottom: 10px' column>
    <v-card height='288' width='180'>
      <v-layout style='height: 120px'>
        <v-img
          v-bind:src='this.product.img_url'
          aspect-ratio="2.75"
        ></v-img>
      </v-layout>
      <v-layout style='height: 75px' overflow-y-hidden>
        <v-card-title primary-title>
          <h6>
            {{productNameFormat(this.product.name)}}
          </h6>
        </v-card-title>
      </v-layout>
      <v-layout style='height: 50'>
        <v-card-actions >
          <h3>{{product.price}} ฿</h3>
        </v-card-actions>
      </v-layout>
      <v-layout style='height: 50' align-space-around fill-height>
        <v-flex>
          <v-dialog v-model="dialog" persistent max-width="290">
            <v-btn slot="activator" color="error" style='width: 157%'>
              <v-icon>delete_forever</v-icon>
            </v-btn>
            <v-card>
              <v-card-title class="headline">Delete?</v-card-title>
              <v-progress-linear :indeterminate="loading" color='red' v-show='loading'></v-progress-linear>
              <v-card-text>
                Do you want to delete <b>{{product.name}}</b> ?
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn @click="dialog = false">Disagree</v-btn>
                <v-btn color="error" @click='deleteProduct(product.id)'>Delete</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-flex>
      </v-layout>
    </v-card>
  </v-layout>
</template>
<script>
import { mapActions, mapGetters } from 'vuex'
import axios from '@/utils/axios'
export default {
  name: 'productCard',
  props: [
    'product',
    'id'
  ],
  data: () => ({
    dialog: false,
    loading: false
  }),
  methods: {
    ...mapActions([
      'setProducts',
      'setDefaultProducts'
    ]),
    productNameFormat (name) {
      if (name.length > 40) {
        return name.substring(0, 35) + '...'
      }
      return name
    },
    async deleteProduct (id) {
      this.loading = true
      const { data } = await axios.delete(`/product/${id}`)
      console.log(data)
      const products = this.getProducts.filter(product => {
        return product.id != id
      })
      this.setProducts(products)
      this.setDefaultProducts(products)
      this.loading = false
      this.dialog = false
    }
  },
  computed: {
    ...mapGetters([
      'getProducts'
    ])
  }
}
</script>
