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
        <v-btn color="primary">Add</v-btn>
      </v-card-actions>
    </v-card>
</template>
<script>
export default {
  name: 'productCard',
  props: ['product']
}
</script>
