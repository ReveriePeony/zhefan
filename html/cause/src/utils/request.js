import URL from './urls'
import $http from './http'
export default {
    /**@name 菜品列表 All All */
    dishesListAll(params) {
        return $http.http('get', URL.GET_listAll, params)
    },
    /**@name 下单 */
    orderBook(params) {
        return $http.http('post', URL.orderBook, params)
    },
    /**@name 列表 */
    List(params) {
        return $http.http('get', URL.List, params)
    },
    /**@name 微信登录 */
    wxAuth(params) {
        return $http.http('get', URL.wxAuth, params)
    }
}