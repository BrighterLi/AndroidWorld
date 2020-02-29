图片本身大小；图片裁剪；dp,px;BitMap;阴影；
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
   1.android:scaleType=“center”
   保持原图的大小，显示在ImageView的中心。当原图的size大于ImageView的size时，多出来的部分被截掉。
   2.android:scaleType=“center_inside”
   以原图正常显示为目的，如果原图大小大于ImageView的size，就按照比例缩小原图的宽高，居中显示在ImageView中。如果原图size小于ImageView的size，则不做处理居中显示图片。
   3.android:scaleType=“center_crop”
   以原图填满ImageView为目的，如果原图size大于ImageView的size，则与center_inside一样，按比例缩小，居中显示在ImageView上。如果原图size小于ImageView的size，则按比例拉升原图的宽和高，填充ImageView居中显示。
   4.android:scaleType=“matrix”
   不改变原图的大小，从ImageView的左上角开始绘制，超出部分做剪切处理。
   5.androd:scaleType=“fit_xy”
   把图片按照指定的大小在ImageView中显示，拉伸显示图片，不保持原比例，填满ImageView.
   6.android:scaleType=“fit_start”
   把原图按照比例放大缩小到ImageView的高度，显示在ImageView的start（前部/上部）。
   7.android:sacleType=“fit_center”
   把原图按照比例放大缩小到ImageView的高度，显示在ImageView的center（中部/居中显示）。
   8.android:scaleType=“fit_end”
   把原图按照比例放大缩小到ImageView的高度，显示在ImageVIew的end（后部/尾部/底部）

(6) 阴影，边框
 .9.png 作为背景
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

(8) 图片拿过来时本身的大小

(9) 自定义属性
    </declare-styleable>