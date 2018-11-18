<template>
  <v-app id="inspire">
    <v-content>
      <v-container fluid fill-height>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card class="elevation-12">
              <v-toolbar dark color="primary">
                <v-toolbar-title>Admin Login</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form>
                  <v-text-field prepend-icon="person" name="login" label="Login" type="text"></v-text-field>
                  <v-text-field id="password" prepend-icon="lock" name="password" label="Password" type="password"></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <router-link to='/add-product'>
                  <v-btn color="primary" @click='onLogin'>Login</v-btn>
                </router-link>
              </v-card-actions>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import { mapActions } from 'vuex'
import axios from '@/utils/axios'
export default {
  data: () => ({
    drawer: null
  }),
  props: {
    source: String
  },
  methods: {
    ...mapActions([
      'setNavBar',
      'setProductUnRecommend',
      'setProducts'
    ]),
    onLogin () {
      this.setNavBar(true)
      this.setAllProducts()
    },
    async setAllProducts () {
      const { data } = await axios.get('/products')
      console.log(data)
      this.setProductUnRecommend(data)
      this.setProducts(data)
    }
  },
  mounted () {
    this.setNavBar(false)
  }
}
</script>
