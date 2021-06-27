package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.graphics.Xfermode;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.consumption.utils.ScreenUtil;

/**
 * 圆形进度条，类似 QQ 健康中运动步数的 UI 控件
 * Created by littlejie on 2017/2/21.
 * https://www.jianshu.com/p/5cec84ea08d3
 */
public class CircleProgress extends View {

    private static final String TAG = CircleProgress.class.getSimpleName();
    private Context mContext;

    //默认大小
    private int mDefaultSize;

    //绘制数值
    private TextPaint mValuePaint;
    private float mValueOffset;

    //绘制圆弧
    private Paint mArcPaint;
    private float mArcWidth;
    private float mStartAngle, mSweepAngle;
    private RectF mRectF;
    //渐变的颜色是360度，如果只显示270，那么则会缺失部分颜色
    private SweepGradient mSweepGradient;
    private int[] mGradientColors = {Color.GREEN, Color.YELLOW, Color.GREEN};
    //当前进度，[0.0f,1.0f]
    private float mPercent;
    //动画时间
    private long mAnimTime;
    //属性动画
    private ValueAnimator mAnimator;

    //绘制背景圆弧
    private int mBgArcColor;
    private float mBgArcWidth;

    private Paint mDialPaint;
    private float mDialWidth;
    private float mDialHeight;
    private int mDialColor;
    private float mDialNeedPadding;
    private int mDialInnerPadding = Constant.DEFAULT_DIAL_INNER_PADDING;

    //圆心坐标，半径
    private Point mCenterPoint;
    private float mRadius;

    private Bitmap mThumbBitmap;
    private final Point mThumbPoint = new Point();

    private Xfermode mXfermode;
    //控件大小比例变更时，需要重新计算这个比例值，这是一个大概值，没有精确值
    private final float mPercentRate = 0.945f;
    private boolean mShowGray = false;

    public CircleProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        mDefaultSize = ScreenUtil.dip2pxInt(mContext, Constant.DEFAULT_SIZE);
        mAnimator = new ValueAnimator();
        mRectF = new RectF();
        mCenterPoint = new Point();
        initAttrs(attrs);
        initPaint();
        mThumbBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.conversation_more);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_OUT);
    }

    public void showGray(){
        this.mShowGray = true;
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.CircleProgress);

        mPercent = typedArray.getFloat(R.styleable.CircleProgress_percent, Constant.DEFAULT_PERCENT);

        mArcWidth = typedArray.getDimension(R.styleable.CircleProgress_arcWidth, Constant.DEFAULT_ARC_WIDTH);
        mStartAngle = typedArray.getFloat(R.styleable.CircleProgress_startAngle, Constant.DEFAULT_START_ANGLE);
        mSweepAngle = typedArray.getFloat(R.styleable.CircleProgress_sweepAngle, Constant.DEFAULT_SWEEP_ANGLE);

        mBgArcColor = typedArray.getColor(R.styleable.CircleProgress_bgArcColor, Color.WHITE);
        mBgArcWidth = typedArray.getDimension(R.styleable.CircleProgress_bgArcWidth, Constant.DEFAULT_ARC_WIDTH);

        mDialWidth = typedArray.getDimension(R.styleable.CircleProgress_dialWidth, 2);
        mDialHeight = typedArray.getDimension(R.styleable.CircleProgress_dialHeight, 30);
        mDialNeedPadding =  mDialHeight * 2 + mDialInnerPadding;
        mDialColor = typedArray.getColor(R.styleable.CircleProgress_dialColor, Color.RED);

        mAnimTime = typedArray.getInt(R.styleable.CircleProgress_animTime, Constant.DEFAULT_ANIM_TIME);

        int gradientArcColors = typedArray.getResourceId(R.styleable.CircleProgress_arcColors, 0);
        if (gradientArcColors != 0) {
            try {
                int[] gradientColors = getResources().getIntArray(gradientArcColors);
                if (gradientColors.length == 0) {//如果渐变色为数组为0，则尝试以单色读取色值
                    int color = getResources().getColor(gradientArcColors);
                    mGradientColors = new int[2];
                    mGradientColors[0] = color;
                    mGradientColors[1] = color;
                } else if (gradientColors.length == 1) {//如果渐变数组只有一种颜色，默认设为两种相同颜色
                    mGradientColors = new int[2];
                    mGradientColors[0] = gradientColors[0];
                    mGradientColors[1] = gradientColors[0];
                } else {
                    mGradientColors = gradientColors;
                }
            } catch (Resources.NotFoundException e) {
                throw new Resources.NotFoundException("the give resource not found.");
            }
        }

        typedArray.recycle();
    }

    private void initPaint() {

        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        // 设置画笔的样式，为FILL，FILL_OR_STROKE，或STROKE
        mArcPaint.setStyle(Paint.Style.STROKE);
        // 设置画笔粗细
        mArcPaint.setStrokeWidth(mArcWidth);
        // 当画笔样式为STROKE或FILL_OR_STROKE时，设置笔刷的图形样式，如圆形样式
        // Cap.ROUND,或方形样式 Cap.SQUARE
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mDialPaint = new Paint();
        mDialPaint.setAntiAlias(true);
        mDialPaint.setColor(mDialColor);
        mDialPaint.setStrokeWidth(mDialWidth);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(cusMeasure(widthMeasureSpec, mDefaultSize),
                cusMeasure(heightMeasureSpec, mDefaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: w = " + w + "; h = " + h + "; oldw = " + oldw + "; oldh = " + oldh);
        //求圆弧和背景圆弧的最大宽度
        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);
        //求最小值作为实际值
        float minSize = Math.min(w - getPaddingLeft() - getPaddingRight() - 2 * maxArcWidth - mDialNeedPadding * 2,
                h - getPaddingTop() - getPaddingBottom() - 2 * maxArcWidth - mDialNeedPadding * 2);
        //减去圆弧的宽度，否则会造成部分圆弧绘制在外围
        mRadius = minSize / 2;
        //获取圆的相关参数
        mCenterPoint.x = w / 2;
        mCenterPoint.y = h / 2;
        //绘制圆弧的边界
        mRectF.left = mCenterPoint.x - mRadius - maxArcWidth / 2;
        mRectF.top = mCenterPoint.y - mRadius - maxArcWidth / 2;
        mRectF.right = mCenterPoint.x + mRadius + maxArcWidth / 2;
        mRectF.bottom = mCenterPoint.y + mRadius + maxArcWidth / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawArc(canvas);
        drawDial(canvas);
    }

    private void drawArc(Canvas canvas) {
        int sc = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.save();
        //进度条最低值为0.1
        float currentAngle = mSweepAngle * mPercentRate * Math.max(mPercent, 0.05f);
        canvas.rotate(mStartAngle, mCenterPoint.x, mCenterPoint.y);
        //1、绘制背景
        mArcPaint.setColor(mBgArcColor);
        mArcPaint.setStyle(Paint.Style.STROKE);
        if (mPercent < 1.0f) {
            //等于1时则隐藏背景
            mArcPaint.setStrokeCap(Paint.Cap.SQUARE);
            canvas.drawArc(mRectF, 0, mSweepAngle, false, mArcPaint);
        }
        //3、绘制进度
        updateArcPaint();
        mArcPaint.setShader(mSweepGradient);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(mRectF, 0, currentAngle, false, mArcPaint);
        mArcPaint.setShader(null);

        //设置混合模式
        mArcPaint.setXfermode(mXfermode);

        mArcPaint.setColor(Color.RED);
        mArcPaint.setStyle(Paint.Style.FILL);
        //画目标图像,2为了修复因为转化而导致的像素偏差
        canvas.drawRect(mCenterPoint.x + mRadius - 2, mCenterPoint.y - mArcWidth/2 - 2,
                mCenterPoint.x + mRadius + mArcWidth + 2, mCenterPoint.y, mArcPaint);
        canvas.rotate(mSweepAngle, mCenterPoint.x, mCenterPoint.y);
        canvas.drawRect(mCenterPoint.x + mRadius - 2, mCenterPoint.y,
                mCenterPoint.x + mRadius + mArcWidth + 2, mCenterPoint.y + mArcWidth/2 + 2, mArcPaint);

        mArcPaint.setXfermode(null);
        canvas.restore();

        canvas.restoreToCount(sc);


        //4、绘制icon
        //先找到图标中心所在的位置，再找到左上角的位置
        Point thumbPoint = getCurrentPoint();
        canvas.drawBitmap(mThumbBitmap,  thumbPoint.x, thumbPoint.y, mArcPaint);
    }

    private void drawDial(Canvas canvas) {
        float maxArcWidth = Math.max(mArcWidth, mBgArcWidth);
        float mDialIntervalDegree = mSweepAngle/ Constant.DEFAULT_SCALE;
        canvas.save();
        canvas.rotate(mStartAngle, mCenterPoint.x, mCenterPoint.y);
        for (int i = 0; i <= Constant.DEFAULT_SCALE; i++) {
            float stopX = mCenterPoint.x + mRadius + maxArcWidth + mDialInnerPadding + mDialHeight;
            if (i % 10 == 0) {
                stopX = stopX + mDialHeight;
            }
            float startX = mCenterPoint.x + mRadius + maxArcWidth + mDialInnerPadding;
            canvas.drawLine(startX, mCenterPoint.y, stopX, mCenterPoint.y, mDialPaint);
            canvas.rotate(mDialIntervalDegree, mCenterPoint.x, mCenterPoint.y);
        }
        canvas.restore();
    }

    /**
     * 更新圆弧画笔
     */
    private void updateArcPaint() {
        if (mSweepGradient != null) {
            return;
        }

        float gradientPercent = mSweepAngle/360f;
        //这样写的意义是只是用了两种过渡色，第三种色一方面是修复起始位置颜色，另一方面是为了前两种颜色在半圆弧时已经过渡
        float[] position ={0f, gradientPercent, 0.9f};
        mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y, mGradientColors, position);

        mArcPaint.setShader(mSweepGradient);
//        mRepairPaint.setColor(mGradientColors[0]);
    }

    private Point getCurrentPoint() {
        //进度条最低值为0.1
        float currentAngle = mSweepAngle * mPercentRate * Math.max(mPercent, 0.05f);
        //在总弧度的基础上减2°，这样不至于到底，需要和弧线宽度进行调整
        float sumAngle = currentAngle + mStartAngle - 2;
        float ABR = mRadius + mArcWidth / 2;
        double sinAB = Math.sin(sumAngle / 360 * 2 * Math.PI);
        double cosAB = Math.cos(sumAngle / 360 * 2 * Math.PI);
        mThumbPoint.x = (int) (mCenterPoint.x + cosAB * ABR);
        mThumbPoint.y = (int) (mCenterPoint.x + sinAB * ABR);

        int bitmapWidth = mThumbBitmap.getWidth();
        int bitmapHeight = mThumbBitmap.getHeight();
        mThumbPoint.x = mThumbPoint.x - bitmapWidth / 2;
        mThumbPoint.y = mThumbPoint.y - bitmapHeight / 2;

        return mThumbPoint;
    }

    public void setPercent(float percent) {
        mPercent = percent;
        invalidate();
    }

    public void showAnim(){
        startAnimator(0, mPercent, mAnimTime);
    }

    private void startAnimator(float start, float end, long animTime) {
        mAnimator = ValueAnimator.ofFloat(start, end);
        mAnimator.setDuration(animTime);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        mAnimator.start();
    }

    public int[] getGradientColors() {
        return mGradientColors;
    }

    /**
     * 设置渐变
     */
    public void setGradientColors(int[] gradientColors) {
        mGradientColors = gradientColors;
        updateArcPaint();
    }

    public long getAnimTime() {
        return mAnimTime;
    }

    public void setAnimTime(long animTime) {
        mAnimTime = animTime;
    }

    /**
     * 重置
     */
    public void reset() {
        startAnimator(mPercent, 0.0f, 1000L);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //释放资源
    }

    /**
     * 测量 View
     *
     */
    public static int cusMeasure(int measureSpec, int defaultSize) {
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == View.MeasureSpec.AT_MOST) {
            result = Math.min(result, specSize);
        }
        return result;
    }

    public static class Constant {

        public static final boolean ANTI_ALIAS = true;

        public static final int DEFAULT_SIZE = 150;
        public static final int DEFAULT_START_ANGLE = 165;
        public static final int DEFAULT_SWEEP_ANGLE = 210;

        public static final int DEFAULT_ANIM_TIME = 1000;

        public static final int DEFAULT_MAX_VALUE = 100;
        public static final int DEFAULT_VALUE = 50;
        public static final float DEFAULT_PERCENT = 0f;

        public static final int DEFAULT_HINT_SIZE = 15;
        public static final int DEFAULT_UNIT_SIZE = 30;
        public static final int DEFAULT_VALUE_SIZE = 15;

        public static final int DEFAULT_ARC_WIDTH = 114;

        public static final int DEFAULT_SCALE = 40;
        public static final int DEFAULT_DIAL_INNER_PADDING = 30;
    }
}

