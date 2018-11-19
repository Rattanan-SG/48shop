import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navbar: false,
    defaultProducts: [],
    products: []
  },
  mutations: {
    setNavBar: (state, show) => {
      state.navbar = show
    },
    setDefaultProducts: (state, products) => {
      state.defaultProducts = products
    },
    setProducts: (state, products) => {
      state.products = products
    }
  },
  actions: {
    setNavBar: ({ commit }, show) => {
      commit('setNavBar', show)
    },
    setDefaultProducts: ({ commit }, products) => {
      commit('setDefaultProducts', products)
    },
    setProducts: ({ commit }, products) => {
      commit('setProducts', products)
    }
  },
  getters: {
    getNavBar: (state) => {
      return state.navbar
    },
    getDefaultProducts: (state) => {
      return state.defaultProducts
    },
    getProducts: (state) => {
      return state.products
    }
  }
})
