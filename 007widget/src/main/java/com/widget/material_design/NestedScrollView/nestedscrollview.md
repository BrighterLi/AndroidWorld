1 NestedScrolling机制能够让父View和子View在滚动式进行配合，其基本流程如下：
  当子view开始滚动之前，可以通知父View，让其先于自己进行滚动；
  子View自己进行滚动；
  子view滚动之后，还可以通知父view继续滚动。
  而要实现这样的交互机制，首先父view要实现NestedScrollingParent接口，而子View需要实现NestedScrollingChild接口，在这套机制中子View是发起者，父view是接受回调并做出响应的。

2 嵌套滑动机制
嵌套滑动机制 的基本原理可以认为是事件共享，即当子控件接收到滑动事件，准备要滑动时，会先通知父控件(startNestedScroll）；然后在滑动之前，会先询问父控件是否要滑动
（dispatchNestedPreScroll)；如果父控件响应该事件进行了滑动，那么就会通知子控件它具体消耗了多少滑动距离；然后交由子控件处理剩余的滑动距离；最后子控件滑动结束后，
如果滑动距离还有剩余，就会再问一下父控件是否需要在继续滑动剩下的距离（dispatchNestedScroll)


3 注意
  NestedScrollView主要用于非列表（除ListView和RecyclerView）间的滑动比较好；
  NestedScrollView嵌套RecyclerView会导致复用失效，看了很多网友的帖子，一直没有很好的解决方案；
  如果RecyclerView数据量很少，可以复用的；
  建议给RecyclerView添加头部和尾部的方式，弃用NestedScrollView；

 3 Demo
 Android -- NestedScrolling滑动机制: https://www.cnblogs.com/wjtaigwh/p/6398562.html
 Android中NestedScrollview的使用: https://www.jianshu.com/p/b2281e2988fc
 Android：下拉上滑显示与隐藏导航栏和状态栏（NestedScrollView实现 + RecyclerView实现）: https://blog.csdn.net/u011343735/article/details/53761170
 NestedScrollview使用---优雅的代替ScrollView内嵌ListView或者RecyclerView: https://blog.csdn.net/Jeff169/article/details/80231257

 4 源码解析
 透过 NestedScrollView 源码解析嵌套滑动原理： https://www.cnblogs.com/huansky/p/12897541.html

