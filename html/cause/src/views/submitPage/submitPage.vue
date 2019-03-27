<template>
    <div class="submitPage">
        <div class="top">
        </div>
        <div class="submitPage-region">
            <div class="items">
                <div class="item clearfix" v-for="item in orderData.list" :key="item.dishesId">
                    <img class="fl" :src="`${$baseUrl}:8111/${item.dishesImg}`" alt="">
                    <div class="food-item-right">
                        <h3>{{item.dishesName}}</h3>
                        <div class="Price-area clearfix">
                            <div class="fl">
                                <span class="Price">{{item.dishesPrice|money}}/份</span> <br>
                                <span class="num">×{{item.foodNumber}}</span>
                            </div>
                            <div class="fr">
                                <span class="Price totalItem">{{item.total|money}}</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="Total">
                共 {{orderData.carDdata.num}} 件商品
                <span class="fr">合计：<strong>{{orderData.carDdata.Price|money}}</strong></span>
            </div>
        </div>
        <div class="Remarks-region">
            <h5>菜品备注</h5>
            <div class="Remarks-box">
                <textarea v-model="value" placeholder="请输入菜品备注"></textarea>
            </div>
        </div>
        <div class="submitBtn" @click="submitFn">提交订单</div>
    </div>
</template>

<script type='text/ecmascript-6'>
import Stepper from 'commom/Stepper'
export default {
    data() {
        return {
            value: ''
        }
    },
    components: {
        Stepper
    },
    mounted() {
        console.log(this.orderData)
    },
    methods: {
        submitFn() {
            let params = {
                book: '测试',
                details: [],
                shopId: this.shopId,
                tableName: this.tableName,
                price: this.orderData.carDdata.Price
            }
            this.orderData.list.forEach(element => {
                params.gerentId = element.gerentId
                params.details.push({
                    count: element.foodNumber,
                    dishesName: element.dishesName,
                    dishesTypeName: element.dishesTypeName,
                    price: element.dishesPrice
                })
            })
            console.log(params)
            this.$api.orderBook(params).then(res => {
                if (res.code === 0) {
                    // this.goods = res.data
                    // this.goods.forEach(element => {
                    //     element.dishes.forEach(item => {
                    //         item.dishesClassName = element.dishesClassName
                    //     })
                    // })
                }
            })
        }
    }
}
</script>

<style  lang='less' rel='stylesheet/less'>
@yellow: #fbc326;
.submitPage {
    padding-bottom: 1.5rem;
    .top {
        height: 1rem;
        background: rgba(255, 220, 25, 1);
    }
    .submitPage-region {
        margin: 0 0.2rem;
        margin-top: -0.8rem;
        background: #fff;
        border-radius: 0.1rem;
    }
    .items {
        border-radius: 0.1rem;
        padding: 0 0.3rem;
        .item {
            padding-top: 0.35rem;
        }
        .food-item-right {
            border-bottom: 1px solid #eeeff1;
            overflow: hidden;
            padding-bottom: 0.1rem;
            .Price-area {
                font-size: 0;
                line-height: 1;
                span {
                    font-size: 0.3rem;
                    color: #8e8e8e;
                }
                .num {
                    display: inline-block;
                    margin-top: 0.1rem;
                }
            }
            .totalItem {
                display: inline-block;
                padding-top: 0.2rem;
                font-size: 0.34rem !important;
                color: #333333 !important;
            }
        }
        img {
            width: 1.4rem;
            height: 1.4rem;
            margin-right: 0.3rem;
        }
        h3 {
            font-size: 0.32rem;
            font-weight: 400;
            color: rgba(51, 51, 51, 1);
            line-height: 0.32rem;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
            padding-bottom: 0.24rem;
        }
    }
    .submitBtn {
        position: fixed;
        bottom: 0.3rem;
        left: 0.3rem;
        right: 0.3rem;
        z-index: 200;
        height: 1rem;
        line-height: 1rem;
        border-radius: 0.5rem;
        overflow: hidden;
        color: #fff;
        font-weight: bold;
        text-align: center;
        font-size: 0.34rem;
        background: linear-gradient(0deg, rgba(242, 225, 104, 1), rgba(255, 179, 4, 1));
    }
    .Total {
        line-height: 0.9rem;
        font-size: 0.28rem;
        color: #666666;
        padding: 0 0.3rem;
        .fr {
            color: #333333;
        }
        strong {
            font-size: 0.36rem;
            font-weight: 700;
            color: @yellow;
        }
    }
    .Remarks-region {
        margin: 0.2rem 0.2rem 0;
        background: #fff;
        border-radius: 0.1rem;
        font-size: 0;
        h5 {
            font-size: 0.28rem;
            color: #333333;
            line-height: 0.9rem;
            padding-left: 0.3rem;
            border-bottom: 1px solid #f7f7f7;
            font-weight: normal;
        }
        .Remarks-box {
            padding: 0.3rem;
        }
        textarea {
            font-size: 0.28rem;
            resize: none;
            width: 100%;
            border: none;
            appearance: none;
            border-radius: 0;
            border: 0;
            outline: 0;
            line-height: 1.6;
        }
    }
}
</style>
