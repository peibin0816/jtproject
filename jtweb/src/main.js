import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/ali-icon/iconfont.css'

/* 导入axios包 */
import axios from 'axios'
/* 设定axios的请求根目录 */
axios.defaults.baseURL = 'http://localhost:8091/'
/* 向vue对象中添加全局对象 以后发送ajax请求使用$http对象 */
Vue.prototype.$http = axios



Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
