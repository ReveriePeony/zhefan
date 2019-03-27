'use strict'
import axios from 'axios'
import Vue from 'vue'
// axios 配置
// 配置API接口地址
let root = 'http://www.huzhenquan.com'
    // axios.defaults.baseURL = root
axios.defaults.baseURL = root;
// 响应时间
axios.defaults.timeout = 10000
    // `withCredentails`选项表明了是否是跨域请求
axios.defaults.withCredentials = false
Vue.prototype.$baseUrl = axios.defaults.baseURL;
// 设置默认请求头
axios.defaults.headers = {
    'X-Requested-With': 'XMLHttpRequest',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
    'Access-Control-Allow-Credentials': 'true'
}
Vue.prototype.$baseUrl = axios.defaults.baseURL
    // 添加请求拦截器
axios.interceptors.request.use(
        config => {
            return config
        },
        error => {
            return Promise.reject(error)
        }
    )
    // 响应时拦截
axios.interceptors.response.use(
    function(response) {
        // if()
        console.log(response.data.code)
        if (response.data.code === 1020) {
            window.location.href = `${root}/mobile/wx/h5auth?url=${window.location.href}`
        } else {
            return response.data
        }

    },
    function(error) {
        return Promise.reject(error.response.data)
    }
)

export default {
    http(method, url, params) {

        return axios({
                method,
                url,
                params: method === 'get' && params,
                data: method !== 'get' && params
            })
            .then(res => {
                return res
            })
            .catch(err => {
                return err
            })
    },
    all(fn1, fn2) {
        return axios
            .all([fn1(), fn2()])
            .then(
                axios.spread(function(acct, perms) {
                    return [acct, perms]
                })
            )
            .catch(err => {
                return err
            })
    }
}