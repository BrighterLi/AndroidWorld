1 Adapter
它在ListView和数据源之间起到桥梁的作用，避免listview和数据源直接接触，而导致因为数据源的复杂性使listview显得臃肿。
Adapter,适配器，把复杂的数据源适配给listview,很容易联想到适配器模式。

下面是几种常用的Adapter:
　ArrayAdapter:简单易用的Adapter，通常用于数组或list集合的数据源（多个值包装成多个列表项）。
　simpleAdapter:并不见得、功能强大的Adapter，可用于list集合的多个对象包装成多个列表项。
　simpleCursorAdapter:与上相似，但是用于包装jCursor(数据库游标)提供的数据源。
  BaseAdapter:通常用于被扩展。扩展BaseAdapter可以对各列表项进行最大限度的定制。

2 listview的优化
Android优化系列之ListView优化老生常谈:https://www.cnblogs.com/yuhanghzsd/p/5595532.html
android-----带你一步一步优化ListView(一)：https://blog.csdn.net/hzw19920329/article/details/51383864
(1) convertView的使用
*优化加载布局问题
.listivew每次滚动都会调用gitview()方法，所以优化gitview是重中之重。
如果没有缓存就加载布局，如果有缓存就直接用convertView对象。所以这样就不用滑动listview的时候
调用getView()方法每次都去加载布局了（如果该布局已经加载）。（LayoutInflater.inflate()比较消耗性能，要解析xml文件）
(2) 内部类ViewHolder的使用
*优化加载控件
主要优化getView方法中每次回调用findviewByID()方法来获取一次控件的代码。
新增加内部类ViewHolder,用于对控件的实例存储进行缓存。
convertView为空时，viewHolder会将控件的实例存放在ViewHolder里，然后用setTag方法讲viewHolder对象存储在view里。
convertView不为空时，用getTag方法获取viewHolder对象.
总结：
1)通过复用view的方式来充分利用android系统本身自带的RecycleBin缓存机制，能够保证即使有再多的item实际中也仅仅会有有限多个item，大大节省内存；
2)使用静态内部类以及setTag方式，将view与其对应的控件绑定起来，避免了每次得到view以后都需要通过findViewById的方式来获取控件；
3)对于有多种布局的ListView来说，我们可以通过getViewTypeCount()获得布局的种类，通过getItemViewType(int)获得当前位置上的布局到底是属于哪一类；

(3) ListView加载图片方面的优化
android-----带你一步一步优化ListView(二): https://blog.csdn.net/hzw19920329/article/details/51523658
要想获取一张图片不管是本地的还是网络的，其性能上均没有从内存中获取快，所以为了提升用户的体验度，对于加载图片的ListView，通常我们会通过缓存做以下优化：
 基本思想：
1)如果想要加载图片，首先先去内存缓存中查看是否有图片(内存缓存)
2)如果内存缓存中没有，就会去本地SD卡上查找是否存在(SD卡缓存)
3)如果本地SD卡上也没有的话，则会去网络下载了，下载完成之后将该图片存入本地缓存和内存缓存；

3 ListView加载成千上万的数据为什么不出OOM错误？/ RecycleBin机制
最主要的是因为RecycleBin机制
对子View进行回收利用