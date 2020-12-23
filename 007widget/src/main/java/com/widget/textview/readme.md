1 自定义View继承TextView
Android自定义View之继承TextView绘制背景：https://www.jb51.net/article/85020.htm
Android 通过继承TextView类自定义字体默认颜色：https://blog.csdn.net/AMinfo/article/details/7907903
android继承TextView实现文本彩色闪烁：https://www.cnblogs.com/sharkli/p/5978637.html

2 TextView实现原理
Android控件TextView的实现原理分析： https://blog.csdn.net/luoshengyang/article/details/8636153
控件为了实现自己的功能而需要的东西是什么呢？有两个材料是必不可少的。第一个材料是画布，第二个材料是用户输入。
有画布才能绘制UI，而有用户输入才能与用户进行交互。因此，接下来我们主要分析TextView的绘制流程，以及它获得用户输入的过程。

测量：为了能告诉父视图自己的所占据的空间的大小，所有控件都必须要重写父类View的成员函数onMeasure。

布局：前面的测量工作实际上是确定了控件的大小，但是控件的位置还未确定。控件的位置是通过布局这个操作来完成的。
我们知道，控件是按照树形结构组织在一起的，其中，子控件的位置由父控件来设置，也就是说，只有容器类控件才需要执行布局操作，
这是通过重写父类View的成员函数onLayout来实现的。从Activity窗口的结构可以知道，它的顶层视图是一个DecorView，这是一个容器类控件。
Activity窗口的布局操作就是从其顶层视图开始执行的，每碰到一个容器类的子控件，就调用它的成员函数onLayout来让它有机会对自己的子控件的位置进行设置，依次类推。
常见的FrameLayout、LinearLayout、RelativeLayout、TableLayout和AbsoluteLayout，都是属于容器类控件，因此，它们都需要重写父类View的成员函数onLayout。
由于TextView控件不是容器类控件，因此，它可以不重写父类View的成员函数onLayout。

绘制：有了前面两个操作之后，控件的位置的大小就确定下来了，接下来就可以对它们的UI进行绘制了。控件为了能够绘制自己的UI，必须要重写父类View的成员函数onDraw。
参数canvas描述的是一块画布，控件的UI就是绘制在这块画布上面的。画布提供了丰富的接口来绘制UI，例如画线（drawLine）、画圆（drawCircle）和贴图（drawBitmap）等等。
有了这些UI画图接口之后，就可以随心所欲地绘制控件的UI了。

Java层的Canvas实际上是封装了C++层的SkCanvas。C++层的SkCanvas内部有一块图形缓冲区，这块图形缓冲区就是窗口的绘图表面Surface）里面的那块图形缓冲区。
窗口的绘图表面里面的那块图形缓冲区实际上是一块匿名共享内存，它是SurfaceFlinger服务负责创建的。SurfaceFlinger服务创建完成这块匿名共享内存之后，
就会将其返回给窗口所运行在的进程。窗口所运行在的进程获得了这块匿名共享内存之后，就会映射到自己的进程空间来，因此，窗口的控件就可以在本进程内访问这块匿名共享内存了，
实际上就是往这块匿名共享内存填入UI数据。注意，这个过程执行完成之后，控件的UI还没有反映到屏幕上来，因为这时候将控件的UI数据填入到图形缓冲区而已。
窗口的UI的显示是WindowManagerService服务来控制的。因此，当窗口的所有控件都绘制完成自己的UI之后，窗口就会向WindowManagerService服务发送一个Binder进程间程通信请求。
WindowManagerService服务接收到这个Binder进程间程通信请求之后，就会请求SurfaceFlinger服务刷新相应的窗口的UI。
1)一个窗口的所有控件的UI都是绘制在窗口的绘图表面上的，也就是说，一个窗口的所有控件的UI数据都是填写在同一块图形缓冲区中；
2)一个窗口的所有控件的UI的绘制操作是在主线程中执行的，事实上，所有与UI相关的操作都是必须是要在主线程中执行，否则的话，就会抛出一个类型为CalledFromWrongThreadException的异常来。