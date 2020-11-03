一 内存
Android Studio 3.0 Memory Profiler使用:https://www.jianshu.com/p/e75680772375
Android Studio调试内存问题：https://www.cnblogs.com/onelikeone/p/7115259.html
1 内存优化工具
(1)profile
1)关于顶部的几种内存类型介绍：
Java : java代码分配的内存
Native:c/c++代码分配的内存(有时候其实并没有使用到c/c++代码,但还是会有Native的内存分配，因为Android Framework会去通过java代码访问一些需要使用Native的资源，如图像资源Bitmap)
Graphics:图像缓存等，包括GL surfaces, GL textures等.
Stack:栈内存（包括java和c/c++）
Code:代码的内存分配（例如代码，资源，libs等等）
Other:这个是连系统都不知道是什么类型的内存，放在这里.
Allocated: jave分配的对象个数 (在Android7.1和以下的设备，这个计数是在设备连接后开始，所以并不是app启动时候的计数。Android8.0或更高，在系统里面内置的Profiler工具，所以无论什么时候连接，都是app启动时候的计数)

Class Name : 这个很好理解，就是类名
Alloc Count : 对象个数
Native Size : c/c++层内存大小(bytes)
Shallow Size : java层内存大小(bytes)
Retained Size : 这个是这个类中所引用到的对象的总大小 * 该类对象的个数

Allocations：堆中的实例数。
Shallow Size：此堆中所有实例的总大小（以字节为单位）。其实算是比较真实的java堆内存
Retained Size：为此类的所有实例而保留的内存总大小（以字节为单位）。这个解释并不准确，因为Retained Size会有大量的重复统计
native size：8.0之后的手机会显示，主要反应Bitmap所使用的像素内存（8.0之后，转移到了native）

2)相关操作
GC:
你怀疑A页面发生了内存泄漏的话，可以在打开A页面之前，观察记录此时的内存的使用情况;
然后打开A页面进行操作，操作结束后，进行GC，GC后再观察此时的total和Java的内存使用情况，看看内存是否存在只增不减的情况，来判断你的页面是否发生了内存泄漏
Dump Java Heap:
一个时间点的内存情况。够通过它观察出哪些对象占用了巨量内存，并且能够找出它们被什么对象把持住，导致无法释放。点击Dump Java Heap后，APP会Freeze住。然后就是等待，这个时候最好别做其他事情，否则可能失败。大概几十秒后，就会进入读取hprof文件的界面了。
 Instance View;References
Allocation:
记录一段区间内各个线程各个方法的内存分配情况

3)如何看Profiler的Memory图
第一：看整体Java内存使用看shallowsize就可以了
第二：想要看哪些对象占用内存较多，可以看Retained Size，不过看Retained Size的时候，要注意过滤一些无用的比如 FinalizerReference，基本类型如：数组对象

总体Java内存使用看shallow size
retained size只是个参考，不准确，存在各种重复统计问题
FinalizerReference retained size 大小极其不准确，而且其强引用的对象并没有被算进去，不过finilize确实可能导致内存泄漏
native size再8.0之后，对Bitmap的观测有帮助。

4）查找内存泄漏有以下几个方式
1,一般我排查内存泄漏的方式是，启动应用，看一下当前内存使用了多少，使用应用一段时间后（当然你不想亲自动手点来点去，也可以使用monkey进行一次自动化测试）, 退回到应用首页，看看当前内存又是多少。进行一次heap dump, 看看结果，分析一下有没有可疑的对象分配（比如说大量重复的Activity，同一个类型对象比较多，对象内存占用较大）.
2,发现可疑点后，通过分析结果，可以找到相应代码，找到代码当然也能找到使用代码的场景，例如是Activity泄漏，反复进行画面的跳转（如果你的应用支持横竖平切换的话，也可以反复旋转屏幕），然后强制gc回收，看看内存是否存在只增不减的情况.
3,也可以使用allocation跟踪一段时间内存分配情况，拿出来做分析
4,最后推荐一款leakcanary工具使用（具体可看：https://github.com/square/leakcanary）
当然如果时间允许的话，以上每个步骤都需要进行详细测试。


2 内存优化方面
(1)内存溢出
内存溢出是程序申请的内存，超出系统所能分配的范围，从而发生内存溢出。

(2)内存泄漏
Android studio profile查看内存泄漏:https://blog.csdn.net/u010513497/article/details/103064655
内存泄漏是程序在运行过程中，无法释放不再使用的内存空间的情况，简单的说，本应该回收的内存,不能被回收。一次内存泄漏的危害可以忽略，
但要是不断堆积就会使程序内存吃紧，不断地申请新内存，不断GC（垃圾回收），使程序变得缓慢，卡顿等。
Android中常见内存泄漏：
1）静态引用引用了该释放的对象
2）非静态内部类
3）Webview
4）资源未释放
