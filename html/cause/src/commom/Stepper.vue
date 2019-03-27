<template>
    <div class="Stepper">
        <!-- {{food}} -->
        <!-- <span class="minus" v-if="genre"></span>
        <span class="num" v-if="genre">{{num}}</span>
        <span class="add" @click="add"></span> -->
        <span class="minus" @click="minusNum"></span>
        <span class="num">{{numdata}}</span>
        <span class="add" @click="addNum"></span>
    </div>
</template>

<script type='text/ecmascript-6'>
export default {
    data() {
        return {
            numdata: this.num,
            delayTime: true
        }
    },
    // props: {
    //     genre: {
    //         type: String
    //     },
    //     size: {
    //         type: String
    //     },
    //     food: {
    //         type: Object,
    //         requied: true
    //     }
    // },
    methods: {
        minusNum() {
            var _this = this
            if (!this.delayTime) {
                return false
            }
            this.delayTime = false
            if (this.minnum && this.numdata <= this.minnum) {
                // this.Toast({
                //     message: '小于预定数量',
                //     duration: 2000
                // })
                console.log('小于预定数量')
                setTimeout(() => {
                    _this.delayTime = true
                }, 100)
                return false
            } else {
                if (this.numdata <= 0) {
                    setTimeout(() => {
                        _this.delayTime = true
                    }, 100)
                    return false
                }
            }
            this.numdata--
            if (this.changefn) {
                this.changefn(this.numdata, this.targetdata, true)
            }
            setTimeout(() => {
                _this.delayTime = true
            }, 100)
        },
        addNum() {
            var _this = this
            if (!this.delayTime) {
                return false
            }
            this.delayTime = false
            if (this.maxnum && this.numdata >= this.maxnum) {
                // this.Toast({
                //     message: '超出预定数量',
                //     duration: 2000
                // })
                console.log('超出预定数量')
                setTimeout(() => {
                    _this.delayTime = true
                }, 100)
                return false
            }
            this.numdata++
            if (this.changefn) {
                this.changefn(this.numdata, this.targetdata, false)
            }
            setTimeout(() => {
                _this.delayTime = true
            }, 100)
        }
    },
    props: ['num', 'minnum', 'maxnum', 'changefn', 'targetdata'],
    watch: {
        num: function() {
            this.numdata = this.num
        }
    },
    mounted() {
        if (!this.num) {
            this.numdata = 0
        }
    }
}
</script>

<style  lang='less' rel='stylesheet/less'>
.Stepper {
    display: inline-block;
    font-size: 0;
    line-height: 0.4rem;
    span {
        display: inline-block;
        font-size: 0.28rem;
        vertical-align: top;
    }
    .minus,
    .add {
        width: 0.4rem;
        height: 0.4rem;
        border-radius: 50%;
        position: relative;
        &::after,
        &::before {
            content: '';
            position: absolute;
            margin: auto;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background-color: #fff;
        }
        &::before {
            width: 0.24rem;
            height: 0.04rem;
        }
        &::after {
            width: 0.04rem;
            height: 0.24rem;
        }
    }
    .minus {
        border: 1px solid #d2d2d2;
        &::before {
            background-color: #999999;
        }
        &::after {
            display: none;
        }
    }
    .add {
        background: linear-gradient(-30deg, rgba(242, 225, 104, 1), rgba(255, 179, 4, 1));
    }
    .num {
        width: 0.5rem;
        text-align: center;
        height: 0.5rem;
    }
}
</style>
