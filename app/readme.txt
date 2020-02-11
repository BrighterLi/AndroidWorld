1控件— 自定义View—组合方式
<1> 自定义标题栏 WidgetCustomTitleBar
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

    layout_gravity="center_vertical"和 layout_centerVertical="true"区别?
    都表示当前控件在父容器的位置是垂直居中,后者的父容器只能是相对布局
(3) 自定义属性
     </declare-styleable>
<2>自定义滚动显示器 WidgetWheelView
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

   <3> 各种自定义View Demo
     https://blog.csdn.net/yilei0033/article/details/79445540
     https://blog.csdn.net/qq_37217804/article/details/80894986
    (1) android自定义组件一般有三种实现方式：
        一、组合控件：组合控件，顾名思义就是将一些小的控件组合起来形成一个新的控件，这些小的控件多是系统自带的控件。
        二、自绘控件: 何为自绘控件，就是完全用Paint和canvas画出来的，就是在onDraw()方法里面绘画，在onMeasure()方法里面进行测量，
        如果是容器在onLayout()方法中定位每个子组件。
        三、继承控件: 就是继承已有的控件，创建新控件，保留继承的父控件的特性，并且还可以引入新特性。
        自绘控件也分两种，自定义组件和自定义容器，自定义组件是继承View类，自定义容器时继承ViewGrounp
     (2) srollTo(int x,int y)
         srollTo(int x,int y)表示的是移动到哪个坐标点,是将View中的内容偏移至坐标(x,y)处，注意移动的是内容，而不是View。
         srcollBy(int x,int y)
         srollTo(x,y)表示的是移动的增量dx和dy,这个和scrollBy有点类似，其实srollToBy方法在其内部是调用了scrollTo()方法，
         但是参数x是内容横向移动相应距离，参数y是内容纵向移动相应距离，也就是说直接在原来坐标的基础上再继续移动。
         (为正时代表右向左或者下向上)
         https://blog.csdn.net/znouy/article/details/51338256
         getSrcollX(): getScrollX() 就是当前view的左上角相对于母视图的左上角的X轴偏移量
         getScrollY(): getScrollY() 就是当前view的左上角相对于母视图的左上角的Y轴偏移量

         规则：左加右减，上加下减
         瞬间移动视图的内容 : 利用 View的 scroll 方法
             1). scrollBy(int x, int y) : 滑动指定的偏移量 (从当前位置瞬间 )
                    x: x 轴上的偏移量 , x>0内容向左滑动 , x<0 内容向右滑动 , x=0 水平方向不滑动
                    y: y 轴上的偏移量 , y>0内容向上滑动 , y<0 内容向下滑动 , y=0 垂直方向不滑动
              2). scrollTo(int x, int y) : 滑动到指定的偏移量 ( 从当前位置瞬间 )
                    x: 目标位置 x轴上的偏移量 , x>0 移动到原始位置的左侧 , x<0移动到原始位置的右侧 ,x=0 移动到水平原始位置


                    y: 目标位置 y轴上的偏移量 , y>0 移动到原始位置的上侧 , y<0移动到原始位置的下侧 ,y=0 移动到垂直原始位置
         区别：by ：每次执行都会移动
              to ：如果参数是死的，那么每次执行只能移动到固定位置
    <4> 地址选择器 WidgetAddressSelector(仿京东地址选择器)(不依赖第三方地址选择器)
        需求：圆角；由下到上弹出；回调刷新(刷新等待与错误处理)；四级或三级；
     (1) Android的对话框有两种：PopupWindow和AlertDialog。
             它们的不同点在于：AlertDialog的位置固定，而PopupWindow的位置可以随意
         AlertDialog是非阻塞线程的，AlertDialog弹出的时候，后台可是还可以做其他事情。
         而PopupWindow是阻塞线程的, 这就意味着在我们退出这个弹出框之前，程序会一直等待。
              从效果上去总结看，dialog和popWindow在一般的弹出效果上能做到一样的效果，但是dialog默认在屏幕的居中弹出，
         popWindow则可以更灵活的通过设置动化效果，从上下左右边缘地带弹出来，还能更方便的控制显示在屏幕上的位置。
              从需求上，如果让显示的就是居中弹出的小窗口，背景带蒙层，那么用dialog更方便，因为popWindow得手动添加蒙层，
          不如dialog便捷，而且当按下物理返回键的时候，dialog会dismiss，但是popWindow默认不会进行dismiss操作，程序会最小化，也就是说，popWindow要手写物理按键的监听。
     (2) 自定义dialog 默认的显示位置是window 的位置
         可以通过dialog或者窗口对象 window 然后通过window 去设置dialog的上下中的位置
   <5> 时间选择器 WidgetTimeSelector  不依赖第三方选择器
       需求：联动；圆角；由下到上弹出；回调刷新；显示一列二列三列可选；
       https://blog.csdn.net/qq_22393017/article/details/58099486

   <6> 时间选择器 WidgetTimeSelector2 依赖第三方选择器
       需求：联动；圆角；由下到上弹出；回调刷新；显示一列二列三列可选；

   <7> PopupWindow  弹窗
       (1) 两种弹窗  PopupWindow;Dialog
           都可以通过将自定义的view设置进去，从而自定义弹窗内容。
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

 (4)Activity中的findViewById()和View中的findViewById()区别
  https://blog.csdn.net/DavidHuang2017/article/details/78249035
  findViewById是有上下文的，默认是在Activity的主布局中，当在一些主布局的View中，子布局中
  比如dialog中，就要用view.findViewById(),才行。要不然报空指针错误。

  4 跨段交互(Across End)
  <1> WebView
   (1) android.annotation.SuppressLint
       Lint是一个静态检查器，它围绕Android项目的正确性、安全性、性能、可用性以及可访问性进行分析。
       它检查的对象包括XML资源、位图、ProGuard配置文件、源文件甚至编译后的字节码。
       Lint包含了API版本检查、性能检查以及其他诸多特性。
       其中还有一个重要的改动是Lint可以使用@SuppressLint标注忽略指定的警告。

   <2> Weex
   (1) 加载weex分为两种情况，一种是加载本地，一种是加载网络。
   //加载网络资源url
   mWXSDKInstance.renderByUrl(url,url,options,jsonInitData,WXRenderStrategy.APPEND_ASYNC);}
   //加载本地资源
   mWXSDKInstance.render(template,template,options,jsonInitData,WXRenderStrategy.APPEND_ASYNC);}

5 网络
 <1> Cookie、Session
  https://blog.csdn.net/lishuai05251986/article/details/84804199
<2> token