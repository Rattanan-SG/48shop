<template>
  <div class="hello">
    <div class="container" id="home-box">
      <h1>{{ msg }}</h1>
        <carousel>
          <carousel-slide>
            <div class='carousel carousel-animated carousel-animate-slide' data-size="5">
  <div class='carousel-container'>
    <div class='carousel-item is-active'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
    <div class='carousel-item'>
      <figure class="image is-2by1"><img src="https://bulma.io/images/placeholders/640x320.png"></figure>
    </div>
  </div>
  <div class="carousel-navigation is-centered">
    <div class="carousel-nav-left">
      <i class="fa fa-chevron-left" aria-hidden="true"></i>
    </div>
    <div class="carousel-nav-right">
      <i class="fa fa-chevron-right" aria-hidden="true"></i>
    </div>
  </div>
</div>
          </carousel-slide>
        </carousel>
        
    
    </div>
  </div>
</template>

<script>
import './../../node_modules/bulma/css/bulma.css';
import Carousel from './carousel/Carousel';
import CarouselSlide from './carousel/CarouselSlide';



export default {
  name: 'Home',
  data () {
    return {
      
      msg: 'หน้าแรก สำหรับแสดงสินค้า'
    }
  },
  components:{
    Carousel,
    CarouselSlide
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
nav {
  border-bottom: 10px;
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
