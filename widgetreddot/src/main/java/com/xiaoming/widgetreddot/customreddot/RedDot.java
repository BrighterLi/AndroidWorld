package com.xiaoming.widgetreddot.customreddot;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class RedDot extends View {
    int r = 50; //圆形背景半径
    Paint mPaint; //中间将要显示的数字
    String mText = "0";
    int bgColor = Color.RED;
    int textColor = Color.WHITE;

    /**
     *
     * @param context
     * @param r 需要传入自定义的圆形半径,该值单位是px

     */
    public RedDot(Context context, int r) {
        this(context,null);
        this.r = r;
    }

    public RedDot(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public RedDot(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RedDot(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {
        //画笔
        mPaint = new Paint();
        //画笔线条类型
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        //画笔颜色
        mPaint.setColor(Color.WHITE);
        //该方法是设置防抖动
        mPaint.setDither(true);
        //该方法作用是抗锯齿
        mPaint.setAntiAlias(true);
        mPaint.setTextAlign(Paint.Align.CENTER);
        //设置背景透明,以免在使用setScale时会覆盖底层控件
        setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //决定了当前View的大小
        setMeasuredDimension(2 * r, 2 * r);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(bgColor);
        //即透明度。其取值范围是0---255,数值越小，越透明，颜色上表现越淡。
        mPaint.setAlpha(225);
        // 设置画笔遮罩滤镜  ,传入度数和样式
        mPaint.setMaskFilter(new BlurMaskFilter(10,BlurMaskFilter.Blur.SOLID));
        //画圆，坐标
        canvas.drawCircle(getMeasuredWidth() / 2, getMeasuredHeight() /2, r, mPaint);
        //这两个矩形分别是为了定位text和+的位置的 ？
        //画长方形，四条边到坐标的距离
        Rect rect = new Rect(getMeasuredWidth() / 2 - r,getMeasuredHeight() / 2 - r,getMeasuredWidth() / 2 + r, getMeasuredHeight() / 2 + r); //画一个矩形
        Rect rect1 = new Rect(getMeasuredWidth() / 2, getMeasuredHeight() / 2 - r, getMeasuredWidth() / 2 + r, getMeasuredHeight() / 2);//画一个矩形

        if(Integer.parseInt(mText) < 0) {
            mText = "0";
        }
        if(Integer.parseInt(mText) <= 99) {
            mPaint.setTextSize(r);
            drawStr(canvas, mPaint, rect,false);
        } else { //超过99显示99+
            //字体大小
            mPaint.setTextSize(r);
            //写文字
            drawStr(canvas, mPaint, rect, true);
            //设置文字颜色
            mPaint.setColor(textColor);
            //?
            float x1 = getMeasuredWidth() / 2 + r / 2;
            //?
            float y1 = (rect1.bottom + rect1.top - mPaint.getFontMetrics().bottom - mPaint.getFontMetrics().top) / 2;
            //写"+"
            canvas.drawText("+", x1, y1, mPaint);
        }


    }

    private void drawStr(Canvas canvas, Paint paint, Rect rect, boolean flag) {
        paint.setColor(textColor);
        float x = getMeasuredWidth() / 2;
        //?
        float y = (rect.bottom + rect.top - paint.getFontMetrics().bottom - paint.getFontMetrics().top) / 2;
        if(flag) {
            canvas.drawText("99", x - r / 5, y, paint);
        } else {
            canvas.drawText(mText, x, y, paint);
        }
    }

    public void setNum(int num) {
        mText = num + "";
        if(getVisibility() != VISIBLE) {
            setVisibility(VISIBLE);
        }
        //postInvalidate方法实现界面刷新
        postInvalidate();
    }

    // 方法或缩小该控件,一般不使用
    public void setScale(float f) {
        setScaleX(f);
        setScaleY(f);
    }

    //移除该控件,一般不使用
    public boolean remove() {
        try {
            ((ViewGroup)getParent()).removeView(this);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void setBgColor(int color) {
        bgColor = color;
    }

    public void setTextColor(int color) {
        textColor = color;
    }

    /**
     *
     * @param target 控件作用到目标view上,最终会显示在右上角,如果目标控件是button,
     *               button的背景色会覆盖掉自定义控件,需要适当调节button的透明度
     *               也可以参考例子中将button外层在包装一层,然后再外层添加RedDot
     */
    //?设置红点和目标View target的位置：右上角
    public void setTargetView(View target) {
        //获取target的父View parentContainer
        ViewGroup parentContainer = (ViewGroup) target.getParent();
        //获取子view target的index
        int index = parentContainer.indexOfChild(target);
        //父View parentContainer移除target
        parentContainer.removeView(target);
        //badgeContainer
        FrameLayout badgeContainer = new FrameLayout(getContext());
        //获取target的LayoutParams参数
        ViewGroup.LayoutParams parentLayoutParams = target.getLayoutParams();
        //badgeContainer.setPadding(0,200,200,0);
        //badgeContainer设置参数
        badgeContainer.setLayoutParams(parentLayoutParams);
        //target设置参数
        target.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //badgeContainer添加target
        badgeContainer.addView(target);
        FrameLayout.LayoutParams f1 = new FrameLayout.LayoutParams(parentLayoutParams);
        f1.gravity = Gravity.TOP | Gravity.RIGHT;
        badgeContainer.addView(this, f1);
        parentContainer.addView(badgeContainer,index,parentLayoutParams);
        //刷新
        postInvalidate();
    }
}
