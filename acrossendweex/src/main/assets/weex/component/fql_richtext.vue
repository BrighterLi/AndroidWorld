<template>
    <div class="content-text-wrap">
        <richtext @click="showContentDetail" class="content-text" ref="richtext">
            <span class="content-text">{{richtext}}</span>
            <span>...</span>
            <image class="space-block"></image>
            <span class="content-text-expansion">展开</span>
        </richtext>

    </div>
</template>

<style>
    .content-text-wrap {
        width: 750px;
        padding-top: 24px;
        padding-bottom: 30px;
        padding-left: 32px;
        padding-right: 32px;
        position: relative;
    }
    .content-text {
        line-height: 52px;
        font-family: PingFangSC-Regular;
        font-size: 28px;
        color: #000;
    }
    .content-text-expansion {
        line-height: 52px;
        font-family: PingFangSC-Medium;
        font-weight: 500;
        font-size: 28px;
        color: #95999F;
    }
    .space-block {
        display: inline-block;
        width: 10px;
        height: 0;
    }
</style>

<script>
    module.exports = {
        data () {
            return {
                isShowDetail: true,
                richtext: '是真的香啊！！！冲鸭朋友们！\n已经弃了我的xsmax\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得金色也不错，实物拿到手之后发现没选错！是很淡的金色，侧面有点像香槟金，很高级的感觉。根本就不是好多人说的黄金那种颜色啊！\n又配了一个橙色的手机壳和充电头。。\n自己买了一个ideal的手机壳，绿色流沙这个真的太美了！！',
                richtext1: '是真的香啊！！！冲鸭朋友们！\n已经弃了我的xsmax\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得金色也不错，实物拿到手之后发现没选错！是很淡的金色，侧面有点像香槟金，很高级的感觉。根本就不是好多人说的黄金那种颜色啊！\n又配了一个橙色的手机壳和充电头。。\n自己买了一个ideal的手机壳，绿色流沙这个真的太美了！！',
                richtext2: '是真的香啊！！！冲鸭朋友们！\n已经弃了我的xsmax\n以前用过的所有的iPhone 都是银色，这次本来想买海蓝色，后来觉得',
            }
        },

        methods: {
            showContentDetail () {
                if (this.isShowDetail) {
                    this.richtext = this.richtext2
                } else {
                    this.richtext = this.richtext1
                }

                this.isShowDetail = !this.isShowDetail
            }
        }
    }
</script>