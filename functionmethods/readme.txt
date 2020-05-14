一、各种方法功能的实现
1 android设备是否root
2 获取volte开关状态 0
3 反射 0
4 安卓获取状态栏
  改变状态栏颜色
     https://blog.csdn.net/maosidiaoxian/article/details/51734895
5 拍照0
6 截屏0
7 改变图片的颜色，将图片置灰0
8 Android 监听APP进入后台或切换到前台
 Android 监听APP进入后台或切换到前台方案对比:
  https://www.cnblogs.com/zhujiabin/p/9336663.html
  https://www.jianshu.com/p/80b6041f4409
  方案一：自己定义一个MyApplication继承Application
    在Application类中，提供了一个应用生命周期回调的注册方法，用来对应用的生命周期进行集中管理，这个接口叫registerActivityLifecycleCallbacks，可以通过它注册自己的ActivityLifeCycleCallback，每一个Activity的生命周期都会回调到这里的对应方法。
  方案二：直接在具体的某个 Activity中写一个判断是否在前台还是后台的方法，然后在 该Activity的 onStart()、onStop()中判断
    ActivityManager.RunningAppProcessInfo
    ActivityManager通过.getRunningAppProcesses()获取当前运行列表这个方法，在5.0以后已经被deprecated掉了
  方案三：监听Home键点击
9 防止按钮快速重复点击
 https://www.cnblogs.com/dingxiansen/p/10442255.html
10 Android实现电话录音与上传的功能(云服务器)/面对面录音；图片压缩；?
11 超时0

二、Android知识点
1 Toast、Dialog不能在子线程里弹出。
 否则会crash崩溃
 解决方式：先调用Looper.prepare();再调用Toast.makeText().show();最后再调用Looper.loop();
2 onKeyDown ?
3 ThreadLocal ?