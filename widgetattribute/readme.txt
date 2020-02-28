控件属性实践
Image;TextView;
padding;MarginTop;Params;

TypedArray：属性的集合
    AttributeSet：XML文件中定义的View的属性的集合
    attrs： 这个View自身的属性集合，是一个int数组

Image
(1) android:layout_width="match_parent"
    android:layout_height="match_parent"

    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
(2) padding
    Margin
(3) android:gravity="center" 主要是对自身View的位置控制。
    android:layout_gravity="center_vertical"  主要用于设置View 或者 Layout 在其父组件中的对齐方式。

    RelativeLayout：
        水平垂直居中：android:layout_centerInParent = "true"
        水平居中:android:layout_centerHorizontal="true"
        竖直居中:android:layout_centerVertical="true"

        layout_gravity="center_vertical"和 layout_centerVertical="true"区别?
        都表示当前控件在父容器的位置是垂直居中,后者的父容器只能是相对布局
(4)  android:background="#b0c4de"
     android:background="@drawable/image
     背景可以是图片和颜色
(5) android:scaleType="fitXY"

(6) .9.png 作为背景
    https://blog.csdn.net/sunbinkang/article/details/77331718
        9patch图片的作用就是在图片拉伸的时候保证其不会失真。所以我们使用.9图片，让图片在指定的位置拉伸和在指定的位置显示内容，
    这样图片的边边角角就不会出现失真了。
        9patch和一般图片的区别在于.9图片有四条黑边，而一般的图片没有，这四条黑边就是用来拉伸和指定显示位置的。
        使用.9图片后，整个图片应该是包裹着你想要显示的内容的，而没有使用的话整个控件布局看起来特别糟糕。


(7) android代码中设置margin  LayoutParams
   LayoutParams继承于Android.View.ViewGroup.LayoutParams，相当于一个Layout的信息包，它封装了Layout的位置、高、宽等信息。
   LinearLayout.LayoutParams   //父布局是LinearLayout
   FrameLayout.LayoutParams  //父布局是FrameLayout
   RelativeLayout.LayoutParams //父布局是RelativeLayout


    自定义属性
    </declare-styleable>