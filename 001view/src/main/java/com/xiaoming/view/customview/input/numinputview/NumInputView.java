package com.xiaoming.view.customview.input.numinputview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.xiaoming.view.R;

//自定义数字输入键盘
//方式：自绘方式
public class NumInputView extends View {

    private int count = 4; //输入框个数
    private int boxWidth; //单个输入框的宽度
    private int boxHeight; //单个输入框的高度
    private int textSize; //文本大小
    private int textColor; //文本颜色
    private int boxColor; //边框颜色
    private int boxInterval;
    private StringBuilder currentNumber = new StringBuilder(); //当前已输入的文本
    private InputMethodManager inputMethodManager;
    private Paint boxPaint = new Paint();
    private TextPaint textPaint = new TextPaint();

    private float textBaseY = 0f;

    public NumInputView(Context context) {
        super(context);
        init();
    }

    public NumInputView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        if (null != attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumInputView);
            count = a.getInteger(R.styleable.NumInputView_box_count, 4);
            textColor = a.getColor(R.styleable.NumInputView_text_color,
                    Color.BLACK);
            boxColor = a.getColor(R.styleable.NumInputView_box_color,
                    Color.BLACK);
            a.recycle();
        }
        init();
    }

    public NumInputView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        if (null != attrs) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.NumInputView);
            count = a.getInteger(R.styleable.NumInputView_box_count, 4);
            textColor = a.getColor(R.styleable.NumInputView_text_color,
                    Color.BLACK);
            boxColor = a.getColor(R.styleable.NumInputView_box_color,
                    Color.BLACK);
            a.recycle();
        }
        init();
    }

    private void init() {
        boxWidth = UiTool.dip2px(getContext(), 50);
        boxHeight = UiTool.dip2px(getContext(), 50);
        textSize = UiTool.sp2px(getContext(), 20);
        boxInterval = UiTool.dip2px(getContext(), 4);

        boxPaint.setAntiAlias(true);
        boxPaint.setStyle(Paint.Style.STROKE);
        boxPaint.setColor(boxColor);

        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);
        textPaint.setTextAlign(Paint.Align.CENTER);

        inputMethodManager = (InputMethodManager) getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        setFocusableInTouchMode(true);
        showInputMethod();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecModel = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecModel = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int mWidth = getDefaultWidth();
        int mHeight = getDefaultHeight();

        switch (widthSpecModel) {
            case MeasureSpec.EXACTLY:
                boxWidth = widthSize / count;
                mWidth = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mWidth = getDefaultWidth();
                break;
        }
        switch (heightSpecModel) {
            case MeasureSpec.EXACTLY:
                boxHeight = heightSize;
                mHeight = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                mHeight = getDefaultHeight();
                break;
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    private int getDefaultWidth() {
        return count * boxWidth;
    }

    private int getDefaultHeight() {
        return boxHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawFrame(canvas);

        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        if (textBaseY == 0)
            textBaseY = getHeight() - (getHeight() - fontHeight) / 2 - fontMetrics.bottom;
        int y = (int) textBaseY;

        if (!TextUtils.isEmpty(currentNumber)) {
            if (currentNumber.length() > count) {
                currentNumber.delete(count, currentNumber.length() - 1);
            }
            for (int i = 0; i < currentNumber.length(); i++) {
                canvas.drawText("" + currentNumber.charAt(i),
                        boxWidth * i + (boxWidth / 2),
                        y,
                        textPaint);
            }
        }

    }

    /**
     * 绘制边框
     *
     * @param canvas
     */
    private void drawFrame(Canvas canvas) {
        RectF oval = new RectF(0,
                0,
                getWidth(),
                getHeight());
        canvas.drawRoundRect(oval, 10, 10, boxPaint);

        for (int i = 1; i < count; i++) {
            canvas.drawLine(boxWidth * i,
                    0,
                    boxWidth * i,
                    boxHeight,
                    boxPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        showInputMethod();
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //接收按键事件，67是删除键(backspace),7-16就是0-9
        if (keyCode == 67 && currentNumber.length() > 0) {
            currentNumber.deleteCharAt(currentNumber.length() - 1);
            //重新绘图
            invalidate();
        } else if (keyCode >= 7 && keyCode <= 16 && currentNumber.length() < count) {
            currentNumber.append(keyCode - 7);
            invalidate();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        outAttrs.inputType = InputType.TYPE_CLASS_NUMBER;//定义软键盘样式为数字键盘
        return super.onCreateInputConnection(outAttrs);
    }

    /**
     * 获取当前已输入文本
     *
     * @return
     */
    public String getCurrentNumber() {
        return currentNumber.toString();
    }

    /**
     * 设置当前显示验证码
     *
     * @param currentNumber
     */
    public void setCurrentNumber(String currentNumber) {
        if (!TextUtils.isEmpty(this.currentNumber)) {
            this.currentNumber.delete(0, currentNumber.length() - 1);
        }
        if (!TextUtils.isEmpty(currentNumber)) {
            if (currentNumber.length() > count) {
                currentNumber.substring(0, count);
            }
            this.currentNumber.append(currentNumber);
        }
        post(new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        });
    }

    /**
     * 打开输入法
     */
    private void showInputMethod() {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                inputMethodManager.viewClicked(NumInputView.this);
                inputMethodManager.showSoftInput(NumInputView.this,
                        InputMethodManager.SHOW_FORCED);
            }
        }, 100);
    }

    /**
     * 关闭输入法
     */
    private void closeInputMethod() {
        post(new Runnable() {
            @Override
            public void run() {
                if (inputMethodManager.isActive()) {
                    inputMethodManager.hideSoftInputFromInputMethod(NumInputView.this.getWindowToken(),
                            0);
                }
            }
        });
    }
}
