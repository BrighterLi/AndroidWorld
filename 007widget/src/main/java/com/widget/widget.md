1
1 组件、插件、控件的区别
控件:是编程中用到的,按钮就算是一个控件,窗口也是等等
组件:是软件的一部分.软件的组成部分.
插件:网页中用到的,flash插件,没有它浏览器不能播放flash.

2组件/构件vs控件vs插件
首先范围最广的应该是组件，英文component，提起组件我们不应该把他和具体的技术，什么dll文件，ocx控件，activex等等联系起来，
因为组件仅仅是一个概念，如果非要解释的话，那就是凡是在软件开发中用到了软件的复用，被复用的部分都可以称为组件。
构件的英文也是component，所以说构件和组件其实是一个意思只是翻译的不同而已。

插件是组件（构件）的一种，我们可以这样给插件进行定义，那就是凡是在应用程序中已经预留接口的组件就是插件，
例如：java中jdbc技术，jdbc只是一个接口，任何一个插件制造商只要实现这个接口都可以被java平台所使用。
我们还可以拿IE插件作为例子，IE中之所以可以嵌入很多的应用程序，那是因为IE允许他们插入，说的明白一点，
那就是在IE的源程序中已经为这些应用程序预留了接口，只要把通知浏览器已经加载了什么插件，
浏览器就会调用预留的接口调用这些所谓的插件。

当然控件也是组件（构件）的一种，按照网上的说法，控件就是可视化的组件，我也同意这种说法，其实再从普通组件中分解出控件完
全是没有必要的，因为对于开发人员来讲，可不可视对于非软件人员来说可能很重要，但是对于软件人员来说又有什么区别呢？

2 具体控件
1 PopupWindow
2 Video
3 下拉刷新
自己动手写一个简单的Android下拉刷新：https://blog.csdn.net/a62321780/article/details/79916845
Android 简易上下拉刷新：https://www.cnblogs.com/liangstudyhome/p/4448600.html
Android--简单的自定义ListView下拉刷新 ：https://www.cnblogs.com/ityizhainan/p/6273919.html
Android下拉刷新完全解析，教你如何一分钟实现下拉刷新功能：https://blog.csdn.net/guolin_blog/article/details/9255575
开源框架：PtrFrameLayout  in.srain.cube:ultra-ptr
google官方： SwipeRefreshLayout
4 加载更多
5 HorizontalScrollView
横向滑动视图HorizontalScrollView精炼详解: https://blog.csdn.net/weixin_41101173/article/details/80158169

6嵌套滑动
(1)ScrollView+RecyclerView
(2)RecyclerView+RecyclerView
RecyclerView双列表联动效果开发细节：https://blog.csdn.net/xyq046463/article/details/94360349?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.channel_param
Android RecyclerView嵌套RecyclerView：https://www.cnblogs.com/zhujiabin/p/7045812.html
Android中RecyclerView嵌套RecyclerView或嵌套ListView:https://blog.csdn.net/bfboys/article/details/82964262
Android 仿京东,淘宝RecyclerView嵌套ViewPager嵌套RecyclerView商品展示:
https://www.jianshu.com/p/a5100ac471ae
(3)NestedScrollView+RecyclerView

7 滑动冲突
(1) 外部拦截法(由父容器决定事件的传递)：让事件都经过父容器的拦截处理
1)首先down事件父容器必须返回false ，因为若是返回true，也就是拦截了down事件，那么后续的move和up事件
就都会传递给父容器(onTouchEvent)，子元素就没有机会处理事件了。
2)其次是up事件也返回了false，一是因为up事件对父容器没什么意义，其次是因为若事件是子元素处理的，
却没有收到up事件会让子元素的onClick事件无法触发。
(2)内部拦截法(自己决定事件的传递)：父容器不拦截任何事件，将所有事件传递给子元素
如果子元素需要则消耗掉，如果不需要则通过requestDisallowInterceptTouchEvent方法(请求父类不要拦截,
返回值为true时不拦截,返回值为false时为拦截)交给父容器处理


8 ListView
(1)addHeaderView方法，addFooterView方法
(2)分页加载
Android ListView分页加载（服务端+android端）Demo:https://blog.csdn.net/u012440207/article/details/36424843?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.channel_param&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-4.channel_param
(3)平常的一个列表页面的生成需要以下文件：
一个Activity文件，有时候可能还会忘记注册
一个包含上下拉刷新控件以及无数据时提示的布局文件
一个Listview的item的布局文件
一个Adapter适配器文件
一个需要被解析的Bean文件
当然在Activity中还需要处理以下功能：
数据解析
分页加载
数据缓存
网络请求

9 RecyclerView
(1)addHeaderView方法，addFooterView方法
(2) ExtendedLinearLayoutManager,GridLayoutManager,StaggeredGridLayoutManager

10 页面管理
(1) 加载中、空页面、错误页面

11 ViewPager
(1)更新及替换ViewPager中的Fragment
https://www.jianshu.com/p/79018b848b92
https://www.cnblogs.com/androiddream/p/9318684.html
12 Fragment

12 吸顶
https://www.jianshu.com/p/26b0911f396f

13 TabLayout
使用Android原生的Tablayout也可以借助第三方框架实现。原生的tablayou局限很多，比如不能设置指示条宽度、
不能定义tab的字体大小、不能设置选中tab字体加粗。不过也有一些通过反射来修改属性的解决方案，都不能完全解决问题。
第三方框架：
XTablayout；FlycoTablayout

14 带气泡在某个位置弹出
自定义：Android：DropPopMenu — 显示位置跟随操作按钮显示的带箭头的弹出菜单：https://blog.csdn.net/hmyang314/article/details/70920295
已有组件：Android-气泡对话框（根据被点击View位置显示、可定制）：https://www.jianshu.com/p/0f09c21c5f7f

15 头部布局及变化
(1) 高度固定+内容不变 + 吸顶
(2) 高度固定+内容变化 + 吸顶
(3) 高度变化(滑动一定距离瞬间变化)+内容变化 + 吸顶
(4) 高度变化(跟随滑动慢慢变化)+内容变化 + 吸顶

16 状态栏
(1) 沉浸式状态栏
Android 沉浸式状态栏实践: https://www.jianshu.com/p/fc5854895a10
Android关于沉浸式状态栏的一些总结:https://www.jianshu.com/p/752f4551e134
轮子StatusBarUtil：https://github.com/laobie/StatusBarUtil
Android沉浸式状态栏(透明状态栏)最佳实现:https://blog.csdn.net/zephyr_g/article/details/53489320
沉浸式状态栏:
实际的效果其实就是透明的状态栏，然后在状态栏的位置显示我们自定义的颜色，通常为应用的actionbar的颜色，或者是将应用的整体的一张图片也占据到状态栏中

17 Activity
* requestCode 请求码，即调用startActivityForResult() 传递过去的值
* resultCode 结果码，结果码用于标识返回数据来自哪个新Activity
请求码的作用:
使用startActivityForResult(Intent intent, int requestCode)方法打开新的Activity，我们需要为startActivityForResult()方法传入一个请求码(第二个参数)。请求码的值是根据业务需要由自已设定，用于标识请求来源。例如：一个Activity有两个按钮，点击这两个按钮都会打开同一个Activity，不管是那个按钮打开新Activity，当这个新Activity关闭后，系统都会调用前面Activity的onActivityResult(int requestCode, int resultCode, Intent data)方法。在onActivityResult()方法如果需要知道新Activity是由那个按钮打开的，并且要做出相应的业务处理
结果码的作用:
在一个Activity中，可能会使用startActivityForResult()方法打开多个不同的Activity处理不同的业务，当这些新Activity关闭后，系统都会调用前面Activity的onActivityResult(int requestCode, int resultCode, Intent data)方法。为了知道返回的数据来自于哪个新Activity,在onActivityResult()方法中可以这样做(ResultActivity和NewActivity为要打开的新Activity)：
public class ResultActivity extends Activity {
           .....
           ResultActivity.this.setResult(1, intent);
           ResultActivity.this.finish();
    }
    public class NewActivity extends Activity {
           ......
            NewActivity.this.setResult(2, intent);
            NewActivity.this.finish();
    }
    public class MainActivity extends Activity { // 在该Activity会打开ResultActivity和NewActivity
           @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                   switch(resultCode){
                       case 1:
                       // ResultActivity的返回数据
                       case 2:
                        // NewActivity的返回数据
                    }
              }
    }

18 AppBarLayout
AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当某个可滚动View的滚动手势发生变化时，其内部的子View实现何种动作。
上面提到的某个可滚动View，可以理解为某个ScrollView。怎么理解上面的话呢？就是说，当某个ScrollView发生滚动时，你可以定制你的“顶部栏”应该执行哪些动作（如跟着一起滚动、保持不动等等）。那某个可移动的View到底是哪个可移动的View呢？这是由你自己指定的

19 CoordinatorLayout