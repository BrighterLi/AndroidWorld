package com.widget.aaaview.draw.ondraw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import com.widget.R;

//Android 利用OnDraw实现自定义View: https://blog.csdn.net/bigconvience/article/details/25187907
public class OnDrawActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_on_draw);

        MyView mv = new MyView(this);
        setContentView(mv);
    }


    public class MyView extends View {

        MyView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            super.onDraw(canvas);

            // 首先定义一个paint
            Paint paint = new Paint();

            // 绘制矩形区域-实心矩形
            // 设置颜色
            paint.setColor(Color.BLUE);
            // 设置样式-填充
            paint.setStyle(Paint.Style.FILL);
            // 绘制一个矩形
            canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);

            // 绘空心矩形
            // 设置颜色
            paint.setColor(Color.RED);
            // 设置样式-空心矩形
            paint.setStyle(Paint.Style.STROKE);
            // 绘制一个矩形
            canvas.drawRect(new Rect(10, 10, 100, 30), paint);

            // 绘文字
            // 设置颜色
            paint.setColor(Color.GREEN);
            // 绘文字
            canvas.drawText("Hello", 10, 50, paint);

            // 绘图
            // 从资源文件中生成位图
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_more);
            // 绘图
            canvas.drawBitmap(bitmap, 10, 60, paint);
        }

    }
}
