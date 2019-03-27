import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/home/home'
import Choose from '@/views/choose/choose'
import My from '@/views/my/my'
import Order from '@/views/order/order'
import submitPage from '@/views/submitPage/submitPage'
Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: '首页',
            component: Home
        },
        {
            path: '/choose',
            name: '选餐',
            component: Choose
        },

        {
            path: '/my',
            name: '我的',
            component: My
        },
        {
            path: '/order',
            name: '订单',
            component: Order
        },
        {
            path: '/submitPage',
            name: '确认订单',
            component: submitPage
        },
        {
            path: '*',
            redirect: () => {
                return '/'
            }
        }
    ]
})
