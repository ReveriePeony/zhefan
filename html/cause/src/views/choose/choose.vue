<!-- 选餐 -->
<template>
    <div id="choose">
        <div class="goods">
            <div class="menu-wrapper" ref="menuWrappers">
                <ul>
                    <li class="menu-item" v-for="(item,index) in goods" :key="index" :class="{'current':currentIndex==index}" @click="selectMent(index,$event)">
                        <span> {{item.dishesClassName}}</span>
                    </li>
                </ul>
            </div>
            <div class="food-wrapper" ref="foodWrappers">
                <ul>
                    <li class="food-list-hook" v-for="(item,index) in goods" :key="index">
                        <h2> {{item.dishesClassName}}</h2>
                        <ul class="food-items">
                            <li class="food-item" v-for="(food,index1) in item.dishes" :key="index1">
                                <img :src="`${$baseUrl}:8111/${food.dishesImg}`" alt="" @click="clickItem(food)">
                                <h3>{{food.dishesName}}</h3>
                                <div class="Price-area clearfix">
                                    <span class="Price fl">{{food.dishesPrice|money}}</span>
                                    <Stepper class="fr" :num="food.num" :targetdata="food" :changefn="changefn"></Stepper>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <div class="shopcart">
            <div class="content clearfix">
                <div class="ball fl" @click="toggleList">
                    <i class="iconfont icon-gouwuche4"></i>
                </div>
                <span class="fl">共{{carDdata.Price|money}}</span>
                <div class="btn fr" @click="bookFn">去结算</div>
            </div>
            <div class="shopcart-list" ref="shopcartList">
                <div class="list-header clearfix">
                    <span class="fl">已选菜品（共{{carDdata.num}}份）</span>
                    <span class="fr" @click="emptyfn"> <i class="iconfont icon-gouwuche4"></i> 清空</span>
                </div>
                <div class="list-content">
                    <ul>
                        <li v-for="(item,index) in Selectgoods" :key="index" class="clearfix">
                            <span class="name fl">{{item.dishesName}}</span>
                            <div class="price  fl">
                                {{item.total|money}}
                            </div>
                            <Stepper class="fr" :num="item.foodNumber" :targetdata="item" :changefn="carchangefn"></Stepper>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
        <transition name="fade">
            <div class="list-mask" v-show="listShow" @click="toggleList"></div>
        </transition>
        <van-popup v-model="showItem" class="details">
            <img :src="`${$baseUrl}:8111/${detailsData.dishesImg}`" alt="">
            <div class="content">
                <h3>{{detailsData.dishesName}}</h3>
                <p>{{detailsData.dishesName}}</p>
                <div class="Price-area clearfix">
                    <span class="Price fl"><i>￥</i>{{detailsData.dishesPrice}}/份</span>
                    <Stepper class="fr" :num="detailsData.num" :targetdata="detailsData" :changefn="changefn"></Stepper>
                </div>
            </div>
        </van-popup>
    </div>
</template>

<script>
import BScroll from 'better-scroll'
import Stepper from 'commom/Stepper'
import { _mul } from '@/filters/index.js'
//获取菜单分类列表
export default {
    data() {
        return {
            listHeigth: [],
            goods: [],
            scorllY: 0,
            meunScroll: null,
            foodsScroll: null,
            listShow: false,
            Selectgoods: [],
            carDdata: {
                Price: 0,
                num: 0
            },
            showItem: false,
            detailsData: {
                name: '',
                price: 0
            }
        }
    },
    computed: {
        currentIndex() {
            for (let i = 0; i < this.listHeigth.length; i++) {
                let height1 = this.listHeigth[i]
                let height2 = this.listHeigth[i + 1]
                if (!height2 || (this.scorllY >= height1 && this.scorllY < height2)) {
                    return i
                }
            }
            return 0
        }
    },
    created: function() {
        this.$nextTick(() => {
            this._initScroll()
            this._calculateHeight()
        })
    },
    methods: {
        selectMent(index, e) {
            if (!e._constructed) {
                return false
            }
            let foodList = this.$refs.foodWrappers.getElementsByClassName('food-list-hook')
            let el = foodList[index]
            this.foodsScroll.scrollToElement(el, 300)
        },
        _initScroll: function() {
            this.meunScroll = new BScroll(this.$refs.menuWrappers, {
                // better-scroll 会将点击事件去掉，要在这里开启，同时点击在PC 会被执行两次，要在这里控制
                click: true
            })
            this.foodsScroll = new BScroll(this.$refs.foodWrappers, {
                // better-scroll 会将点击事件去掉，要在这里开启，同时点击在PC 会被执行两次，要在这里控制
                click: true,
                probeType: 3
            })
            this.foodsScroll.on('scroll', pos => {
                this.scorllY = Math.abs(Math.round(pos.y))
            })
        },
        _calculateHeight() {
            let foodList = this.$refs.foodWrappers.getElementsByClassName('food-list-hook')
            let height = 0
            this.listHeigth.push(height)
            for (let i = 0; i < foodList.length; i++) {
                let item = foodList[i]
                height += item.clientHeight
                this.listHeigth.push(height)
            }
        },
        toggleList() {
            if (this.carDdata.num != 0) {
                this.listShow = !this.listShow
                if (this.listShow) {
                    this.$refs.shopcartList.style.height = `${this.Selectgoods.length * 1.05 + 2.2}rem`
                } else {
                    this.$refs.shopcartList.style.height = 0
                }
            }
        },
        clickItem(item) {
            this.detailsData = item
            this.showItem = true
        },
        //去重
        preparation(arr, item) {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i].dishesId == item.dishesId) {
                    return i
                }
            }
            return -1
        },
        //数字改变的方法
        changefn(val, item) {
            // let total = _mul(val, item.dishesPrice)
            var newfood = {
                dishesId: item.dishesId,
                dishesName: item.dishesName,
                foodNumber: val,
                dishesTypeName: item.dishesClassName,
                dishesPrice: item.dishesPrice,
                dishesImg: item.dishesImg,
                gerentId: item.gerentId
                // total: total
            }
            var index = this.preparation(this.Selectgoods, newfood, true)
            if (index != -1) {
                this.Selectgoods[index].foodNumber = val
            } else if (val != 0) {
                this.Selectgoods.push(newfood)
            }
            this.goods.forEach(element => {
                var rule1 = this.preparation(element.dishes, item)
                if (rule1 != -1) {
                    element.dishes[rule1].num = val
                }
            })
            if (val == 0) {
                let spliceIndex = 0
                for (let j = 0; j < this.Selectgoods.length; j++) {
                    let item = this.Selectgoods[j]
                    if (item.dishesId == newfood.dishesId) {
                        spliceIndex = j
                    }
                }
                this.Selectgoods.splice(this.Selectgoods.indexOf(this.Selectgoods[spliceIndex]), 1)
            }
            this.countfn()
        },
        //改变购物车数字
        carchangefn(val, item) {
            item.foodNumber = val
            this.goods.forEach(element => {
                var rule1 = this.preparation(element.dishes, item)
                if (rule1 != -1) {
                    element.dishes[rule1].num = val
                }
            })
            if (val == 0) {
                let newSelectgoods = []
                for (let j = 0; j < this.Selectgoods.length; j++) {
                    let element = this.Selectgoods[j]
                    if (this.Selectgoods[j].foodNumber > 0) {
                        newSelectgoods.push(element)
                    }
                }
                this.Selectgoods = newSelectgoods
                // this.Selectgoods.splice(this.Selectgoods.indexOf(this.Selectgoods[spliceIndex]), 1)
            }
            this.countfn()
            if (this.carDdata.num == 0) {
                this.$refs.shopcartList.style.height = 0
                this.listShow = false
            } else {
                this.$refs.shopcartList.style.height = `${this.Selectgoods.length * 1.05 + 2.2}rem`
            }
        },
        //清空购物车
        emptyfn() {
            this.carDdata.num = 0
            this.carDdata.Price = 0
            this.Selectgoods = []
            this.goods.forEach(element => {
                element.dishes.forEach(item => {
                    item.num = 0
                })
            })
            this.$refs.shopcartList.style.height = 0
            this.listShow = false
        },
        //计算方法
        countfn() {
            this.carDdata.num = 0
            this.carDdata.Price = 0
            this.Selectgoods.forEach(function(item) {
                item.total = _mul(item.foodNumber, item.dishesPrice)
                this.carDdata.num += item.foodNumber
                this.carDdata.Price += item.foodNumber * item.dishesPrice
            }, this)
        },
        bookFn() {
            if (this.carDdata.num < 1) {
                return false
            }
            let data = {
                list: this.Selectgoods,
                carDdata: this.carDdata
            }
            this.$store.commit('ChangeOrder', data)
            this.$router.push('/submitPage')
        }
    },
    mounted() {
        this.$api
            .dishesListAll({
                shopId: this.shopId
            })
            .then(res => {
                if (res.code === 0) {
                    this.goods = res.data
                    this.goods.forEach(element => {
                        element.dishes.forEach(item => {
                            item.dishesClassName = element.dishesClassName
                        })
                    })
                }
            })
    },
    components: {
        Stepper
    }
}
</script>

<style lang="less">
.boxShadow() {
    box-shadow: 0px 7px 10px rgba(0, 0, 0, 0.06);
}

@yellow: #fbc326;
@gray: #333333;
// 点餐页面
#choose {
    .goods {
        display: flex;
        position: absolute;
        top: 0px;
        bottom: 0px;
        width: 100%;
        overflow: hidden;
    }
    .menu-wrapper {
        width: 1.6rem;
        background: #f5f5f5;
        > ul {
            padding-bottom: 1.5rem;
        }
    }
    .menu-item {
        border-left: 0.06rem solid #f5f5f5;
        box-sizing: border-box; // line-height: 0.88rem;
        font-size: 0.24rem;
        background: #f5f5f5;
        border-right: 1px solid #f5f5f5;
        padding: 0.25rem 0.1rem 0.25rem 0.25rem;
        line-height: 0.3rem;
        span {
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 2;
            overflow: hidden;
        }
        &.current {
            border-left: 0.06rem solid @yellow;
            background: #fff;
        }
    }
    .food-wrapper {
        background: #fff;
        flex: 1;
        > ul {
            padding: 0rem 0.2rem 1.5rem 0.2rem;
            li:first-child {
                h2 {
                    margin-top: 0;
                }
            }
        }
        h2 {
            padding: 0;
            border: none;
            line-height: 0.8rem;
        }
        .food-items {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
            padding-bottom: 0.2rem;
            .food-item {
                width: 2.68rem;
                padding-bottom: 0.2rem;
                img {
                    display: block;
                    width: 100%;
                    height: 1.8rem;
                }
                h3 {
                    line-height: 0.66rem;
                    white-space: nowrap;
                    overflow: hidden;
                    text-overflow: ellipsis;
                }
            }
        }
    }
    .shopcart {
        position: fixed;
        bottom: 0.3rem;
        left: 0.3rem;
        right: 0.3rem;
        z-index: 200;
        .content {
            height: 1rem;
            width: 100%;
            border-radius: 0.5rem;
            overflow: hidden;
            background: rgba(30, 30, 30, 1);
            color: #fff;
            .ball {
                margin: 0.1rem 0.35rem 0 0.1rem;
                width: 0.8rem;
                height: 0.8rem;
                background: linear-gradient(-30deg, rgba(242, 225, 104, 1), rgba(255, 179, 4, 1));
                border-radius: 0.5rem;
                font-size: 0;
                text-align: center;
                line-height: 0.8rem;
                i {
                    font-size: 0.45rem;
                    margin-right: 0.05rem;
                }
            }
            span {
                line-height: 1rem;
                font-size: 0.36rem;
                font-weight: bold;
            }
            .btn {
                width: 2.1rem;
                height: 1rem;
                background: linear-gradient(
                    -30deg,
                    rgba(255, 179, 4, 1),
                    rgba(242, 225, 104, 1)
                ); // background: linear-gradient(-30deg, rgba(242, 225, 104, 1), rgba(255, 179, 4, 1));
                border-radius: 0px 0.5rem 0.5rem 0px;
                font-size: 0.32rem;
                line-height: 1rem;
                text-align: center;
                font-weight: bold;
            }
        }
        .shopcart-list {
            position: absolute;
            overflow: hidden;
            bottom: 0;
            left: 0rem;
            right: 0rem;
            z-index: -1;
            background: #fff;
            border-radius: 0.5rem;
            height: 0;
            border-top-right-radius: 0.3rem;
            border-top-left-radius: 0.3rem;
            transition: all 0.5s;
            .list-header {
                line-height: 0.7rem;
                padding: 0 0.3rem;
                border-bottom: 1px solid #eeeff1;
                font-size: 0.24rem;
                color: #666666;
                i {
                    vertical-align: middle;
                }
            }
            .list-content {
                font-size: 0.28rem;
                padding: 0 0.3rem;
                li {
                    line-height: 1.05rem;
                    border-bottom: 1px solid #eeeff1;
                    .name {
                        width: 3.2rem;
                        white-space: nowrap;
                        overflow: hidden;
                        text-overflow: ellipsis;
                    }
                    .Stepper {
                        margin-top: 0.3rem;
                    }
                }
            }
        }
    }
    .Price {
        font-size: 0.3rem;
    }
}

.list-mask {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 40;
    backdrop-filter: blur(10px);
    opacity: 1;
    background: rgba(7, 17, 27, 0.6);
    &.fade-enter-active,
    &.fade-leave-active {
        transition: all 0.5s;
    }

    &.fade-enter,
    &.fade-leave-active {
        opacity: 0;
        background: rgba(7, 17, 27, 0);
    }
}
.details {
    border-radius: 0.1rem;
    img {
        width: 6.3rem;
        height: 4.2rem;
    }
    .content {
        padding: 0 0.3rem 0.3rem;
    }
    h3 {
        padding: 0.1rem 0;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
    }
    p {
        font-size: 0.26rem;
        color: rgba(153, 153, 153, 1);
        line-height: 0.32rem;
        margin-bottom: 0.2rem;
    }
}
</style>
