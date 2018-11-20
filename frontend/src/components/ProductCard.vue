<template>
  <div class="columns box">
    <div class="column is-3">
      <img :src='product.img_url' width="100"/>
    </div>
    <div class="column" >
      <div class="columns" style="height: 50px">
        <div class="column">
          <h2>
            {{formatDetail(product.name)}}
          </h2>
        </div>
      </div>
      <div class="columns" style="height: 50px">
        <div class="column">
          <p style="color: grey">
            {{formatDetail(product.detail)}}
          </p>
        </div>
      </div>
      <div class="columns">
        <div class="column is-offset-8 is-4">
          <h3>
            {{product.price}} ฿
          </h3>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'addProduct',
  props: [
    'product'
  ],
  methods: {
    formatDetail (detail) {
      if (detail.length > 50) {
        return detail.substring(0, 50) + '...'
      }
      return detail
    }
  }
}
</script>
