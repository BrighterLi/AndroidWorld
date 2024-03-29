package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FaceMaskView extends View {
    private Context mContext;
    private int mPaintColor;

    public FaceMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public void setmPaintColor(int mPaintColor) {
        this.mPaintColor = mPaintColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
        //paint.setColor(0xFFFFFFFF);
        int center = getWidth()/2;
        Log.d("FaceMaskView", "bright9#onDraw#getWidth()：" + getWidth());
        int innerCircle = dip2px(mContext, 89); //内圆半径
        int ringWidth = dip2px(mContext, 5);   //圆环宽度

        // 第一种方法绘制圆环
        //绘制内圆
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0x0000CDFF);
        //paint.setColor(Color.RED);
        //paint.setColor(0x407AFFFF);
        //paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xDC143C); //red
        //paint.setStrokeWidth(2);
        //canvas.drawCircle(center, center-2, innerCircle, paint);

        //绘制圆环
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0x0000CDFF);
        paint.setColor(mPaintColor);
        //paint.setColor(0x407AFFFF);
        //paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xFFFFFFFF);
        //paint.setColor(0xDC143C);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center-8, innerCircle + 1 + ringWidth/2, paint);

        //绘制外圆
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0xFFFFFFFF);
        //paint.setColor(Color.RED);
        //paint.setColor(0x407AFFFF);
        //paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xDC143C); //red
        /*paint.setStrokeWidth(2);
        canvas.drawCircle(center, center-2, innerCircle + ringWidth, paint);*/
        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
