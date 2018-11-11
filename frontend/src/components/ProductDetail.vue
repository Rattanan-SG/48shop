<template>
  <div class="ProductDetail">
    <div class="container" id="home-box">
      <h1>{{ msg }}</h1>
      <div class="columns">
        <div class="column is-one-fifth">
            <img src="./../assets/item1.png" alt="Placeholder image">
            item1
        </div>
        <div class="column">
            <div class="card">
                <div class="card-header">
                    ITEM1
                </div>
                <div class="card-content">
                    kgkdfghdfjghdglghdfd;gjsg
                </div>
                <div class="card-footer">
                    <router-link to="/Home"><div class="button">Back</div></router-link>
                    <router-link to="/ProductDetail"><div class="button" disabled>Buy now</div></router-link>
                </div>
            </div>
          </div>
      </div>
    </div>
    <div class="navbar is-fixed-bottom" id="nav-bot">
        <div class="container" id="nav-bot-box">
            เพิ่มเครดิต
            <div class="button">เพิ่ม</div>
        </div>
      
    </div>
  </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';

export default {
  name: 'ProductDetail',
  data () {
    return {
      msg: 'This is Detail page'
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#nav-bot {
    height: 240px;
}

#nav-bot-box {
    width: 1000px;
}

#nav-top {
  border-bottom: 10px;
  border-color: #E0E0E0;
}

#nav-box {
  width: 1000px;
}

#home-box {
  background: white;
  width: 1000px;
  min-height: 2000px;
  margin-top: 13px;
  box-shadow: 2px 10px grey;
}
</style>
