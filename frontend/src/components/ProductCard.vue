<template>
  <router-link to="/ProductDetail">
    <div class="card">
      <div class="field is-grouped" id="img">
        <div class="card-image">
          <figure class="image " >
            <img src='https://versions.bulma.io/0.7.0/images/placeholders/1280x960.png' alt="Placeholder image">
          </figure>
        </div>
      </div>
      <div class="field is-grouped" id="content" >
        <div class="card-content" >
          <div class="field is-grouped">
            <div class="content">
              <p>iPhone 8</p>
            </div>
          </div>
          <div class="field is-grouped" id="price" >
            <div class="content">
              <p>1x,xxx</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </router-link>
</template>
<script>
export default {
  name: 'addProduct'
}
</script>
