 RecyclerView上拉加载更多
Android RecyclerView 瀑布流滑动到最后自动加载更多：https://www.cnblogs.com/genggeng/p/5336479.html

1 RecyclerView优缺点
(1) 优点
1)RecyclerView封装了viewholder的回收复用，也就是说RecyclerView标准化了ViewHolder，编写Adapter面向的是ViewHolder而不再是View了，复用的逻辑被封装了，写起来更加简单。
  直接省去了listview中convertView.setTag(holder)和convertView.getTag()这些繁琐的步骤
2)提供了一种插拔式的体验，高度的解耦，异常的灵活，针对一个Item的显示RecyclerView专门抽取出了相应的类，来控制Item的显示，使其的扩展性非常强。
3)设置布局管理器以控制Item的布局方式，横向、竖向以及瀑布流方式
例如：你想控制横向或者纵向滑动列表效果可以通过LinearLayoutManager这个类来进行控制(与GridView效果对应的是GridLayoutManager,与瀑布流对应的还StaggeredGridLayoutManager等)。也就是说RecyclerView不再拘泥于ListView的线性展示方式，它也可以实现GridView的效果等多种效果。
4)可设置Item的间隔样式（可绘制）
通过继承RecyclerView的ItemDecoration这个类，然后针对自己的业务需求去书写代码。
5)可以控制Item增删的动画,容易实现添加item、删除item的动画效果。
可以通过ItemAnimator这个类进行控制，当然针对增删的动画，RecyclerView有其自己默认的实现。
但是关于Item的点击和长按事件，需要用户自己去实现。
6)默认支持局部刷新。
我们都知道ListView通过adapter.notifyDataSetChanged()实现ListView的更新，这种更新方法的缺点是全局更新，即对每个Item View都进行重绘。但事实上很多时候，我们只是更新了其中一个Item的数据，其他Item其实可以不需要重绘。
这里给出ListView实现局部更新的方法：
public void updateItemView(ListView listview, int position, Data data){
    int firstPos = listview.getFirstVisiblePosition();
    int lastPos = listview.getLastVisiblePosition();
    if(position >= firstPos && position <= lastPos){  //可见才更新，不可见则在getView()时更新
        //listview.getChildAt(i)获得的是当前可见的第i个item的view
        View view = listview.getChildAt(position - firstPos);
        VH vh = (VH)view.getTag();
        vh.text.setText(data.text);
    }
}
可以看出，我们通过ListView的getChildAt()来获得需要更新的View，然后通过getTag()获得ViewHolder，从而实现更新。
RecyclerView提供了notifyItemInserted(),notifyItemRemoved(),notifyItemChanged()等API更新单个或某个范围的Item视图。
7)容易实现拖拽、侧滑删除等功能
RecyclerView是一个插件式的实现，对各个功能进行解耦，从而扩展性比较好。
(2) 缺点
ListView相比RecyclerView，有一些优点：
1)addHeaderView(), addFooterView()添加头视图和尾视图。
2)通过”android:divider”设置自定义分割线。
3)setOnItemClickListener()和setOnItemLongClickListener()设置点击事件和长按事件。
这些功能在RecyclerView中都没有直接的接口，要自己实现（虽然实现起来很简单），因此如果只是实现简单的显示功能，ListView无疑更简单。
(3)对比
1)缓存机制
RecyclerView比ListView多两级缓存，支持多个离ItemView缓存，支持开发者自定义缓存处理逻辑，支持所有RecyclerView共用同一个RecyclerViewPool(缓存池)。
2)回收机制
(4)结论：
1)在一些场景下，如界面初始化，滑动等，ListView和RecyclerView都能很好地工作，两者并没有很大的差异：
2)数据源频繁更新的场景，如弹幕：http://www.jianshu.com/p/2232a63442d6等RecyclerView的优势会非常明显；
进一步来讲，结论是：
列表页展示界面，需要支持动画，或者频繁更新，局部刷新，建议使用RecyclerView，更加强大完善，易扩展；
其它情况(如微信卡包列表页)两者都OK，但ListView在使用上会更加方便，快捷。

2 基本使用
Android 控件 RecyclerView:https://www.jianshu.com/p/4f9591291365
(1)Adapter
在Adapter中实现3个方法:
1)onCreateViewHolder()
这个方法主要生成为每个Item inflater出一个View，但是该方法返回的是一个ViewHolder。该方法把View直接封装在ViewHolder中，然后我们面向的是ViewHolder这个实例，当然这个ViewHolder需要我们自己去编写
2)onBindViewHolder()
这个方法主要用于适配渲染数据到View中。方法提供给你了一viewHolder而不是原来的convertView。
3)getItemCount()
这个方法就类似于BaseAdapter的getCount方法了，即总共有多少个条目。
可以看出，RecyclerView将ListView中getView()的功能拆分成了onCreateViewHolder()和onBindViewHolder()。
