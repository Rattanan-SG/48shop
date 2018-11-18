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
                  counter='255'
                  required
                ></v-text-field>
              </v-flex>
              <v-flex>
                <v-textarea
                  label="Detail"
                  counter='255'
                  :rules='[rules.counter]'
                  required
                ></v-textarea>
              </v-flex>
            </v-layout>
          </v-flex>
          <v-flex offset-md2 md6>
            <img v-bind:src='imageUrl' alt='image' style='height: 250px' v-show="imageUrl.length != 0"/>
          </v-flex>
        </v-layout>
      </v-layout>
      <v-layout>
        <v-flex md5>
          <v-text-field
            label="Image Url"
            v-model='imageUrl'
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
            required
          ></v-text-field>
        </v-flex>
      </v-layout>
      <v-layout>
        <v-flex md3>
          <v-overflow-btn
            :items="categoryList"
            label="Category"
            target="#dropdown-example"
          ></v-overflow-btn>
        </v-flex>
        <v-flex offset-md6 md3>
          <v-btn
            color='success'
          >
            ADD
          </v-btn>
        </v-flex>

      </v-layout>

    </v-container>
  </v-app>
</template>
<script>
export default {
  name: 'addProduct',
  data: () => ({
    categoryList: [
      'cosmetic',
      'device'
    ],
    imageUrl: '',
    rules: {
      required: value => !!value || 'Required.',
      counter: value => (value) ? value.length <= 255 || 'Max 255 characters' : null,
      number: value => {
        const pattern = /(\d+\.)|(\d)/
        return pattern.test(value) || 'Invalid number'
      }
    }
  })
}
</script>
