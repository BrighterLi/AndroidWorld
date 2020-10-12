一 业务场景
1 滑动列表
2 TabLayout+ViewPager+Fragment
Android:使用TabLayout详解:https://www.cnblogs.com/lyd447113735/p/8693931.html
Android Studio列表用法之一：ListView图文列表显示（实例）:https://www.cnblogs.com/AnneHan/archive/2018/09/30/9726391.html
3 上下滑动隐藏出现头布局
仿支付宝上下滑动隐藏出现头布局:
https://blog.csdn.net/u014005316/article/details/83346982?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param
Android CoordinatorLayout + AppBarLayout(向上滚动隐藏指定的View):
https://www.cnblogs.com/wingyip/p/4604472.html
4 外部列表内部列表/多个列表
一步步教你使用android的RecyclerView实现完整的复杂列表布局：https://www.cnblogs.com/aademeng/articles/9820680.html
Android 多个滑动控件嵌套实现方式:https://blog.csdn.net/Pillar1066527881/article/details/89459084
即刻App详情页滑动效果，SlideLayout双列表页面实现 :https://www.sohu.com/a/348598471_608959
玩转Android嵌套滚动:https://www.cnblogs.com/aikongmeng/articles/10761344.html
Android开发之复杂布局嵌套(ScrollView+TabLayout+ViewPager+RecyclerView)导致冲突的解决办法
https://blog.csdn.net/hq942845204/article/details/88844272
5 吸附悬停
Android 滑动定位+吸附悬停效果实现：https://www.cnblogs.com/taixiang/p/9426874.html

6 瀑布流
Android 瀑布流Demo：https://blog.csdn.net/u012440207/article/details/24002603?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromBaidu-1.channel_param
ANDROID 瀑布流（图片浏览）:https://www.cnblogs.com/prescheng/p/4937298.html  ScrollView实现

7 TabLayout
TabLayout设置下划线(Indicator)宽度:https://www.cnblogs.com/Android-FJH/p/9198453.html

8 NestedScrollView
即支持嵌套滑动的ScrollView

二 Task
1 Android实践之ScrollView中滑动冲突处理
  https://www.jianshu.com/p/87a41b8c0dd0
2要求：上滑的时候先滑headerView，headerView滑出屏幕时，tableView吸顶且开始滑动。下滑时先滑tableView，滑到顶部第一个cell出现，则开始滑headerView。 这是一个最简单的scrollView嵌套需求，后面还会有进阶的需求。
进阶的需求
  上下滑的同时，还要求左右滑:

三 技术分析
ListView、RecycleView、ScrollView、NetScrollView、CoordinatorLayout

1 RecycleView与ScrollView,Listview对比分析
https://blog.csdn.net/fdadala/article/details/82461340
(1)ScrollView与listView
1)ScrollView中不管多少的数据项，它都会全部给加载出来，通过AndroidStudio中的AndroidProfiler查看Memory可以发现内存没
有任何的变化。
2)ScrollView里面能摆放很多控件组件，高度超过ScrollView的高度的话就可以滚动了，ScrollView里面的东西是初始化完成了就
存在了，就已经在内存中了。并且没有回收与复用，假设一个界面特别长，条目种类特别多，内存就会占用特别的大。当内存不足
时就会导致内存溢出（OOM）。
3)ListView 是只显示自身listItem内的东西，比如你有30条，listview能显示20条。那开始内存里只有20条，当下滑时才加载后面
的10条。
4)RecyclerView 是Android的一个更强大的控件，相对于ListView，其不仅可以实现与ListView同样的效果，有可以优化ListView中的各种
不足。ListView是纵向滚动，但是RecyclerView既可以实现纵向滚动也可以实现横向滚动，下面是对于使用RecyclerView出现的一些问题。
5)ScrollView，其是一个滚动视图，当你的页面内容很多的时候 ，就需要使用到ScrollView，其可以上下滑动内容

2 NetScrollView 和ScrollView之间的异同点
相同点：NestScrollView和ScrollView只能包含一个bai直接子节点，二du者嵌套ListView都会出现滚zhi动冲突，只出现第一行dao数据
二者嵌套ListView或者RecyclerView等其他的滑动view都会出现滚动冲突：最简单有效的解决办法，重新定义View的子类，重写里面的onMeasure(){}方法

事实上 ，并不建议使用这个ScrollView或者NestScrollView嵌套别的滑动view，这样虽然能用上面的方法解决冲突问题，
但是有可能导致内存溢出因为每个view加载的数据无限制多，如果服务器返回的每页数据特别多，将导致内存溢出可能造成ANR 
我现在都使用RecyclerView的多布局实现整个页面的设计和布局

不同点：在design包下的文件中使用NestScrollView可以避免一些横屏滑动和竖屏滑动之间的冲突问题 通常情况下建议使用NestScrollView
