import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import states from '@/store/computed'
import MintUI from 'mint-ui'
import VueAwesomeSwiper from 'vue-awesome-swiper'
import Vant from 'vant'
import api from './utils/request'
import 'vant/lib/vant-css/index.css'
import 'swiper/dist/css/swiper.css'
import 'mint-ui/lib/style.css'
import './assets/css/main.less'
import * as filters from './filters'
// 全局混入
Vue.mixin({
    computed: states
})
Vue.config.productionTip = false
Vue.use(Vant)
Vue.use(VueAwesomeSwiper /* { default global options } */ )
Vue.prototype.$api = api //全局定义api请求的方法
Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key])
})
Vue.use(MintUI)

/* eslint-disable */
document.getElementsByTagName('html')[0].style.fontSize =
    document.getElementsByTagName('html')[0].offsetWidth / 7.5 + 'px'
window.onresize = function() {
    document.getElementsByTagName('html')[0].style.fontSize =
        document.getElementsByTagName('html')[0].offsetWidth / 7.5 + 'px'
}
new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app')