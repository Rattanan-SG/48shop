<template>
  <v-app>
    <v-toolbar app v-show='getNavBar'>
      <v-toolbar-title class="headline text-uppercase">
        <span>48 SHOP</span>
        <span class="font-weight-light">Admin Page</span>
      </v-toolbar-title>
      <v-toolbar-items>
        <v-btn flat>
          <router-link to='/add-product' style="text-decoration: none">Add Product</router-link>
        </v-btn>
        <v-btn flat>
          <router-link to='/add-recommend-product' style="text-decoration: none">Add Recommend Product</router-link>
        </v-btn>
        <v-btn flat>
          <router-link to='/view-recommend-product' style="text-decoration: none">View Recommend Product</router-link>
        </v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-btn flat>
        <router-link to='/' style="text-decoration: none">LOGOUT</router-link>
      </v-btn>
    </v-toolbar>
    <v-content>
      <router-view></router-view>
    </v-content>
  </v-app>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'App',
  components: {
  },
  data () {
    return {
      //
    }
  },
  computed: {
    ...mapGetters([
      'getNavBar'
    ])
  }
}
</script>
