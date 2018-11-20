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
                <v-flex md1>
                  <v-progress-circular
                    :indeterminate='loading'
                    color="primary"
                    v-show='loading'
                  ></v-progress-circular>
                </v-flex>
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
    loading: false
  }),
  methods: {
    ...mapActions([
      'setNavBar',
      'setDefaultProducts',
      'setProducts',
      'setCategories'
    ]),
    onLogin () {
      this.loading = true
      this.setAllCategories()
      this.loading = false
      this.setNavBar(true)
    },
    async setAllCategories () {
      const { data } = await axios.get('/categories')
      console.log(data)
      this.setCategories(data)
    }
  },
  computed: {
  },
  mounted () {
    this.setNavBar(false)
  }
}
</script>
