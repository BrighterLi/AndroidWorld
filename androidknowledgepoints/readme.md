一 知识点分类
1 View
时钟；浮标；
组件
下拉刷新；
2 net
域名劫持；跨域访问；Http,Https；
3 thread
4 性能
卡顿优化;Bugly;APM;内存优化；native crash;
图片优化；
网络优化；
5 框架
okhttp+rxjava+retrofit原理；
easypermissions;
eventBus;
Dagger;
RxLifeCycle;
6 架构
mvc;mvp;
重构；
7 工具
Debug;
jekens；gradle;maven
8 组件
列表选择器；
9 设计模式
10 跨端
11 屏幕适配
12 数据挖掘与分析
13 深度学习
14 C++
ndk;
15 JVM
16 新技术
热修复；AOP;

二 零散知识点
1 jni
Android JNI学习——Demo演示:https://www.jianshu.com/p/0f34c097028a
靠谱Android Studio JNI Demo：https://msd.misuland.com/pd/3255817997595447088

（NDK开发）javah 命令找不到类文件的解决方法:https://blog.csdn.net/huangbryant/article/details/79403733?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.nonecase

2 注解
3 动态代理
4 点击Button/屏幕接下来的流程
5 sql
6 flutter.gradle
7 爬虫
8 反射
9 扫二维码
10 lifecycle
11 重定向
12 Handler
13 脚本注入
14 代码混淆
Android Studio系列之代码混淆proguardFiles：https://blog.csdn.net/fine1938768839/article/details/75529260
15 自定义异常
16 堆栈
17 hook
18 自定义权限
19 加密解密
base64;
20 单元测试
21 ThreadLocal
22 断言
23 图片的高宽
24 保活
25 java内部类访问局部变量时局部变量必须声明为final
 因为生命周期的原因。方法中的局部变量，方法结束后这个变量就要释放掉，final保证这个变量始终指向一个对象。
 首先，内部类和外部类其实是处于同一个级别，内部类不会因为定义在方法中就会随着方法的执行完毕而跟随者被销毁。
 问题就来了，如果外部类的方法中的变量不定义final，那么当外部类方法执行完毕的时候，这个局部变量肯定也就被GC了，
 然而内部类的某个方法还没有执行完，这个时候他所引用的外部变量已经找不到了。如果定义为final，
 java会将这个变量复制一份作为成员变量内置于内部类中，这样的话，由于final所修饰的值始终无法改变，所以这个变量所指向的内存区域就不会变。
 为了解决：局部变量的生命周期与局部内部类的对象的生命周期的不一致性问题
26 阻塞非阻塞；同步异步
Java之阻塞和非阻塞以及同步和异步的区别：https://blog.csdn.net/u011109589/article/details/80333775
同步和异步是相对于操作结果来说，会不会等待结果返回。
阻塞就是说在煮水的过程中，你不可以去干其他的事情，非阻塞就是在同样的情况下，可以同时去干其他的事情。阻塞和非阻塞是相对于线程是否被阻塞。

先说AlertDialog，弹出来之后，背面会变灰，并没有阻塞后台的进程，如果没特殊控制，点击后面灰暗处，弹框会消失掉的。
至于PopupWindow，则是弹出来，后面没有任何变化，并且阻塞该应用的进程，如果一直没退出，应用汇一直等待，点击后面也是没有反应的。
27 多个Dialog控制一个一个顺序弹出
28 动画使用Lottie
   Lottie- 让Android动画实现更简单:https://www.jianshu.com/p/cae606f45c0b
29 hook lancet
30 json
 (1)JSON (JavaScript Object Notation) 一种简单的数据格式，是一种比较轻量级的数据存储格式；
 只是一种数据结构，可以理解为JSON格式的数据结构（key-value 结构），可以使用put方法给json对象添加元素。JSONObject可以很方便的转换成字符串，也可以很方便的把其他对象转换成JSONObject对象。
 {} 双括号表示对象
 JSON的三种解析方式：https://www.cnblogs.com/zhujiabin/p/5498272.html
 (2)Gson
 (3) json的存在意义（json和String的区别）
 String字符串:
 var str1 = '{ "name": "cxh", "sex": "man" }'; 
 JSON对象:
 var str2 = { "name": "cxh", "sex": "man" };
 那明明一种形式能用String表示，干嘛非得费劲地整成json格式呢？-----那是因为json在获取内容方面比String更方便
 如果你在前台使用，那么Json对象可以通过xx.name来调用获取；如果是字符串，那就只能是字符串，你就只能各种切割各种分组获得name，数据一多看不切割死你，烦不胜烦... ...
 (4)Json对象和Json字符串的区别
  https://www.cnblogs.com/wxh0929/p/11132073.html
  Json对象:最显著的特征：对象的值可以用 “对象.属性” 进行访问
  var person={"name":"shily","sex":"女","age":"23"}//json对象
  Json字符串:（所谓字符串：单引号或者双引号引起来）
  var personStr='{"name":"shily","sex":"女","age":"23"}';//json字符串
  Json字符串转化为Json对象:
  var  strToObj = JSON.parse(personStr);
  
31 ASM插桩无埋点性能监控
32 静默安装
33 闪屏(图片、gif、视频)
Android启动页面（闪屏页面）的实现：https://blog.csdn.net/PlainWaterh/article/details/78184847
34 android设备是否root
35 获取volte开关状态 0
36 反射 0
37 安卓获取状态栏
  改变状态栏颜色
     https://blog.csdn.net/maosidiaoxian/article/details/51734895
38 拍照0
39 截屏0
40  改变图片的颜色，将图片置灰0
41 Android 监听APP进入后台或切换到前台
 Android 监听APP进入后台或切换到前台方案对比:
  https://www.cnblogs.com/zhujiabin/p/9336663.html
  https://www.jianshu.com/p/80b6041f4409
  方案一：自己定义一个MyApplication继承Application
    在Application类中，提供了一个应用生命周期回调的注册方法，用来对应用的生命周期进行集中管理，这个接口叫registerActivityLifecycleCallbacks，可以通过它注册自己的ActivityLifeCycleCallback，每一个Activity的生命周期都会回调到这里的对应方法。
  方案二：直接在具体的某个 Activity中写一个判断是否在前台还是后台的方法，然后在 该Activity的 onStart()、onStop()中判断
    ActivityManager.RunningAppProcessInfo
    ActivityManager通过.getRunningAppProcesses()获取当前运行列表这个方法，在5.0以后已经被deprecated掉了
  方案三：监听Home键点击
42 防止按钮快速重复点击
 https://www.cnblogs.com/dingxiansen/p/10442255.html
43 Android实现电话录音与上传的功能(云服务器)/面对面录音；图片压缩；?
44 超时0
45 Toast、Dialog不能在子线程里弹出。
 否则会crash崩溃
 解决方式：先调用Looper.prepare();再调用Toast.makeText().show();最后再调用Looper.loop();
46 onKeyDown ?
47 ThreadLocal ?
48 编码
unicode;UTF-8,UTF-9,UTF-32;ASCII;GB2312,GBK;Base64
(1)Unicode 是「字符集」,UTF-8 是「编码规则」
     字符集：为每一个「字符」分配一个唯一的 ID（学名为码位 / 码点 / Code Point）,编码规则：将「码位」转换为字节序列的规则（编码/解码 可以理解为 加密/解密 的过程）
     每个国家都像中国一样，把自己的语言编码，于是出现了各种各样的编码，如果你不安装相应的编码，就无法解释相应编码想表达的内容。终于，有个叫 ISO 的组织看不下去了。
他们一起创造了一种编码 UNICODE ，这种编码非常大，大到可以容纳世界上任何一个文字和标志。所以只要电脑上有 UNICODE 这种编码系统，无论是全球哪种文字，
只需要保存文件的时候，保存成 UNICODE 编码就可以被其他电脑正常解释。
     UNICODE 在网络传输中，出现了两个标准 UTF-8 和 UTF-16，分别每次传输 8个位和 16个位。于是就会有人产生疑问，UTF-8 既然能保存那么多文字、符号，
为什么国内还有这么多使用 GBK 等编码的人？因为 UTF-8 等编码体积比较大，占电脑空间比较多，如果面向的使用人群绝大部分都是中国人，用 GBK 等编码也可以。

(2) Ascii编码（American Standard Code for Information Interchange，美国信息互换标准代码）
当时世界上所有的计算机都用同样的ASCII方案来保存英文文字,把所有的空 
格、标点符号、数字、大小写字母分别用连续的字节状态表示，一直编到了第127号，这样计算机就可以用不同字节来存储英语的文字了。
(3)GB2312
GB2312 是对 ASCII 的中文扩展。结果扩展之后的编码方案被称为 GBK 标准，GBK包括了GB2312 的所有内容，同时又增加了近20000个新的汉字（包括繁体字）和符号。 后来少数民族也要用电脑了，
于是我们再扩展，又加了几千个新的少数民族的字，GBK扩成了 GB18030。
49 从数据到后台到前端
(1)后台用django写的，数据是python爬
50 try{}catch{}finally{}
try{}catch{}finally{}中，如果finally{}中的返回值＋1，返回的是多少，会改变返回结果吗?
java中关于try、catch、finally中的细节分析:https://blog.csdn.net/mxd446814583/article/details/80355572
总结:
1)try、catch、finally语句中，如果只有try语句有return返回值，此后在catch、finally中对变量做任何的修改，都不影响try中return的返回值。
2)try、catch中有返回值，而try中抛出的异常恰好与catch中的异常匹配，则返回catch中的return值。
3)如果finally块中有return 语句，则try或catch中的返回语句忽略。
4)如果finally块中抛出异常，则整个try、catch、finally块中抛出异常.并且没有返回值。
所以在使用try、catch、finally语句块时需要注意以下几点：
1)尽量在try或者catch中使用return语句。通过finally块中达到对try或者catch返回值修改是不可行的。
2)finally块中避免使用return语句，因为finally块中如果使用return语句，会显示的忽略掉try、catch块中的异常信息，屏蔽了错误的发生。
3)finally块中避免再次抛出异常，否则整个包含try语句块的方法回抛出异常，并且会忽略掉try、catch块中的异常。

三 相关Demo
1 一款Android图文精选app，通过抓取网页获得图文列表。目前包含猫弄（MONO）早午茶、站酷（Zcool）精选、国家地理（National Geographic）每日一图、知乎日报、豆瓣一刻（Moment）。
https://github.com/XunMengWinter/Now
2 天气预报
天气预报API接口：http://tianqiapi.com/
