一 ListView
https://blog.csdn.net/maybeforever/article/details/95998029
https://www.jianshu.com/p/da23fe946ed1
 1 基本用法
    （1）由于数组中的数据无法直接传递给ListView，我们需要借助适配器（Adapter）来完成。
    （2）ArrayAdapter可以通过泛型来指定要适配的数据，然后在构造函数中把要适配的数据传入。
    （3）android.R.layout.simple_list_item_1是ListView内置的一个子项布局，里面只有一个TextView，可显示一段文本
   补充：
   （1）simple_list_item_1（单行显示），此布局中只有一个TextView，是Android内置的布局文件，id为：android.R.id.text1；
   （2）simple_list_item_2和two_line_list_item（双行显示），都有两个TextView：android.R.id.text1和android.R.id.text2，
   不同之处在于，前者两行字是不一样大的，而后者两行字一样大小。
 2 自定义界面
   由于只显示一段文本的ListView太过于单调，我们对其界面进行自定义。
   首先，定义一个实体类，作为ListView的适配器类型。
   其次，为ListView的子项指定一个我们自定义的布局。
   然后，创建一个自定义的适配器。
 3 提升运行效率 convertView;ViewHolder
   （1）在上一部分中我们在WeatherAdapter类中使用了getView()方法，这个方法在每个子项被滚动到屏幕内的时候会被调用。
   该方法存在一个弊端：每次都得将布局重新加载一次。当快速滚动ListView的时候，会影响App的性能。getView()方法中还有一个convertView参数，
   这个参数用于将之前加载好的布局进行缓存，便于重复利用。
   我们在getView()中加入判断，如果convertView为null，则使用LayoutInflater去加载布局，不为空则直接重用convertView。
   （2）修改之后，虽然不会重复加载布局，但是每次getView()方法还是会调用View的findViewById()方法来获取一次控件的实例。
   我们使用ViewHolder来对这部分性能进行优化，ViewHolder通常出现在适配器里，为的是ListView滚动的时候快速设置值，
   而不必每次都重新创建很多对象，从而提升性能。通过将所有控件的实例都缓存在了ViewHolder里，就不需要每次通过findViewById()方法来获取控件实例。

  ps:性能测试对比？
      加载10000条item，对比内存，滑动流畅度...

  4 点击事件
  使用OnItemClickListener()方法为ListView注册监听器。部分代码如下：
  //为ListView添加点击事件
          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Weather weather = weatherList.get(position);
                  Toast.makeText(MainActivity.this, weather.getName(), Toast.LENGTH_SHORT).show();
              }
          });

          对ListView中的setOnItemClickListener的OnItemClick()方法进行介绍：
          //完整版
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}
          //简版
          OnItemClick(AdapterView agr0, View arg1, int arg2, long arg3)
         举个例子：X,Y两个ListView，X里面有1,2,3,4这4个item，Y里面有a，b，c，d这4个item，如果你点击了b这个item，如下：
         （1）arg0，即parent
         相当于ListView Y适配器的一个指针，可以通过它来获得Y里面装的一切东西，通俗点就是告诉你，你点的是Y不是X。
         （2）arg1，即view
         是你点b这个view句柄，就是你可以用这个view，来获得b里的控件的id后操作控件，通过它可以获得该项中的各个组件，例如：arg1.textview.setText(“abc”)。
         （3）arg2，即position
         是b在Y适配器里的位置（生成ListView时，适配器一个一个的做item，然后把他们按顺序排好队，再放到ListView里，意思就是这个b是第position号做好的）。
         （4）arg3，即id
         是b在ListView Y里面的第几行，在没有headerView、用户添加的view以及footerView的情况下，position和id的值是一样的。


二 RecycleView
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





三 ScrollView