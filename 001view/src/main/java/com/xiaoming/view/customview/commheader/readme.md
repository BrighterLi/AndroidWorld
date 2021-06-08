
1 布局
(1) 多个View在同一水平线上
(2) 三个View,其中两个View靠左显示，另外一个View靠右显示
方法一:
外层是Releative，左边两个View放在LinearLayout或者ReletiveLayout(ReletiveLayout更好可以控制里面的每个子View)，\
右边的View使用android:layout_alignParentRight="true"(控制靠右显示，中间空白部分宽度根据不同屏幕不同，起到适配作用)
方法二: