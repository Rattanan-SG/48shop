// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})

var button =document.getElementById('button');
var modal =document.getElementById('page-modal');

var button2 =document.getElementById('button2');
var modal2 =document.getElementById('page-modal2');



  button.onclick=function(){
    modal.style.display='block';
  }

  button2.onclick=function(){
    modal2.style.display='block';
  }
  