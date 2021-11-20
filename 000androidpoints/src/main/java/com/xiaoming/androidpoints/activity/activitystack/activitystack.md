1、获取堆栈中Activity
Android 获取activity栈中activity: https://blog.csdn.net/yaojie5519/article/details/82252439
获取栈中所有activity的方法: https://www.jianshu.com/p/3f74a06a71a1
在开发中，有时需要获取自己APP栈中所有的activity，比如实现在某个activity中彻底退出整个APP，如果此时我们能够获取到当前栈中所有的activity，那么逐个finish掉就OK了。当然，要实现退出整个APP也不是一定要这么做，这只是一种思路而已。
方法1、获取activity栈（不行）
//先申请权限
  <uses-permission android:name="android.permission.GET_TASKS" />
  //获取activity任务栈
  ActivityManager activityManager=(ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
   //参数1是指最大任务栈数，一般APP也就只有一个任务栈
  ActivityManager.RunningTaskInfo runningTaskInfo = activityManager.getRunningTasks(1).get(0);
  方法倒是很简单，到这里我们就获取到任务栈的相关信息：RunningTaskInfo，然而里面只包含有像什么id啊，顶部activity名字啊，运行中activity个数等等信息，而无法获取到栈中所有activity实例，故无法达到目的。
方法2、基类收集
方法也非常简单，我们在自定义的Application中维护一个Activity实例列表：List<Activity> activitys
然后在activity基类中进行收集，比如在onCreate中add，在onDestroy中remove
这样就可以获取到栈中所有activity了
方法3、ActivityLifecycleCallbacks收集
上面的方法可行是可行，就是得在基类中进行收集，如果某个activity不继承基类，就收集不了，而使用ActivityLifecycleCallbacks却能解决这个问题
方法4、反射大法
通常来说，很多功能的实现最终总可以通过发射来完成的

2、获取App中所有Activity
Android获取自己App中的所有Activity: https://www.jianshu.com/p/8c544013a9fb