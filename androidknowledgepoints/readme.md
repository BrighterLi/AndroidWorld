一 知识点分类
1 View
时钟；
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
jekens；gradle
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

三 相关Demo
一款Android图文精选app，通过抓取网页获得图文列表。目前包含猫弄（MONO）早午茶、站酷（Zcool）精选、国家地理（National Geographic）每日一图、知乎日报、豆瓣一刻（Moment）。
https://github.com/XunMengWinter/Now
