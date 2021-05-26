<template>
    <div>
        <richtext @itemclick="listener" style="color:red;text-overflow:ellipsis">
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