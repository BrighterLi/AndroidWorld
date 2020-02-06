1控件— 自定义View—组合方式
<1> 自定义标题栏
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
<2>自定义滚动显示器
    https://blog.csdn.net/yun382657988/article/details/84761433
   (1)布局加载方式
      LayoutInflater布局加载器
      Android基础之LayoutInflater的使用:https://blog.csdn.net/weixin_37292229/article/details/70046279
      1) 什么是LayoutInflater
      Inflater英文意思是膨胀，在Android中应该是扩展的意思吧。
      LayoutInflater是一个用于将xml布局文件加载为View或者ViewGroup对象的工具，我们可以称之为布局加载器。
            在应用中自定义一个view，需要获取这个view的布局，需要用到
            (LinearLayout) LayoutInflater.from(context).inflate(R.layout.contentitem, null);
      2)如何获取LayoutInflater
      LayoutInflater是一个抽象类，不可以通过new的方式获得它的实例。
      获取LayoutInflater通常有以下三种方式。
      1> LayoutInflater inflater = LayoutInflater.from(context);
      2> 在Activity内部调用getLayoutInflater()方法
      3> LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       实际上，1和2两种方式都是调用的3。
      3)使用场景：使用场景根据实际需要更改，这只是举例子。
       1> 在Fragment中
       inflater.inflate(R.layout.fragment_home, container, false)
       解释：当在Fragment的onCreateView()方法中填充并返回View时，要将attachToRoot设为false。如果传入true，会抛出IllegalStateException，
       因为指定的子View已经有父View了。我们只需要设置View的参数，而添加、移除或替换Fragment则是FragmentManager的事情。
       2> 在adapter中
       LayoutInflater.from(mContext).inflate(R.layout.layout_top_category_item, parent, false)
       解释：RecyclerView负责决定什么时候展示这个View，不需要我们指定添加。
       3> 自定义View
       View view = LayoutInflater.from(getContext()).inflate(R.layout.view_common_banner, this);
       把我们的布局文件添加到我们自定义View当中，比如自定义View继承的ViewGroup（比如Framlayout）中，也就是当前的this。
       4) LayoutInflater.from(context).inflate()和View.inflate的区别。
       View.inflate()是封装了LayoutInflater的inflate()方法，由于是静态方法，比较简便；但LayoutInflater相当于
       比View多了一个attachToRoot参数的inflate()方法，功能更强大。可以设置是否添加到root布局当中。View是当root不为null的时候，默认添加的。
       5) LayoutInflater的作用类似于findViewById(),LayoutInflater是用来实例化整个布局文件，而findViewById()是实例化布局文中的View,
       是找具体某一个xml下的具体 widget控件。
2 框架
<1> 框架(函数响应式编程)—RxJava Demo
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
<2> 框架(网络)—OkHttp
  https://blog.csdn.net/yubo_725/article/details/51180617
  (1) 请求；上传；下载；封装；原理

<3> 框架(网络)—Retrofit
  底层基于OKHttp;使用注解
  (1)请求方法注解 Get Post
  (2)标记类注解
  (3)参数类注解 Body Path Field Query

<4> 框架(路由)—Router

3 其他
 <1> LayoutInflater
  https://www.cnblogs.com/wwicked/articles/4339629.html
  (1) LayoutInflater和findViewById的区别
  // LayoutInflater的作用是，把一个View的对象与XML布局文件关联并实例化
  View itemView = inflater.inflate(R.layout.list_view_item, null);
  // View的对象实例化之后，可以通过findViewById()查找布局文件中的指定Id的组件
  TextView title = (TextView) itemView.findViewById(R.id.txtTitle);
  TextView text = (TextView) itemView.findViewById(R.id.txtContent);
  (2) LayoutInflater的作用及用法：
  作用：  1、对于一个没有被载入或者想要动态载入的界面, 都需要使用inflate来载入.
  2、对于一个已经载入的Activity, 就可以使用实现了这个Activity的的findViewById方法来获得其中的界面元素.
  方法： Android里面想要创建一个画面的时候, 初学一般都是新建一个类, 继承Activity基类, 然后
  在onCreate里面使用setContentView方法来载入一个在xml里定义好的界面.
 (3) 什么是已经被载入的layout,什么是还没有载入的,我们启动一个应用,与入口Activity相关的layout
 {常见的是main.xml}就是被载入的,即在onCreate()中的.而其他的layout是没有被载入的.就要动态载入了或通过另一个activity.