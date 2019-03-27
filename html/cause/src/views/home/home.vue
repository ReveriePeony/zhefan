<!-- 首页 -->
<template>
    <div id="home">
        <div class="banner-area">
            <div class="banner">
                <mt-swipe @change="handleChange" :auto="0" :showIndicators="false">
                    <mt-swipe-item><img src="../../assets/images/banner.png" alt=""></mt-swipe-item>
                    <mt-swipe-item><img src="../../assets/images/banner.png" alt=""></mt-swipe-item>
                    <mt-swipe-item><img src="../../assets/images/banner.png" alt=""></mt-swipe-item>
                </mt-swipe>
                <div class="swipe-num">
                    {{swipeActive}}/3
                </div>
            </div>
        </div>
        <div class="ability-area">
            <div class="item" @click="choose">
                <i class="iconfont icon-unie63e"></i>
                <span>点餐</span>
            </div>
            <div class="item">
                <i class="iconfont icon-lingdang-fill"></i>
                <span>呼叫服务</span>
            </div>
            <div class="item">
                <i class="iconfont icon-huodong"></i>
                <span>优惠活动</span>
            </div>
            <div class="item">
                <i class="iconfont icon-wode4"></i>
                <span>我的</span>
            </div>
        </div>
        <!-- <div class="activity-area">
            <h2>最新活动</h2>
            <swiper :options="swiperOption" ref="mySwiper">
                <swiper-slide>
                    <div class="activity-item clearfix">
                        <img class="fl" src="../../assets/images/activity-banner.png" alt="">
                        <div class="activity-item-right">
                            <h3>这里是商品名称显示区域，不够拷贝不够不够拷贝不够不够 拷贝不够</h3>
                            <div class="Price-area">
                                <span class="Price"><i>￥</i>15.00/份</span>
                                <span class="oldPrice">￥300</span>
                            </div>
                            <div class="clearfix">
                                <span class="Sold fl">已售：1份</span>
                                <span class="scareBuying fr">马上抢购</span>
                            </div>
                        </div>
                    </div>
                </swiper-slide>
                <swiper-slide>
                    <div class="activity-item clearfix">
                        <img class="fl" src="../../assets/images/activity-banner.png" alt="">
                        <div class="activity-item-right">
                            <h3>这里是商品名称显示区域，不够... 拷贝不够</h3>
                            <div class="Price-area">
                                <span class="Price"><i>￥</i>15.00/份</span>
                                <span class="oldPrice">￥300</span>
                            </div>
                            <div class="clearfix">
                                <span class="Sold fl">已售：1份</span>
                                <span class="scareBuying fr">马上抢购</span>
                            </div>
                        </div>
                    </div>
                </swiper-slide>
                <swiper-slide>
                    <div class="activity-item clearfix">
                        <img class="fl" src="../../assets/images/activity-banner.png" alt="">
                        <div class="activity-item-right">
                            <h3>这里是商品名称显示区域，不够... 拷贝不够</h3>
                            <div class="Price-area">
                                <span class="Price"><i>￥</i>15.00/份</span>
                                <span class="oldPrice">￥300</span>
                            </div>
                            <div class="clearfix">
                                <span class="Sold fl">已售：1份</span>
                                <span class="scareBuying fr">马上抢购</span>
                            </div>
                        </div>
                    </div>
                </swiper-slide>
                <swiper-slide>
                    <div class="activity-item clearfix">
                        <img class="fl" src="../../assets/images/activity-banner.png" alt="">
                        <div class="activity-item-right">
                            <h3>这里是商品名称显示区域，不够... 拷贝不够</h3>
                            <div class="Price-area">
                                <span class="Price"><i>￥</i>15.00/份</span>
                                <span class="oldPrice">￥300</span>
                            </div>
                            <div class="clearfix">
                                <span class="Sold fl">已售：1份</span>
                                <span class="scareBuying fr">马上抢购</span>
                            </div>
                        </div>
                    </div>
                </swiper-slide>
            </swiper>
        </div> -->
        <div class="groom-area">
            <h2>厨师推荐</h2>
            <div class="food-item clearfix" v-for="item in groomList" :key="item.id">
                <img class="fl" :src="`${$baseUrl}:8111/${item.dishesImg}`" alt="">
                <div class="food-item-right">
                    <h3>{{item.dishesName}}</h3>
                    <p v-if="item.remark">{{item.remark}}</p>
                    <p v-else>暂无描述</p>
                    <div class="Price-area clearfix">
                        <span class="Price fl"><i>￥</i>{{item.dishesPrice}}/份</span>
                        <Stepper class="fr"></Stepper>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Stepper from 'commom/Stepper'
export default {
    name: 'home',
    data() {
        return {
            swipeActive: 1,
            swiperOption: {
                slidesPerView: 'auto',
                spaceBetween: 10
            },
            groomList: []
        }
    },
    computed: {
        swiper() {
            return this.$refs.mySwiper.swiper
        }
    },
    methods: {
        handleChange(index) {
            this.swipeActive = index + 1
        },
        //跳转点餐
        choose() {
            this.$router.push({
                path: '/choose'
            })
        },
        getGroom() {
            this.$api
                .List({
                    shopId: this.shopId,
                    recommend: 1,
                    status: 1,
                    soldOut: 0,
                    pageSize: 99
                })
                .then(res => {
                    if (res.code === 0) {
                        // this.goods = res.data
                        // this.goods.forEach(element => {
                        //     element.dishes.forEach(item => {
                        //         item.dishesClassName = element.dishesClassName
                        //     })
                        // })
                        this.groomList = res.data.records
                    }
                })
        }
    },
    mounted() {
        // current swiper instance
        // 然后你就可以使用当前上下文内的swiper对象去做你想做的事了
        // console.log('this is current swiper instance object', this.swiper)
        // this.swiper.slideTo(1000, false)
        this.getGroom()
        console.log()
        // window.location.href = `http://47.107.248.96/mobile/wx/h5auth?url=${window.location.href}`
        // this.$api.wxAuth().then(res => {
        //     console.log(res)
        //     if (res.code === 0) {
        //         // this.goods = res.data
        //         // this.goods.forEach(element => {
        //         //     element.dishes.forEach(item => {
        //         //         item.dishesClassName = element.dishesClassName
        //         //     })
        //         // })
        //         // this.groomList = res.data.records
        //     }
        // })
    },
    components: {
        Stepper
    }
}
</script>
