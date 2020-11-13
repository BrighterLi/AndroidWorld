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

(2)MAT
MAT(Memory Analyzer Tool)：细看，强引用，导致内存泄漏的引用
从众多的对象中进行分析，快速的计算出在内存中对象的占用大小，看看是谁阻止了垃圾收集器的回收工作，并可以通过报表直观的查看到可能造成这种结果的对象
下载：https://www.eclipse.org/mat/downloads.php
把dump下载的文件memory-20190828T162317.hprof拖进platform-tools文件夹
敲入cmd命令hprof-conv [-z] memory-20190828T162317.hprof 1.hprof转成可被MAT识别的1.hprof文件 (两个文件要带上完整路径)
使用MAT打开1.hprof

2 内存优化方面
(1)内存溢出
内存溢出是程序申请的内存，超出系统所能分配的范围，从而发生内存溢出。

(2)内存泄漏
Android studio profile查看内存泄漏:https://blog.csdn.net/u010513497/article/details/103064655AndroidStudio 
内存泄漏是程序在运行过程中，无法释放不再使用的内存空间的情况，简单的说，本应该回收的内存,不能被回收。一次内存泄漏的危害可以忽略，
但要是不断堆积就会使程序内存吃紧，不断地申请新内存，不断GC（垃圾回收），使程序变得缓慢，卡顿等。
内存泄漏产生的原因在Android中大致分为以下几种：
https://www.cnblogs.com/kelina2mark/p/6140658.html
1.static变量引起的内存泄漏 
因为static变量的生命周期是在类加载时开始 类卸载时结束，也就是说static变量是在程序进程死亡时才释放，如果在static变量中 引用了Activity 
那么 这个Activity由于被引用，便会随static变量的生命周期一样，一直无法被释放，造成内存泄漏。
解决办法： 
在Activity被静态变量引用时，使用 getApplicationContext 因为Application生命周期从程序开始到结束，和static变量的一样。
2.线程造成的内存泄漏 
类似于上述例子中的情况，线程执行时间很长，及时Activity跳出还会执行，因为线程或者Runnable是Acticvity内部类，因此握有Activity的实例
(因为创建内部类必须依靠外部类)，因此造成Activity无法释放。 
AsyncTask 有线程池，问题更严重
解决办法： 
1.合理安排线程执行的时间，控制线程在Activity结束前结束。 
2.将内部类改为静态内部类，并使用弱引用WeakReference来保存Activity实例 因为弱引用 只要GC发现了 就会回收它 ，因此可尽快回收
3.BitMap占用过多内存 
bitmap的解析需要占用内存，但是内存只提供8M的空间给BitMap，如果图片过多，并且没有及时 recycle bitmap 那么就会造成内存溢出。
解决办法： 
及时recycle 压缩图片之后加载图片
4.资源未被及时关闭造成的内存泄漏 
比如一些Cursor 没有及时close 会保存有Activity的引用，导致内存泄漏
解决办法： 
在onDestory方法中及时 close即可
5.Handler的使用造成的内存泄漏 
由于在Handler的使用中，handler会发送message对象到 MessageQueue中 然后 Looper会轮询MessageQueue 然后取出Message执行，但是如果一个
Message长时间没被取出执行，那么由于 Message中有 Handler的引用，而 Handler 一般来说也是内部类对象，Message引用 Handler ，Handler引用 Activity 这样 使得 Activity无法回收。
解决办法： 
依旧使用 静态内部类+弱引用的方式 可解决
6.带参数的单例
如果我们在在调用Singleton的getInstance()方法时传入了Activity。那么当instance没有释放时，这个Activity会一直存在。因此造成内存泄露。
解决方法：
可以将new Singleton(context)改为new Singleton(context.getApplicationContext())即可，这样便和传入的Activity没关系了。


3 阿里课程
Android内存优化实战（一）：https://www.bilibili.com/video/BV1Jg4y1B7v3/?spm_id_from=333.788.videocard.11
Android内存优化实战（二）：https://www.bilibili.com/video/BV12T4y1g7zn/?spm_id_from=333.788.videocard.15
Android内存优化（三）常见问题及总结：https://www.bilibili.com/video/BV1Bt4y1y7zo/?spm_id_from=333.788.videocard.16
Android内存优化实战（完整版）:https://www.bilibili.com/video/BV1yT4y1u7Fm/?spm_id_from=333.788.videocard.9

内存抖动；短时间内有大量的对象创建销毁导致的，伴随着频繁的GC
eg:字符串的拼接造成内存抖动(每拼接一次都会创建StringBuilder)
影响：1)频繁的GC会导致卡顿，stop the world,工作线程被挂起，无法响应用户操作(表现为卡顿) 
      2) OOM(内存碎片，没有连续的满足要求空间大小的内存)
注意：1)尽量避免在循环与频繁调用的方法中创建对象 2)合理利用对象池(eg：Message.obtain)

内存泄漏：
原因：长生命周期对象持有短生命周期对象强引用，从而导致短生命周期对象无法被回收。
可达性

软引用：定义一些还有用但非必须的对象。软引用关联的对象，GC不会直接回收，而是在系统将要内存溢出之前才会触发GC进行回收。
弱引用：非必须对象。被弱引用关联的对象，GC执行时就会被直接回收。

工具：
profile：粗看
MAT(Memory Analyzer Tool)：细看，强引用，导致内存泄漏的引用

4 相关问题
(1)Android的 java程序为什么容易出现OOM
这个是因为Android系统对dalvik的vm heapsize作了硬性限制，当java进程申请的java空间超过阈值时，就会抛出OOM异常（这个阈值可以是48M、24M、16M等，视机型而定），可以通过adb shell getprop | grep dalvik.vm.heapgrowthlimit查看此值。

也就是说，程序发生OMM并不表示RAM不足，而是因为程序申请的java heap对象超过了dalvik vm heapgrowthlimit。也就是说，在RAM充足的情况下，也可能发生OOM。

这样的设计似乎有些不合理，但是Google为什么这样做呢？这样设计的目的是为了让Android系统能同时让比较多的进程常驻内存，这样程序启动时就不用每次都重新加载到内存
，能够给用户更快的响应。迫使每个应用程序使用较小的内存，移动设备非常有限的RAM就能使比较多的app常驻其中。但是有一些大型应用程序是无法
忍受vm heapgrowthlimit的限制的，后面会介绍如何让自己的程序跳出vm heapgrowthlimit的限制。

二 帧率
三 CPU
四 安全

 
    
新的方式
1 移动端堆栈关键行定位的新思路：https://developer.aliyun.com/article/777602?spm=a2c6h.12873639.0.0.2aa7489fMxCiOr&groupCode=othertech   