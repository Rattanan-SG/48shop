import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import ProductDetail from '@/components/ProductDetail'
import CategoryProduct from '@/components/CategoryProduct'
import SearchPage from '@/components/SearchPage'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/home',
      name: 'Home',
      component: Home
    },
    {
      path: '/product/:id',
      name: 'ProductDetail',
      component: ProductDetail,
      props: true
    },
    {
      path: '/product',
      name: 'CategoryProduct',
      component: CategoryProduct
    },
    {
      path: '/search',
      name: 'SearchProduct',
      component: SearchPage
    }
  ]
})
