1 Rxjava,RxAndroid
***经典案例：RxAndroid从零开始学习之一(RxJava的简单Demo):https://cloud.tencent.com/developer/article/1383095
(1)RxAndroid其实只是RxJava的一个扩展，它在RxJava的基础上添加了一些针对Android系统的新API。
RxJava是用来解决异步工作问题的，在Android中特别恼火的多线程开发问题上游刃有余
(2)Observable,Subscribers
开发的人都知道监听者模式，订阅模式这些概念。而Observable和Subscribers的英文意思就是如此。我们大概也知道差不多和监听者模式差不多。
Observable事件源，被观察者。
Subcriblers 观察者，事件订阅者
Observer 同Subcribler差不多
subscribe() 方法，绑定Observable与Subcribler或者Observabler
很显然，Observable对象发生动静，然后通信Subcribers，然后Subcribers实现自己的业务逻辑。

2 Demo
Android 使用Rxjava和OkHttp3封装文件下载器: https://blog.csdn.net/shuaizhigen/article/details/88829887?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.control
使用Retrofit+RxJava实现带进度下载文件: https://blog.csdn.net/jiashuai94/article/details/78775314?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-2.control
使用Retrofit+RxJava下载文件并实现APP更新:https://blog.csdn.net/liu362732346/article/details/83987778?utm_medium=distribute.pc_feed_404.none-task-blog-BlogCommendFromBaidu-5.nonecase&depth_1-utm_source=distribute.pc_feed_404.none-task-blog-BlogCommendFromBaidu-5.nonecas

