<!-- 订单 -->
<template>
    <div class="order">
        <div class="top"></div>
        <div class="order-region">
            <div class="order-item" v-for="item in list" :key="item.state">
                <div class="item-top">
                    <div class="fl order-num">订单：{{item.orderNum}}</div>
                    <div v-if="item.state===1" class="wait fr">待取餐</div>
                    <div v-if="item.state===2" class="complete fr">已完成</div>
                </div>
                <div class="item-info" v-for="infoItem in item.infos" :key="infoItem">
                    <div class="info-l"> <img src="../../assets/images/activity-banner.png" alt=""></div>
                    <div class="info-r">
                        <div class="info-r-info">
                            <p>一份好吃的沙拉</p>
                            <p>x 4</p>
                        </div>
                        <div class="money">￥48.00</div>
                    </div>
                </div>
                <div class="item-bottom">
                    <div class="fl gray">2018/10/19 18:00</div>
                    <div class="fr">
                        <span >合计：</span>
                        <span class="order-money">￥76.00</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            list: [
                {
                    orderNum: 20181024001,
                    state: 1,
                    infos: [1, 2]
                },
                {
                    orderNum: 20181024002,
                    state: 2,
                    infos: [1, 2]
                }
            ]
        }
    }
}
</script>

<style lang="less">
.wait {
    color: rgba(255, 194, 36, 1);
}

.complete {
    color: rgba(153, 153, 153, 1);
}
</style>
