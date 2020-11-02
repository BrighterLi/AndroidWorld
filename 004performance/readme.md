1 内存
Android Studio 3.0 Memory Profiler使用:https://www.jianshu.com/p/e75680772375
Android Studio调试内存问题：https://www.cnblogs.com/onelikeone/p/7115259.html
profile关于顶部的几种内存类型介绍：
Java : java代码分配的内存
Native:c/c++代码分配的内存(有时候其实并没有使用到c/c++代码,但还是会有Native的内存分配，因为Android Framework会去通过java代码访问一些需要使用Native的资源，如图像资源Bitmap)
Graphics:图像缓存等，包括GL surfaces, GL textures等.
Stack:栈内存（包括java和c/c++）
Code:代码的内存分配（例如代码，资源，libs等等）
Other:这个是连系统都不知道是什么类型的内存，放在这里.
Allocated: jave分配的对象个数 (在Android7.1和以下的设备，这个计数是在设备连接后开始，所以并不是app启动时候的计数。Android8.0或更高，在系统里面内置的Profiler工具，所以无论什么时候连接，都是app启动时候的计数)
(1)内存溢出
(2) 内存泄漏
demo
(3)
GC
Dump Java Heap
够通过它观察出哪些对象占用了巨量内存，并且能够找出它们被什么对象把持住，导致无法释放。点击Dump Java Heap后，APP会Freeze住。然后就是等待，这个时候最好别做其他事情，否则可能失败。大概几十秒后，就会进入读取hprof文件的界面了。
Allocation
记录一段区间内各个线程各个方法的内存分配情况。