一 内存
Android Studio 3.0 Memory Profiler使用:https://www.jianshu.com/p/e75680772375
Android Studio调试内存问题：https://www.cnblogs.com/onelikeone/p/7115259.html
1 内存优化工具
ps:华为、oppo手机无法使用profile工具，会闪退
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
1>GC按钮/Force garbage collection:
你怀疑A页面发生了内存泄漏的话，可以在打开A页面之前，观察记录此时的内存的使用情况;
然后打开A页面进行操作，操作结束后，进行GC，GC后再观察此时的total和Java的内存使用情况，看看内存是否存在只增不减的情况，来判断你的页面是否发生了内存泄漏
2>Dump Java Heap按钮:
捕获一个heap dump
一个时间点的内存情况。够通过它观察出哪些对象占用了巨量内存，并且能够找出它们被什么对象把持住，导致无法释放。点击Dump Java Heap后，APP会Freeze住。然后就是等待，这个时候最好别做其他事情，否则可能失败。大概几十秒后，就会进入读取hprof文件的界面了。
 Instance View;References
 使用介绍：
 当点击app heap下拉列表会出现3个选项
 Default heap: 这个我也不太明白是什么意思
 App heap: app中的堆分配
 Image heap: 图像的堆分配
 Zygote heap: 这个按照官方的解释是来自安卓系统fork进程的地方产生的写数据备份
 
 当点击Arrange by class下拉列表会出现3个选项
 Arrange by class:根据类名进行分组
 Arrange by package:根据包名进行分组
 Arrange by callstack:根据调用栈进行分配(这个目前也不是太理解)
 
 Heap Dum 内存信息 :
 ① Allocations : 对象个数 , 一般情况下一个对象只有一个 , 如果出现多个 , 就要考虑是否有内存泄漏问题 ; 
 ② Shallow Size : 对象占用内存大小 ; 
 ③ Retained Set : 对象引用组的内存 ;
 
 当我们点击其中一个类的时候会弹出一个新的Instance View面板
 每列中包括以下：
 Depth: GC root到达该实例的最短跳数.
 Native Size: c/c++层中内存的大小(bytes)
 Shallow Size:java层内存大小(bytes)
 Retained Size:这个类中所引用到的对象的总大小(bytes)
 另外补充一下，heap dump是看不到调用栈信息的.也就是上图中的Call Stack面板.
 
 分析你的heap,按照一下步骤.
 1,浏览Class Name列表,看看有没有大量对象存在，并且这些对象你认为是不应该存在的，可能存在内存泄漏的情况. 点击类名可以看到详细的对象信息.
 2,在这个Instance View面板中，点击一个实例References面板就会显示出来，里面都是使用该Instance的Reference，点击剪头可以看到引用它的所有区域。点击鼠标右键可以选择go to instance去看到引用该引用的引用，或者jump to source去看调用的源代码.
 另外heap dump也是可以保存成为HPROF文件的,点击如下按钮即可保存起来，用于以后分析，或用作其它工具分析
 
 自动检测 Activity / Fragment 的内存泄漏 : 选中 Activity / Fragment Leaks 复选框 , 该工具会自动分析 Activity / Fragment 的内存泄漏问题 ;
 
 
3>Allocation/Record按钮-Record memory allocations:
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

Dalvik Debug Monitor Server (DDMS) 是 ADT插件的一部分，其中有两项功能可用于内存检查 : 
·heap  查看堆的分配情况 
·allocation tracker跟踪内存分配情况 
DDMS 这两项功能有助于找到内存泄漏的操作行为。 
Eclipse Memory Analysis Tools (MAT) 是一个分析 Java堆数据的专业工具，用它可以定位内存泄漏的原因。

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

5内存相关知识点
1 方法区:
  1.又叫静态区，跟堆一样，被所有的线程共享。方法区包含所有的class和static变量。
  2.方法区中包含的都是在整个程序中永远唯一的元素，如class，static变量。

二 帧率
三 CPU
四 安全

 
    
新的方式
1 移动端堆栈关键行定位的新思路：https://developer.aliyun.com/article/777602?spm=a2c6h.12873639.0.0.2aa7489fMxCiOr&groupCode=othertech

五 具体场景
Java内存优化和性能优化的几点建议:https://www.cnblogs.com/iwideal/p/7699527.html
1 静态成员变量
(1)静态变量
java 静态变量太多的优化方法：https://www.jianshu.com/p/8b9d77a1e207
https://www.cnblogs.com/rese-t/p/7858423.html
当某个对象被定义为stataic变量所引用，这个对象所占有的内存将不会被回收。有时，开发者会将经常调用的对象或者变量定义为static，以便提高程序的运行性能。因此，不是常用到的对象或者变量，不要定义为static类型的变量，尤其是静态类对象的定义，一定要仔细考虑是否有必要。
在程序运行期间,类中的静态变量其内存空间对所有该类的实例是共享的，因此在某些时候，为了节省内存空间开销，共享资源，
我们可以将类中的变量声明为静态变量。
但是因为静态变量生命周期太长，并且不易被系统回收，所有如果使用不合理，就会适得其反，从而造成大量内存的浪费。
因此建议在全部符合下列条件的情况下才使用静态变量:
（1）变量所包含的对象体积较大，占用内存较多
（2）变量所包含的对象生命周期较长
（3）变量所包含的对象数据稳定
（4）该类的实例 有 对该变量包含对象的共享需求
(2)静态变量vs单例模式
静态成员：
静态变量中的静态，指的是在整个程序运行过程之处就被分配存储空间，不需要后续调用之时再动态分配的变量。且只有在整个程序运行完毕之后，静态成员变量才会被GC销毁。
所有类的对象共享一个类的静态变量，JVM遇到静态成员时，会为其指向到静态区，而非在而不是自己再初始化一个新的。所以对于一些类似SharedPreferences的工具，需要反复使用，并运用同一个不需要改变的变量时，多采用static关键字(有必要时配合final关键字)有助于见效内存消耗。
单例模式：
单例模式也是以静态成员为基础。
其中懒汉式单例模式相对于静态成员的优势在于，可以在需要用到时候再进行初始化，节省一定资源。
饿汉式单例模式相比静态成员，有继承，多态等形式
(3)使用误区
Android static静态成员变量的使用误区：https://blog.csdn.net/amazing_alex/article/details/46519957
1)引发异常：在一些不规范的代码中经常看到Activity或者是Service当中定义许多静态成员属性。这样做可能会造成许多莫名其妙的null pointer异常。
异常分析：Java虚拟机的垃圾回收机制会主动回收没有被引用的对象或属性。在内存不足时，虚拟机会主动回收处于后台的Activity或Service所占用的内存。当应用再次去调用静态属性或对象的时候，就会造成null pointer异常 占用的内存。当应用再次去调用静态属性或对象的时候，就会造成null pointer异常
解决异常：Application在整个应用中，只要进程存在，Application的静态成员变量就不会被回收，不会造成null pointer异常
(4) public class X{
        static Y a = new Y();
    }
    类X创建了，没有被回收的话，静态变量a一直占用内存。
(5) 静态变量vs实例变量
对于静态变量在内存中只有一个拷贝（节省内存），JVM只为静态分配一次内存，在加载类的过程中完成静态变量的内存分配，可用类名直接访问（方便），当然也可以通过对象来访问（但是这是不推荐的）。
对于实例变量，没创建一个实例，就会为实例变量分配一次内存，实例变量可以在内存中有多个拷贝，互不影响（灵活）。
(6)静态代码块
static代码块也叫静态代码块，是在类中独立于类成员的static语句块，可以有多个，位置可以随便放，它不在任何的方法体内，JVM加载类时会执行这些静态的代码块，如果static代码块有多个，JVM将按照它们在类中出现的先后顺序依次执行它们，每个代码块只会被执行一次，所以说static块可以用来优化程序性能。

2 充分利用单例机制
 实用单例可以减少对资源的加载，缩短运行的时间，提高系统效率。但是，单例并不是所有地方都适用于。简单来说，单例可以适用于以下两个方面：
1.   控制资源的使用，通过线程同步来控制资源的并发访问；
2.   控制实例的产生，以达到节约资源的目的；

3 尽量使用基本数据类型代替对象
String str = "hello";
上面这种方式会创建一个“hello”字符串，而且JVM的字符缓存池还会缓存这个字符串。
String str = new String("hello");
此时程序除创建字符串外，str所引用的String对象底层还包含一个char[]数组，这个char[]数组依次存放了h,e,l,l,o

4 使用移位来代替乘法或者除法（'a/b'，仅适合2^n情况）的操作

5 避免不必要的对象创建
A a = new A();
if(i==1){list.add(a);}
应该改为
if(i==1){
A a = new A();
list.add(a);}

6 减少对变量的重复计算
for(int i=0;i<list.size();i++)
应该改写为：
for(int i=0,len=list.size();i<len;i++)
或者
for(int i = list.size(); I > -1; i--)
并且在循环中应该避免使用复杂的表达式，在循环中，循环条件会被反复计算，如果不使用复杂表达式，而使循环条件值不变的话，程序将会运行的更快。

7 尽量使用局部变量
调用方法时传递的参数以及在调用中创建的临时变量都保存在分配给改方法的栈（Stack）中，速度较快。其他变量，如静态变量、实例变量等，都在堆（Heap）中创建，速度较慢。

8 使用final修饰符
带有final修饰符的类是不可派生的。在Java核心API中，有许多应用 final的例子，例如java.lang.String。为String类指定final防止了使用者覆盖length()方法。另外，如果一个类是 final的，则该类所有方法都是final的。Java编译器会寻找机会内联（inline）所有的final方法（这和具体的编译器实现有关）。此举能够使性能平均提高50%。


Perfetto
Perfetto分析进阶: https://blog.csdn.net/feelabclihu/article/details/126672666?spm=1001.2014.3001.5502

