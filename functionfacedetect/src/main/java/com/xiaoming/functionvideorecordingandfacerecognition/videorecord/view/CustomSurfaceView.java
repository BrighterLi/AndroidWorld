package com.xiaoming.functionvideorecordingandfacerecognition.videorecord.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import com.xiaoming.functionvideorecordingandfacerecognition.videorecord.utils.ScreenUtil;

/**
 * 圆形SurfaceView
 * 这个SurfaceView 使用时 必须设置其background，可以设置全透明背景
 */

//https://blog.csdn.net/qunqunstyle99/article/details/80864214?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-8.nonecase
public class CustomSurfaceView extends SurfaceView {
    private Paint mPaint;
    private int mWidthSize;
    private Camera mCamera;
    private int mHeight;
    private static String TAG = "CustomSurfaceView";
    private Context mContext;

    public CustomSurfaceView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public CustomSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public CustomSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    private void initView() {
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }

    //负责对当前View的尺寸进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取总宽度,是包含padding值
        mWidthSize = MeasureSpec.getSize(widthMeasureSpec);
        Log.v(TAG, "bright#onMeasure#mWidthSize: " + mWidthSize);
        //获取屏幕长宽比例，这样设置不会发生畸变，千万不要根据一个手机设定一个数
        //那样换一个手机就可能会出现显示的比例问题
        int screenWidth = ScreenUtil.getScreenWidth(getContext());
        int screenHeight = ScreenUtil.getScreenHeight(getContext());
        //mHeight = 600;
        //可以理解为红色的背景盖住了大部分的区域，我们只能看到圆框里面的，如果还是按照原来的比例绘制surfaceview
        //需要把手机拿的很远才可以显示出整张脸，故而我用了一个比较取巧的办法就是，按比例缩小，试验了很多数后，感觉0.55
        //是最合适的比例
        double screenWidht2 = 0.65 * screenWidth; //可以通过改变0.65这个数字来改变圆形框大小
        double screenHeight2 = 0.65 * screenHeight;
        Log.v(TAG, "bright#onMeasure#widthMeasureSpec=" + screenWidth + " heightMeasureSpec= " + screenHeight);
        //绘制的输入参数必须是整数型，做浮点型运算后为float型数据，故需要做取整操作
        //存储测量得到的宽度和高度
    }

    //负责把当前这个View绘制出来
    //绘制一个圆形的框，并设置圆框的坐标和半径大小
    //这个绘制在16:9的手机上显示很好，但是在更长的手机上（大于16/9）会偏上，
    @Override
    public void draw(Canvas canvas) {
        Path path = new Path();
        //设置裁剪的圆心、半径
        path.addCircle(mWidthSize / 2, mWidthSize / 2, mWidthSize / 2, Path.Direction.CCW);
        Log.d(TAG, "bright9#draw#mWidthSize：" + mWidthSize);
        //裁剪画布，并设置其填充方式
        canvas.clipPath(path, Region.Op.REPLACE);
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
        //paint.setColor(0xFFFFFFFF);
        int center = getWidth()/2;
        Log.d(TAG, "bright9#getWidth()：" + getWidth());
        int innerCircle = dip2px(mContext, 160); //内圆半径
        int ringWidth = dip2px(mContext, 8);   //圆环宽度

        // 第一种方法绘制圆环
       //绘制内圆
        paint.setARGB(255, 138, 43, 226);
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, paint);

        //绘制圆环
        paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0x407AFFFF);
        //paint.setColor(0xFFFFFFFF);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 +ringWidth/2, paint);

        //绘制外圆
        paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0xFFFFFFFF);
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, paint);
        super.onDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        int screenWidth = ScreenUtil.getScreenWidth(getContext());
        int screenHeight = ScreenUtil.getScreenHeight(getContext());
        Log.v(TAG, "bright#onSizeChanged#screenWidth: " + Integer.toString(screenWidth));
        Log.v(TAG, "bright#onSizeChanged#screenHeight: " + Integer.toString(screenHeight));
        w = screenWidth;
        h = screenHeight;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
