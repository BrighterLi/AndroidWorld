package com.xiaoming.widgetwheelview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//时间选择器-单列，可扩展至2列，3列，4列
public class WheelView extends ScrollView {
    public static final String TAG = WheelView.class.getSimpleName();

    //静态内部类，作为回调接口使用
    public static class OnWheelViewListener {
        public void onSelected(int selectedIndex, String item) {
        }
    }

    private Context context;
    private LinearLayout views;

    public WheelView(Context context) {
        super(context);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WheelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    List<String> items; //列表数据
    private List<String> getItems() {
        return items;
    }

    //设置列表数据，根据列表数据来构建列表View
    public void setItems(List<String> list) {
        if(null == items) {
            items = new ArrayList<String>();
        }
        items.clear();
        items.addAll(list);

        //前面和后面补全，即显示空白,前后显示offset个空白
        Log.d(TAG, "offset: " + offset);
        for(int i = 0; i < offset; i++) {
          items.add(0,""); //前面添加""
          items.add("");   //后面添加""
        }
        initData();
    }

    public static final int OFF_SET_DEFAULT = 1;
    int offset = OFF_SET_DEFAULT; //偏移量(需要在最前面和最后面补全)

    // 对话框中当前项上面和下面的项数,所以显示的总项数为(1 + offset * 2)
    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getOffset() {
        return offset;
    }

    int displayItemCount; //每页显示的数量
    int selectedIndex = 1;

    //初始化
    private void init(Context context) {
        this.context = context;
        Log.d(TAG, "parent:" + this.getParent());
        this.setVerticalScrollBarEnabled(false); //隐藏滚动条
        views = new LinearLayout(context);
        views.setOrientation(LinearLayout.VERTICAL);
        this.addView(views);  //添加LinearLayout

        //?
        scrollerTask = new Runnable(){
            @Override
            public void run() {
                int newY = getScrollY();
                if(initialY - newY == 0) {
                    final int remainder = initialY % itemHeight;
                    final int divided = initialY / itemHeight;
                    if(remainder == 0) {
                        selectedIndex = divided + offset;
                        onSeletedCallBack();
                    } else {
                        if(remainder > itemHeight / 2) {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollBy(0, initialY - remainder + itemHeight);
                                    selectedIndex = divided + offset + 1;
                                    onSeletedCallBack();
                                }
                            });
                        } else {
                            WheelView.this.post(new Runnable() {
                                @Override
                                public void run() {
                                    WheelView.this.smoothScrollTo(0, initialY - remainder);
                                    selectedIndex = divided + offset;
                                    onSeletedCallBack();
                                }
                            });
                        }
                    }
                } else {
                    initialY = getScrollY();
                    WheelView.this.postDelayed(scrollerTask, newCheck);
                }
            }
        };

        //默认初始不滑动时执行一次回调
        if(null != onWheelViewListener) {
            onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
        }
    }

    int initialY;

    Runnable scrollerTask;
    int newCheck = 50;

    //开始滑动的异步任务
    public void startScrollerTask() {
        initialY = getScrollY();
        Log.d(TAG, "initialY: " + initialY);
        this.postDelayed(scrollerTask, newCheck);
    }

    private  void initData() {
        //显示的总item数量
        displayItemCount = offset * 2 + 1;

        views.removeAllViews();
        for(String item : items) {
            views.addView(createView(item)); //根据String来构建View并添加到LinearLayout中
        }

        refreshItemView(0);
    }

    int itemHeight = 0; //列表View的每个View的高度

    private TextView createView(String item) { //创建的是TextView
       TextView tv = new TextView(context);
       tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
       tv.setSingleLine(true);
       tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
       tv.setText(item);
       tv.setGravity(Gravity.CENTER);
       int padding = dip2px(15);
       tv.setPadding(padding, padding, padding,padding);
        Log.d(TAG, "itemHeight: " + itemHeight);
       if(0 == itemHeight) {
           itemHeight = getViewMeasuredHeight(tv);  //获取高度
           views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, itemHeight * displayItemCount, Gravity.CENTER_HORIZONTAL));
           views.setGravity(Gravity.CENTER);
           //获取当前的高度和宽度
           LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this.getLayoutParams();
           //设置WheelView的高度和宽度
           this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight * displayItemCount));
       }
       return tv;
    }

    @Override
    protected void onScrollChanged(int l, int t, int old1, int oldt) { //滚动触发
        super.onScrollChanged(1, t, old1, oldt);
        refreshItemView(t);

        if(t > oldt) {
            Log.d(TAG, "向下滚动");
            scrollDirection = SCROLL_DIRECTION_DOWN; //滚动方向为向下
        } else {
            Log.d(TAG, "向上滚动");
            scrollDirection = SCROLL_DIRECTION_UP; //滚动方向为向上
        }
    }

    //输入的是高度，刷新内容
    private void refreshItemView(int y) {
        int position = y / itemHeight + offset;
        int remainder = y % itemHeight;
        int divided = y / itemHeight;

        if(remainder == 0) {
            position = divided + offset; //?
        } else {
            if(remainder > itemHeight / 2) {
                position = divided + offset + 1;
            }
        }

        int childSize = views.getChildCount();
        for(int i = 0; i < childSize; i++) {
            TextView itemView = (TextView) views.getChildAt(i);
            if(null == itemView) {
                return;
            }
            if(position == i) {
                //让中间位置的Text为红色，其他为绿色
                itemView.setTextColor(context.getResources().getColor(R.color.red));
            } else {
                itemView.setTextColor(context.getResources().getColor(R.color.green));
            }
        }
    }

    //获取选中区域的边界
    int[] selectedAreaBorder;

    private int[] obtainSelectedAreaBorder() {
        if(null == selectedAreaBorder) {
            selectedAreaBorder = new int[2];
            selectedAreaBorder[0] = itemHeight * offset;
            selectedAreaBorder[1] = itemHeight * (offset + 1);
        }
        return selectedAreaBorder;
    }

    private int scrollDirection = -1;
    private static final int SCROLL_DIRECTION_UP = 0;
    private static final int SCROLL_DIRECTION_DOWN = 1;

    Paint paint;
    int viewWidth;

    //?
    @Override
    public void setBackground(Drawable background) {
        Log.d(TAG, "viewWidth1: " + viewWidth);
        if(viewWidth == 0) {
            viewWidth = views.getWidth();
            Log.d(TAG, "viewWidth2: " + viewWidth);
        }

        if(null == paint) {
            paint = new Paint();
            paint.setColor(Color.parseColor("#A020F0")); //紫色
            paint.setStrokeWidth(dip2px(5f));
        }
        background = new Drawable() {
            @Override
            public void draw(@NonNull Canvas canvas) {
                canvas.drawLine(viewWidth * 1 / 6, obtainSelectedAreaBorder()[0], viewWidth * 5 / 6, obtainSelectedAreaBorder()[0], paint);
                canvas.drawLine(viewWidth * 1 / 6, obtainSelectedAreaBorder()[1], viewWidth * 5 / 6, obtainSelectedAreaBorder()[1], paint);
            }

            @Override
            public void setAlpha(int alpha) {

            }

            @Override
            public void setColorFilter(@Nullable ColorFilter colorFilter) {

            }

            @Override
            public int getOpacity() {
                return PixelFormat.UNKNOWN;
            }
        };
        super.setBackground(background);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) { //view大小改变了则触发
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "w: " + w + ", h: " + h + ", oldw: " + oldw + ", oldh: " + oldh);
        viewWidth = w;
        setBackgroundDrawable(null);
    }

    //选中回调
    private void onSeletedCallBack() {
        if(null != onWheelViewListener) {
            onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
        }
    }

    //设置选中项
    public void setSeletion(int position) {
        final int p = position;
        selectedIndex = p + offset; //
        this.post(new Runnable() {
            @Override
            public void run() {
                //滑动到选中位置
                WheelView.this.smoothScrollTo(0, p * itemHeight);
            }
        });
    }

    public String getSeletedItem() {
        return items.get(selectedIndex);
    }

    public int getSelectedIndex() {
        return selectedIndex - offset;
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY /3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_UP) {
            startScrollerTask(); //点击就开始滑动的异步任务
        }
        return super.onTouchEvent(ev);
    }

    private OnWheelViewListener onWheelViewListener;

    public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
        this.onWheelViewListener = onWheelViewListener;
        onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
    }

    public OnWheelViewListener getOnWheelViewListener() {
        return onWheelViewListener;
    }

    //dp转成px
    private int dip2px(float dpValue) {
        //这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
        //也可以说是相对密度
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //获取View的高度
    private int getViewMeasuredHeight(View view) {
        //MeasureSpec通过将SpecMode和SpecSize打包成一个int值来避免过多的对象内存分配，为了方便操作，其提供了打包和解包的方法。
        //其中specSize记录的是大小，specMode记录的是规格。
        int width = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        view.measure(width, expandSpec);
        return view.getMeasuredHeight();
    }
}
