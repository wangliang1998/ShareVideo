import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    username: "",
    icon:""
  },
  mutations: {
    SET_USERNAME: (state, username) => {
      state.username = username
      localStorage.setItem('username', username)
    },
    REMOVE_USERNAME: (state) => {
      state.username = ''
      localStorage.setItem('username', '')
    },
    SET_ICON: (state, icon) => {
      state.icon = icon
      localStorage.setItem('icon', icon)
    },
    REMOVE_ICON: (state) => {
      state.icon = ''
      localStorage.setItem('icon', '')
    }
  },
  getters:{
    getUser: state => {
      return state.username
    },
    getIcon: state => {
      return state.icon
    }
  },
  actions: {
  },
  modules: {
  }
})
