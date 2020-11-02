1 PopupWindow
2 Video
3 下拉刷新
自己动手写一个简单的Android下拉刷新：https://blog.csdn.net/a62321780/article/details/79916845
Android 简易上下拉刷新：https://www.cnblogs.com/liangstudyhome/p/4448600.html
Android--简单的自定义ListView下拉刷新 ：https://www.cnblogs.com/ityizhainan/p/6273919.html
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

10 页面管理
(1) 加载中、空页面、错误页面

11 ViewPager
(1)更新及替换ViewPager中的Fragment
https://www.jianshu.com/p/79018b848b92
https://www.cnblogs.com/androiddream/p/9318684.html
12 Fragment

12 吸顶
https://www.jianshu.com/p/26b0911f396f