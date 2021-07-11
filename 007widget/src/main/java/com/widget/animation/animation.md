1 Android 动画分类
https://www.cnblogs.com/lixiansheng/p/11359933.html
Android动画可以分为两类，最初的传统动画和Android3.0 之后出现的属性动画；传统动画又包括 帧动画（Frame Animation）和补间动画（Tweened Animation）。

Android的动画类型
Tween Animation 补间动画
Frame Animation 帧动画
Layout Animation 布局动画
Property Animation 属性动画

传统动画
(1)帧动画
帧动画是最容易实现的一种动画，这种动画更多的依赖于完善的UI资源，他的原理就是将一张张单独的图片连贯的进行播放，
从而在视觉上产生一种动画的效果；有点类似于某些软件制作gif动画的方式。
(2)补间动画
补间动画又可以分为四种形式，分别是 alpha（淡入淡出），translate（位移），scale（缩放大小），rotate（旋转）。
补间动画的实现，一般会采用xml 文件的形式；代码会更容易书写和阅读，同时也更容易复用。Java也可以直接实现。
View Animation
又称补间动画，在 android.view.animation.Animation 类之下衍生了五个子类。
AlphaAnimation	渐变透明度
RotateAnimation	旋转
ScaleAnimation	尺寸缩放
TranslateAnimation	位置平移
AnimationSet	动画集合
​通过前四个类，基本可以解决大部分动画需求，再使用 AnimationSet 使动画具有组合的能力。

(3)属性动画
属性动画，顾名思义它是对于对象属性的动画。因此，所有补间动画的内容，都可以通过属性动画实现。
ObjectAnimator，ValueAnimator

2 区别与使用
(1) 补间动画和属性动画主要区别：
Android补间动画和属性动画的区别及属性动画使用详解: https://www.cnblogs.com/lgdcoder/p/10684996.html
作用对象不同，补间动画只能作用在view上，属性动画可以作用在所有对象上。
属性变化不同，补间动画只是改变显示效果，不会改变view的属性，比如位置、宽高等，而属性动画实际改变对象的属性。
动画效果不同，补间动画只能实现位移、缩放、旋转和透明度四种动画操作，而属性动画还能实现补间动画所有效果及其他更多动画效果。

新引入的属性动画机制已经不再是针对于 view 来设计的，也不限定于只能实现位移、缩放、旋转和透明度这几种动画操作，同时也不再只是一种视觉上的动画效果。
它实际上是一种不断地对值进行操作的机制，并将值赋值到指定对象的指定属性上，可以是任意对象的任意属性。我们只需要告诉系统动画的运行时长，需要执行哪种类型的动画，
以及动画的初始值和结束值，剩下的工作就可以全部交给系统去完成了。

(2)使用方式
代码；xml

(3) 属性动画
RotateAnimation
Android 常用动画之RotateAnimation： https://blog.csdn.net/a751608624/article/details/46874615

3 具体情境
 (1) 抖动 上下 左右  角度旋转
 https://blog.csdn.net/zhangcanyan/article/details/54896456?utm_source=blogxgwz4
 https://blog.csdn.net/qq_36574212/article/details/82900452
android怎么做iphone那种图片抖动动画的效果(包括button和EditText):http://m.myexception.cn/iphone/1708029.html
https://www.cnblogs.com/loaderman/p/10207077.html
(2) 组合动画
多个动画连续播放，同时播放

4 Lottie
android：Lottie--让Android动画更优雅:https://www.jianshu.com/p/1f011bc472cd
Android Lottie动画初探:https://www.jianshu.com/p/0a5cf2261b4b?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
要实现这些特效，大概有以下几种方式:
(1)使用帧动画。但是一个动画需要添加很多张图片，apk体积必然变大，并且还要根据不同的尺寸进行适配。
(2)用 Gif。但是使用Gif 占用空间较大，同样需要为各种屏幕尺寸、分辨率做适配，同时原生Android本身是不支持gif直接展示的。
(3)编码加图片辅助。这种方式繁琐并且不易维护，稍作修改就要推倒重新来过。
(5)Android 5.x 之后提供了对 SVG 的支持，通过 VectorDrawable、AnimatedVectorDrawable 的结合可以实现一些稍微复杂的动画，但是问题和前2个类似。
那么，现在有一个方案，不使用大量图片，甚至零图片，不占空间，不占内存，不需要适配，且易于维护，简单而且方便。


5 Activity加入动画
相关方法：
(1) 在onCreate加
overridePendingTransition(R.anim.enter, R.anim.exit):
Activity的切换动画指的是从一个activity跳转到另外一个activity时的动画。
它包括两个部分：
一部分是第一个activity退出时的动画；
另外一部分时第二个activity进入时的动画；

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
    overridePendingTransition(R.anim.enter, R.anim.exit);
    setContentView(R.layout.activity_functions);
}
(2) 在Activity的theme加入动画
动画不生效原因：
用的Activity在Manifest中设置了SingleInstance属性，所以Activity处于已经实例化的状态，不会再被实例化，于是Activity动画不会播放，只有Window动画会播放。把这个属性改成SingleTop就好了。
(3) 在startActivity后面加
overridePendingTransition(R.anim.enter , R.anim.exit);
(4) 在finish里面加
overridePendingTransition()使用方法
Activity的切换动画从业务层面上来说可以分为两种，一种是Activity启动时的动画，一种是从Activity返回时的动画，它们都可以通过overridePendingTransition()来设置，
要设置启动时的动画需要在执行startActivity()或startActivityForResult()之后调用overridePendingTransition()，要设置返回时的动画需要在finish()之后调用overridePendingTransition()。
启动动画和返回动画是相互独立的，设置启动动画不会对返回动画产生影响，如果只在startActivity()或startActivityForResult()之后调用了overridePendingTransition()，没有在finish()的时候调用，
则Activity返回的时候仍然是默认的动画效果，也可以在finish()的时候使用和启动时不同的动画效果。


6相关问题：
(1)不起作用的原因
大概是以下几个方面的原因：
android系统版本2.0以下，这个没办法，想其他办法解决切换动画吧。
在ActivityGroup等的嵌入式Activity中，这个比较容易解决，用如下方法就可以了：this.getParent().overridePendingTransition 就可以解决。
在一个Activity的内部类中，或者匿名类中，这时候只好用handler来解决了。
手机的显示动画效果被人为或者其他方式给关闭了 现在打开即可 设置->显示->显示动画效果
(2)Activity动画跳转黑屏
Activity设置切换动画时黑屏问题的解决:https://blog.csdn.net/tndroid/article/details/47446423