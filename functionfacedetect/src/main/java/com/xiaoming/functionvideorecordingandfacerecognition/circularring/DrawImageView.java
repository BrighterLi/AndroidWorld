package com.xiaoming.functionvideorecordingandfacerecognition.circularring;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

//【android】绘制圆环的三种方式：https://blog.csdn.net/mp624183768/article/details/78870551
public class DrawImageView extends View {

    private final Paint paint;
    private final Context context;
    public DrawImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.paint = new Paint();
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getMeasuredHeight();
        Log.d("DrawImageView", "bright9#onMeasure#getMeasuredHeight：" + getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //getMeasuredWidth():视图绘制的measure阶段，获取的是View原始的大小，也就是这个View在XML文件中配置或者是代码中设置的大小
        //getWidth:layout阶段获取的是这个View最终显示的大小，这个大小有可能等于原始的大小，也有可能不相等
        int center = getWidth()/2;
        Log.d("DrawImageView", "bright9#onDraw#getWidth：" + getWidth());
        int innerCircle = dip2px(context, 83); //内圆半径
        int ringWidth = dip2px(context, 10);   //圆环宽度

        // 第一种方法绘制圆环
        //绘制内圆
        this.paint.setARGB(255, 138, 43, 226);
        //this.paint.setColor(0x407AFFFF);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, this.paint);

        //绘制圆环
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 +ringWidth/2, this.paint);

        //绘制外圆
        this.paint.setARGB(255, 138, 43, 226);
        this.paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, this.paint);

        super.onDraw(canvas);



    }


    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
