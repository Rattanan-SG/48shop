import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login.vue'
import AddProduct from './views/AddProduct.vue'
import DeleteProduct from './views/DeleteProduct'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Login
    },
    {
      path: '/add-product',
      name: 'addProduct',
      component: AddProduct
    },
    {
      path: '/delete-product',
      name: 'deleteProduct',
      component: DeleteProduct
    }

  ]
})
