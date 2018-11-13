import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import ProductDetail from '@/components/ProductDetail'
import CategoryProduct from '@/components/CategoryProduct'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/Home',
      name: 'Home',
      component: Home
    },
    {
      path: '/ProductDetail',
      name: 'ProductDetail',
      component: ProductDetail
    },
    {
      path: '/CategoryProduct',
      name: 'CategoryProduct',
      component: CategoryProduct
    }
  ]
})
