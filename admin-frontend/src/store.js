import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navbar: false,
    recommendProducts: [],
    products: [],
    productUnRecommend: []
  },
  mutations: {
    setNavBar: (state, show) => {
      state.navbar = show
    },
    setRecommendProducts: (state, product) => {
      state.recommendProducts.push(product)
    },
    setProducts: (state, products) => {
      state.products = products
    },
    setProductUnRecommend: (state, products) => {
      state.productUnRecommend = products
    }
  },
  actions: {
    setNavBar: ({ commit }, show) => {
      commit('setNavBar', show)
    },
    setRecommendProducts: ({ commit }, product) => {
      commit('setRecommendProducts', product)
    },
    setProducts: ({ commit }, products) => {
      commit('setProducts', products)
    },
    setProductUnRecommend: ({ commit }, products) => {
      commit('setProductUnRecommend', products)
    }
  },
  getters: {
    getNavBar: (state) => {
      return state.navbar
    },
    getRecommendProducts: (state) => {
      return state.recommendProducts
    },
    getProducts: (state) => {
      return state.products
    },
    getProductUnRecommend: (state) => {
      return state.productUnRecommend
    }
  }
})
