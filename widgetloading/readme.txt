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

3 ProgressDialog vs ProgressBar
  (1)ProgressDialog是动态显示出来 并且遮挡住Activity 此时不能进行交互
而ProgressBar是静态显示在布局中的  加载过程中 同时可以与Activity进行交互
  (2)ProgressDialog进度条对话框是在加载数据的时候，弹出的一个对话框，会将部分Activity遮住
ProgressBar进度条是在页面中嵌入的一个组件，不是弹出的层
