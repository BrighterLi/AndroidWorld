package com.xiaoming.functionvideorecordingandfacerecognition.vivodetection;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class FaceMask extends View {
    Paint localPaint = null;
    RectF rect = null;
    LinearLayout container = null;
    byte[] data = null;
    int wSize;
    int hSize;
    String name = null;
    int age  = -1;


    public FaceMask(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        rect = new RectF();
        localPaint = new Paint();
        localPaint.setColor(0xff00b4ff);
        localPaint.setStrokeWidth(5);
        localPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
