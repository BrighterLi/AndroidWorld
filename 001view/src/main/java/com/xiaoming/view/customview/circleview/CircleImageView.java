package com.xiaoming.view.customview.circleview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xiaoming.view.R;

//自定义View：自绘方式
public class CircleImageView extends View {
    private Paint mPaint;  //画笔
    private Bitmap imageBitmap;
    private float circleRadio;

    public CircleImageView(Context context) {
        super(context);
        mPaint = new Paint();
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(); //画笔
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.square_image);
    }

    //重写onDraw方法
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(getCircle(imageBitmap),0,0,mPaint);
    }

    //将bitmap转换成圆形bitmap
    private Bitmap getCircle(Bitmap bitmap) {
        //circleRadio图形图片的半径
        circleRadio = bitmap.getWidth()/2;
        //创建一张新的bitmap,跟传入图片一样宽的正方形bitmap
        Bitmap b = Bitmap.createBitmap(bitmap.getWidth(),bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        //初始化画布，并将刚才创建的bitmap给这个画布，让画布在这bitmap上面
        Canvas canvas = new Canvas(b);
        //初始化画笔
        Paint p = new Paint();
        //在画布中画一个等宽的圆
        canvas.drawCircle(circleRadio,circleRadio,circleRadio,p);
        //设置画笔属性，让画笔只在那圆圈中画画、
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0,0,p);
        return b;
    }
}
