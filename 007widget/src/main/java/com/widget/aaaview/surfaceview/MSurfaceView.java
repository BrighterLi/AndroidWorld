package com.widget.aaaview.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private Paint paint;
    private SurfaceHolder holder;
    private int x, y;
    private int radius;
    public MSurfaceView(Context context){
        super(context);
        // TODO Auto-generated constructor stub
        paint = new Paint();
        holder = this.getHolder();
        holder.addCallback(this);
        paint.setColor(Color.RED);
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        //mDraw();
    }
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub

    }
    public void mDraw(){
        Canvas canvas = holder.lockCanvas();
        canvas.drawText("HELLO SURFACEVIEW", x, y, paint);
        canvas.drawCircle(x, y, setRadius(), paint);
        holder.unlockCanvasAndPost(canvas);
    }
    private int setRadius(){
        radius = (int)(Math.random()*20);
        return radius;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        x = (int)event.getX();
        y = (int)event.getY();
        mDraw();
        return true;
    }


}
