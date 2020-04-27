1 Android 动画分类
https://www.cnblogs.com/lixiansheng/p/11359933.html
Android动画可以分为两类，最初的传统动画和Android3.0 之后出现的属性动画；传统动画又包括 帧动画（Frame Animation）和补间动画（Tweened Animation）。
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

2 具体情境
 (1) 抖动
 https://blog.csdn.net/zhangcanyan/article/details/54896456?utm_source=blogxgwz4
 https://blog.csdn.net/qq_36574212/article/details/82900452
android怎么做iphone那种图片抖动动画的效果(包括button和EditText):http://m.myexception.cn/iphone/1708029.html
https://www.cnblogs.com/loaderman/p/10207077.html