<template>
    <div>

        <richtext :key="i">
            -{{i}}-
        </richtext>

        <text @click="clickbutton" style="width:250;height:250">
           按钮
        </text>
    </div>
</template>

<style>

  .slide-fade-enter{
  transform: translateX(40px);
  opacity: 0;
}

</style>
<script>
    var modal = weex.requireModule('modal');
    module.exports = {
        data () {
          return {
            i: 0
          }
        },
        created () {
          // setInterval(() => {
          //   this.i ++

          //       modal.toast({
          //         message: 'i is '+ this.i,
          //         duration: 1
          //       });
          // }, 1000)
        },
        methods: {
            listener: function (foo) {
                modal.toast({
                  message: 'My pseudoRef is '+foo.pseudoRef,
                  duration: 3
                });
            },
            clickbutton: function (foo) {
              this.i += 3
              this.$forceUpdate()
                modal.toast({
                  message: 'click button' + this.i,
                  duration: 3
                });
            },
        }
    }
</script>


<style>
.logo{width: 50;height: 50;}
.title{font-size:42; color: #FF5400;}
.but{width: 250;height: 250;}
</style>