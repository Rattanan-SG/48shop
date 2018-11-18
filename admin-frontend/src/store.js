import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navbar: false,
    recommendProducts: [],
    products: []
  },
  mutations: {
    setNavBar: (state, show) => {
      state.navbar = show
    },
    setRecommendProducts: (state, product) => {
      let tempRecommendProducts = state.recommendProducts.push(product)
      state.recommendProducts = tempRecommendProducts
    },
    setProducts: (state, products) => {
      state.products = products
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
    }
  }
})
