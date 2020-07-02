package com.xiaoming.functionvideorecordingandfacerecognition.facedetectui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class FaceMaskView extends View {
    private Context mContext;

    public FaceMaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true); //消除锯齿
        paint.setStyle(Paint.Style.STROKE);  //绘制空心圆或 空心矩形
        //paint.setColor(0xFFFFFFFF);
        int center = getWidth()/2;
        Log.d("FaceMaskView", "bright9#onDraw#getWidth()：" + getWidth());
        int innerCircle = dip2px(mContext, 86); //内圆半径
        int ringWidth = dip2px(mContext, 8);   //圆环宽度

        // 第一种方法绘制圆环
        //绘制内圆
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0x407AFF);
        paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xDC143C); //red
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle, paint);

        //绘制圆环
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0x407AFF);
        paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xFFFFFFFF);
        //paint.setColor(0xDC143C);
        paint.setStrokeWidth(ringWidth);
        canvas.drawCircle(center, center, innerCircle + 1 +ringWidth/2, paint);

        //绘制外圆
        //paint.setARGB(255, 138, 43, 226);
        //paint.setColor(0xFFFFFFFF);
        //paint.setColor(0x407AFF);
        paint.setARGB(64, 122, 255, 1); //#407AFF转换
        //paint.setColor(0xDC143C); //red
        paint.setStrokeWidth(2);
        canvas.drawCircle(center, center, innerCircle + ringWidth, paint);
        super.onDraw(canvas);
    }

    /* 根据手机的分辨率从 dp 的单位 转成为 px(像素) */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
