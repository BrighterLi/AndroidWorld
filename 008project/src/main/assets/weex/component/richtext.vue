<template>
    <div>
        <richtext @itemclick="listener" style="color:red;text-overflow:ellipsis">
            <span>link</span>
            <a pseudo-ref="21" href="http://t.cn?_wx_tpl=http://g.tbcdn.cn/ali-wireless-h5/res/0.0.16/hello.js">
                <image style="width:150; height:150" src="https://img.alicdn.com/tps/i2/TB1hRb1IXXXXXX3XVXXXQaP.pXX-87-87.jpeg"></image>
                <span style="font-size:42;color:#FF5400;">TAOBAO</span>
            </a>
            <image style="width:300; height:300" src="http://www.fresher.ru/manager_content/images2/kadry-veka/big/2-1.jpg" pseudo-ref="23"></image>
            <span>-{{i}}-</span>
            <span>继承Transition继承Transition继承Transition继承Transition继承Transition继承Transition继承Transition继承Transition继承Transition</span>
        </richtext>
    </div>
</template>

<script>
    var modal = weex.requireModule('modal');
    module.exports = {
        data () {
          return {
            i: 0
          }
        },
        created () {
          setInterval(() => {
            this.i ++

                modal.toast({
                  message: 'i is '+ this.i,
                  duration: 1
                });
          }, 1000)
        },
        methods: {
            listener: function (foo) {
                modal.toast({
                  message: 'My pseudoRef is '+foo.pseudoRef,
                  duration: 3
                });
            }
        }
    }
</script>


<style>
.logo{width: 50;height: 50;}
.title{font-size:42; color: #FF5400;}
</style>