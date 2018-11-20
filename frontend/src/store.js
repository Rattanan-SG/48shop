import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    products: [],
    keyword: '',
    orderId: ''
  },
  actions: {
    setProducts: ({ commit }, products) => {
      commit('setProducts', products)
    },
    setKeyword: ({ commit }, keyword) => {
      commit('setKeyword', keyword)
    },
    setOrderId: ({ commit }, id) => {
      commit('setOrderId', id)
    }
  },
  mutations: {
    setProducts: (state, products) => {
      state.products = products
    },
    setKeyword: (state, keyword) => {
      state.keyword = keyword
    },
    setOrderId: (state, id) => {
      state.orderId = id
    }
  },
  getters: {
    getProducts: (state) => {
      return state.products
    },
    getKeyword: (state) => {
      return state.keyword
    },
    getOrderId: (state) => {
      return state.orderId
    }
  }
})
