<template>
  <v-app>
    <v-container>
      <v-layout row>
        <v-layout row style='height: 250px'>
          <v-flex md4>
            <v-layout column>
              <v-flex>
                <v-text-field
                  label="Name"
                  :rules='[rules.counter, rules.required]'
                  v-model='name'
                  counter='255'
                  required
                ></v-text-field>
              </v-flex>
              <v-flex>
                <v-textarea
                  label="Detail"
                  counter='255'
                  v-model='detail'
                  :rules='[rules.counter]'
                  required
                ></v-textarea>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex offset-md2 md6>
            <img v-bind:src='img_url' alt='image' style='height: 250px' v-show="img_url.length != 0"/>
          </v-flex>
        </v-layout>
      </v-layout>
      <v-layout>
        <v-flex md5>
          <v-text-field
            label="Image Url"
            v-model='img_url'
            :rules='[rules.required]'
            required
          ></v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md3>
          <v-text-field
            :rules='[rules.required, rules.number]'
            label="Price"
            v-model='price'
            required
          ></v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md3>
          <v-overflow-btn
            :items="getCategories"
            label="Category"
            target="#dropdown-example"
            v-model='category'
            placeholder='category'
            item-text='name'
            return-object
          ></v-overflow-btn>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex>
            <v-flex>
                <v-dialog v-model="dialog" persistent max-width="650">
                  <v-btn slot="activator" color="success" style='width: 157%' @click='message = ""'>
                    <v-icon>done_outline</v-icon>
                  </v-btn>
                  <v-card>
                    <v-container>
                      <v-layout row>
                        <v-flex md7>
                          <v-card-title class="headline">
                            Add Product Confirmation
                            <v-progress-circular
                            :indeterminate='loading'
                            v-show='loading'
                            color="primary"
                          ></v-progress-circular>
                          </v-card-title>
                          <v-card-text>Name: {{this.name}} </v-card-text>
                          <v-card-text>Detail: {{this.detail}}</v-card-text>
                          <v-card-text>Price: {{this.price}}</v-card-text>
                          <v-card-text>Category: {{this.category.name}}</v-card-text>
                        </v-flex>
                        <v-flex md5 align-center justify-center>
                          <img v-bind:src='img_url' alt='image' style='height: 200px' v-show="img_url.length != 0"  />
                        </v-flex>
                      </v-layout>
                    </v-container>
                    <v-card-actions>
                      <v-alert
                        :value="message.length > 0"
                        type="error"
                      >
                        {{message}}
                      </v-alert>
                      <v-spacer></v-spacer>
                      <v-btn @click="dialog = false">Disagree</v-btn>
                      <v-btn color="success" @click='addProduct'>Confirm</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
            </v-flex>
        </v-flex>
      </v-layout>
    </v-container>
  </v-app>
</template>
<script>
import { mapGetters } from 'vuex'
import axios from '@/utils/axios'
export default {
  name: 'addProduct',
  data: () => ({
    name: '',
    detail: '',
    img_url: '',
    price: '',
    category: {},
    categories: [],
    dialog: false,
    message: '',
    loading: false,
    rules: {
      required: value => !!value || 'Required.',
      counter: value => (value) ? value.length <= 255 || 'Max 255 characters' : '',
      number: value => {
        const pattern = /(\d+\.)|(\d)/
        return pattern.test(value) || 'Invalid number'
      }
    }
  }),
  computed: {
    ...mapGetters([
      'getCategories'
    ])
  },
  methods: {
    async addProduct () {
      if (this.validateData()) {
        this.loading = true
        const { data } = await axios.post('/product', {
          name: this.name,
          detail: this.detail,
          img_url: this.img_url,
          price: this.price,
          category: this.category
        })
        console.log(data)
        this.dialog = false
        this.name = ''
        this.detail = ''
        this.img_url = ''
        this.price = ''
        this.category = ''
        this.message = ''
        this.loading = false
      } else {
        this.message = 'please fill the form correctly'
      }
    },
    validateData () {
      if (
        this.name.length > 0 &&
        this.detail.length > 0 &&
        parseFloat(this.price) >= 0 &&
        this.category
      ) {
        return true
      } else {
        return false
      }
    }
  }
}
</script>
