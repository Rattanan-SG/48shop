import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    navbar: false
  },
  mutations: {
    setNavBar: (state, show) => {
      state.navbar = show
    }
  },
  actions: {
    setNavBar: ({ commit }, show) => {
      commit('setNavBar', show)
    }
  },
  getters: {
    getNavBar: (state) => {
      return state.navbar
    }
  }
})
