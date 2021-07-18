package com.widget.aaaview.surfaceview;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.widget.R;

public class MySurface extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    SurfaceHolder holder;
    boolean goOn = true;
    int w;
    int h;
    Bitmap bitmap;
    int x;
    int y;
    int bx;
    int by;
    boolean down = true;
    boolean right = true;
    int p = 30;

    public MySurface(Context context, int wi, int hi) {
        super(context);
        // TODO Auto-generated constructor stub
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        w = wi;
        h = hi;
        Bitmap obitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.arrow_more);
        bitmap = Bitmap.createScaledBitmap(obitmap, 260, 200, false);
        bx = bitmap.getWidth();
        by = bitmap.getHeight();
        Thread t = new Thread(this);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        goOn = false;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (goOn) {
            myDraw();
            try {
                if (y < h - by && down) {
                    y += p;

                } else {
                    down = false;
                    if (x < w - bx && right) {
                        x += p;
                    } else {
                        right = false;
                        if (y > 0 && !down) {
                            y -= p;
                        } else {

                            if (x > 0 && !right) {
                                x -= p;
                            } else {
                                down = true;
                                right = true;
                            }
                        }
                    }
                }

                Thread.sleep(10);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    Canvas canvas;

    private void myDraw() {
        // TODO Auto-generated method stub
        canvas = holder.lockCanvas();
        if (canvas != null) {
            Paint paint = new Paint();
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            canvas.drawPaint(paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            canvas.drawColor(Color.GRAY);
            try {
                if (canvas != null) {
                    canvas.drawBitmap(bitmap, x, y, null);
                }
            } finally {
                if (canvas != null) {
                    holder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}

