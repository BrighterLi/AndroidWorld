自定义View—组合方式
自定义标题栏并使用
https://blog.csdn.net/qq1271396448/article/details/78686052
实现功能：
(1) 自定义View标题栏布局
(2) 灵活的可以自己传入类型,选择所需要的控件来显示隐藏
(3) 免继承,可直接在布局里使用
(4) 直接可以在布局控件里设置属性

知识点：
(1) TypedArray：属性的集合
    AttributeSet：XML文件中定义的View的属性的集合
    attrs： 这个View自身的属性集合，是一个int数组
(2) android:gravity 主要是对自身View的位置控制。
    android:layout_gravity 主要用于设置View 或者 Layout 在其父组件中的对齐方式。
    RelativeLayout：
    水平垂直居中：android:layout_centerInParent = "true"
    水平居中:android:layout_centerHorizontal="true"
    竖直居中:android:layout_centerVertical="true"
(3) 自定义属性
     </declare-styleable>