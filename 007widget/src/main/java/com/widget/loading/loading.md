1 原生Loading控件：ProgressDialog android.app.ProgressDialog
   https://blog.csdn.net/lpCrazyBoy/article/details/80758619
   (1)普通的圆形进度条对话框
   (2)普通的条形进度条对话框，不更新进度
   (3)普通的条形进度条对话框，更新进度

2 原生Loading控件：ProgressBar android.widget.ProgressBar
  ProgressBar的几个常用属性和方法
  android:max=”200”    滚动条最大值
  android:progress=”0” 滚动条当前值
  android:visibility=”visible”  滚动条是否可见
  style滚动条样式：progressBarStyleHorizontal一个长条形,progressBarStyleLarge一个大圆形样式
  setProgress(int) 设置当前值

  ProgressBar有两个进度，一个是android:progress，另一个是android:secondaryProgress。
  后者主要是为缓存需要所涉及的，比如在看网络视频时候都会有一个缓存的进度条以及还要一个播放的进度，
  在这里缓存的进度就可以是android:secondaryProgress，而播放进度就是android:progress。

  https://www.jianshu.com/p/3e3bcadf90ea
  https://www.cnblogs.com/salam/archive/2010/10/06/1844703.html
  (1) 普通的ProgressBar
  (2) 有进度的ProgressBar
  (3) 自定义有进度ProgressBar

3 ProgressDialog vs ProgressBar
  (1)ProgressDialog是动态显示出来 并且遮挡住Activity 此时不能进行交互
而ProgressBar是静态显示在布局中的  加载过程中 同时可以与Activity进行交互
  (2)ProgressDialog进度条对话框是在加载数据的时候，弹出的一个对话框，会将部分Activity遮住
ProgressBar进度条是在页面中嵌入的一个组件，不是弹出的层
