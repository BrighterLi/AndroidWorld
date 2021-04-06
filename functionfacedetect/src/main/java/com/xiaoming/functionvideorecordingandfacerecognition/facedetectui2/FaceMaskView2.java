package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FaceMaskView2 extends View {
    private Context mContext;
    private int mPaintColor;
    private Paint mPaint;

    public FaceMaskView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mPaint = new Paint();
    }

    public void setPaintColor(int mPaintColor) {
        this.mPaintColor = mPaintColor;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setAntiAlias(true); //消除锯齿
        mPaint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
        int center = getWidth()/2;
        Log.d("FaceMaskView", "bright9#onDraw#getWidth()：" + getWidth());
        int innerCircle = dip2px(mContext, 95); //内圆半径
        int ringWidth = dip2px(mContext, 5);   //圆环宽度
        mPaint.setColor(mPaintColor);
        mPaint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 + ringWidth/2, mPaint);
        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
