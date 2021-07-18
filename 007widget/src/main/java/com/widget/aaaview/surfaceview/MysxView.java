package com.widget.aaaview.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

public class MysxView extends View {

    String title[] = { "四川", "重庆", "北京", "上海", "南京", "深圳" };

    public MysxView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        canvas.drawCircle(400, 400, 300, paint);
        int each = 360 / title.length;
        Paint p1 = new Paint();
        Paint p2 = new Paint();
        Paint p3 = new Paint();
        p1.setColor(Color.BLUE);
        p2.setColor(Color.YELLOW);
        p3.setColor(Color.WHITE);
        for (int i = 0; i < title.length; i++) {
            RectF oval = new RectF(100,100,700,700);
            canvas.drawArc(oval, i * each, each, true, i % 2 == 0 ? p1 : p2);

        }

    }
}

