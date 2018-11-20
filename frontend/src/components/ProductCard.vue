<template>
  <div class="columns box">
    <div class="column is-3">
      <img :src='product.img_url' width="100"/>
    </div>
    <div class="column" >
      <div class="columns" style="height: 50px">
        <div class="column">
          <h2>
            <b>{{formatDetail(product.name)}}</b>
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
          <h2>
            {{addCommas(product.price)}} ฿
          </h2>
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
    },
    addCommas (nStr) {
      nStr += '';
      let x = nStr.split('.');
      let x1 = x[0];
      let x2 = x.length > 1 ? '.' + x[1] : '';
      var rgx = /(\d+)(\d{3})/;
      while (rgx.test(x1)) {
        x1 = x1.replace(rgx, '$1' + ',' + '$2');
      }
      return x1 + x2;
    }
  }
}
</script>
