import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    products: []
  },
  actions: {
    setProducts: ({ commit }, products) => {
      commit('setProducts', products)
    }
  },
  mutations: {
    setProducts: (state, products) => {
      state.products = products
    }
  },
  getters: {
    getProducts: (state) => {
      return state.products
    }
  }
})
