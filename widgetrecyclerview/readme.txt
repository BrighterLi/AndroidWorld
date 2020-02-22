1 RecyclerView
  https://blog.csdn.net/qq_36243942/article/details/82187204?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
  (1)线性垂直RecyclerView
     1)item显示文字图片；
     2)分割线:使用addItemDecoration（）方法可以用来设置分割线，传进去的参数是ItemDecotation类型的对象；
     3)点击位置:
       方式一：直接在LinearAdapter的onBindViewHolder（）方法中直接实现监听器接口，然后绑定监听器即可。
       方式二：因为RecyclerView没有和ListView和GridView一样在外部有监听器接口，但是可以通过回调函数在外部实现监听的方法（不同类），想在LinearRecycleView.java中实现监听怎么做？
       原理：在LinearAdapter这个类里面写一个接口，然后在LinearRecycleView实现这个接口，然后复写里面的Onclick（）方法，通过函数的回调实现在LinearRecycleView这个类监听LinearAdapter这个类的事件。
  (2) 线性水平RecycleView的简单使用
2 RecyclerView嵌套
  https://www.cnblogs.com/jymblog/p/6812609.html

RecycleView
       RecyclerView 可以说是一个增强版的ListView，不仅可以轻松实现和 ListView 同样的效果，还优化了 ListView 中存在的各种不足之处。
       RecyclerView 也属于新增控件，为了让 RecyclerView 在所有 Android 版本上都能使用，将 RecyclerView 定义在了 support 库当中。
   因此首先要在项目的 build.gradle 中添加相应的依赖库才行。flexible（可扩展性）是RecyclerView的特点。RecyclerView是support-v7包中的新组件
        RecyclerView并不会完全替代ListView（这点从ListView没有被标记为@Deprecated可以看出），两者的使用场景不一样。但是RecyclerView的出现会让很多开源项目被废弃，
   例如横向滚动的ListView, 横向滚动的GridView, 瀑布流控件，因为RecyclerView能够实现所有这些功能。

   RecylerView相对于ListView的优点罗列如下：
   RecyclerView封装了viewholder的回收复用，也就是说RecyclerView标准化了ViewHolder，编写Adapter面向的是ViewHolder而不再是View了，复用的逻辑被封装了，写起来更加简单。
   直接省去了listview中convertView.setTag(holder)和convertView.getTag()这些繁琐的步骤。
   提供了一种插拔式的体验，高度的解耦，异常的灵活，针对一个Item的显示RecyclerView专门抽取出了相应的类，来控制Item的显示，使其的扩展性非常强。
   设置布局管理器以控制Item的布局方式，横向、竖向以及瀑布流方式
   例如：你想控制横向或者纵向滑动列表效果可以通过LinearLayoutManager这个类来进行控制(与GridView效果对应的是GridLayoutManager,与瀑布流对应的还StaggeredGridLayoutManager等)。也就是说RecyclerView不再拘泥于ListView的线性展示方式，它也可以实现GridView的效果等多种效果。
   可设置Item的间隔样式（可绘制）
   通过继承RecyclerView的ItemDecoration这个类，然后针对自己的业务需求去书写代码。
   可以控制Item增删的动画，可以通过ItemAnimator这个类进行控制，当然针对增删的动画，RecyclerView有其自己默认的实现。
   但是关于Item的点击和长按事件，需要用户自己去实现。