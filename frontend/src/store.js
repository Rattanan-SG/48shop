import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    products: [],
    keyword: ''
  },
  actions: {
    setProducts: ({ commit }, products) => {
      commit('setProducts', products)
    },
    setKeyword: ({ commit }, keyword) => {
      commit('setKeyword', keyword)
    }
  },
  mutations: {
    setProducts: (state, products) => {
      state.products = products
    },
    setKeyword: (state, keyword) => {
      state.keyword = keyword
    }
  },
  getters: {
    getProducts: (state) => {
      return state.products
    },
    getKeyword: (state) => {
      return state.keyword
    }
  }
})
