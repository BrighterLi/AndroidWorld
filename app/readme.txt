1控件— 自定义View—组合方式
<1> 自定义滚动显示器
https://www.jb51.net/article/88663.htm
实现功能：
(1) 自定义View标题栏布局
(2) 灵活的可以自己传入类型,选择所需要的控件来显示隐藏
(3) 免继承,可直接在布局里使用
(4) 直接可以在布局控件里设置属性

知识点：
(1) TypedArray：属性的集合
    AttributeSet：XML文件中定义的View的属性的集合
    attrs： 这个View自身的属性集合，是一个int数组
(2) android:gravity 主要是对自身View的位置控制。
    android:layout_gravity 主要用于设置View 或者 Layout 在其父组件中的对齐方式。
    RelativeLayout：
    水平垂直居中：android:layout_centerInParent = "true"
    水平居中:android:layout_centerHorizontal="true"
    竖直居中:android:layout_centerVertical="true"
(3) 自定义属性
     </declare-styleable>

2 框架(函数响应式编程)—RxJava Demo
(1) 观察者、被观察者、订阅
(2) 操作符
    创建操作符：
    https://blog.csdn.net/nicolelili1/article/details/52044330
    create:
    defer:Defer操作符会一直等待直到有观察者订阅它，然后它使用Observable工厂方法生成一个Observable。
 它对每个观察者都这样做，因此尽管每个订阅者都以为自己订阅的是同一个Observable，
 事实上每个订阅者获取的是它们自己的单独的数据序列。
    just:不断地将事件添加到任务队列中进行发射
    from:将数组的内容进行发射

    变换操作符：
    map:
    flatMap:
3 框架(网络)—OkHttp
  https://blog.csdn.net/yubo_725/article/details/51180617
  (1) 请求；上传；下载；封装；原理

4 框架(网络)—Retrofit
  底层基于OKHttp;使用注解
  (1)请求方法注解 Get Post
  (2)标记类注解
  (3)参数类注解 Body Path Field Query
5 框架(路由)—Router


