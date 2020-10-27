package com.xiaoming.a008project.consumption.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.xiaoming.a008project.R;

import java.util.Locale;


//自定义TabLayout
public class MyTabStrip extends HorizontalScrollView {

    private TabClickListener mTabClickListener;
    private boolean isAlreadyDraw = false;


    public interface IconTabProvider {
        int getPageIconResId(int position);
    }

    private static final int[] ATTRS = new int[]{
            android.R.attr.textSize,
            android.R.attr.textColor
    };

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private final PageListener pageListener = new PageListener();
    public OnPageChangeListener delegatePageListener;

    private LinearLayout tabsContainer;
    private ViewPager pager;

    private int currentPosition = 0;
    private int dragPosition = 0;
    private float currentPositionOffset = 0f;

    private Paint rectPaint;
    private Paint dividerPaint;
    private RectF rectF;

    private int indicatorLength = 0;
    private int indicatorCorner = 0;
    private int indicatorBottomMargin = 0;
    private int indicatorColor = 0xFF666666;
    private int underlineColor = 0x1A000000;
    private int dividerColor = Color.TRANSPARENT;

    private boolean shouldExpand = false;

    private int scrollOffset = 52;
    private int edgeLeft = 0;
    private int edgeRight = 0;
    private int indicatorHeight = 8;
    private int underlineHeight = 1;
    private int dividerPadding = 12;
    private int tabPadding = 24;
    private int dividerWidth = 1;

    private int tabDefaultTextSize = 14;
    private int tabSelectedTextSize = 14;
    private int tabDefaultTextColor = Color.GRAY;
    private int tabSelectedTextColor = Color.BLUE;
    private Typeface tabTypeface = null;
    private int tabTypefaceStyle = Typeface.NORMAL;
    private int lastScrollX = 0;
    private int tabBackgroundResId = 0;
    private int tabForegroundResId = 0;
    private Locale locale;

    public MyTabStrip(Context context) {
        this(context, null);
    }

    public MyTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setFillViewport(true);
        setWillNotDraw(false);
        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        DisplayMetrics dm = getResources().getDisplayMetrics();

        scrollOffset = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, scrollOffset, dm);
        indicatorHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, indicatorHeight, dm);
        underlineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, underlineHeight, dm);
        dividerPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerPadding, dm);
        tabPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, tabPadding, dm);
        dividerWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dividerWidth, dm);
        tabDefaultTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, tabDefaultTextSize, dm);

        // get system attrs (android:textSize and android:textColor)
        TypedArray a = context.obtainStyledAttributes(attrs, ATTRS);

        tabDefaultTextSize = a.getDimensionPixelSize(0, tabDefaultTextSize);
        tabSelectedTextColor = a.getColor(1, tabSelectedTextColor);

        a.recycle();

        // get custom attrs
        a = context.obtainStyledAttributes(attrs, R.styleable.PagerSlidingTabStrip);

        indicatorColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsIndicatorColor, indicatorColor);
        underlineColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsUnderlineColor, underlineColor);
        dividerColor = a.getColor(R.styleable.PagerSlidingTabStrip_pstsDividerColor, dividerColor);
        indicatorHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsIndicatorHeight, indicatorHeight);
        underlineHeight = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsUnderlineHeight, underlineHeight);
        dividerPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsDividerPadding, dividerPadding);
        tabPadding = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsTabPaddingLeftRight, tabPadding);
        tabBackgroundResId = a.getResourceId(R.styleable.PagerSlidingTabStrip_pstsTabBackground, tabBackgroundResId);
        shouldExpand = a.getBoolean(R.styleable.PagerSlidingTabStrip_pstsShouldExpand, shouldExpand);
        scrollOffset = a.getDimensionPixelSize(R.styleable.PagerSlidingTabStrip_pstsScrollOffset, scrollOffset);

        a.recycle();

        rectPaint = new Paint();
        rectPaint.setAntiAlias(true);
        rectPaint.setStyle(Style.FILL);
        rectF = new RectF(0, 0, 0, 0);

        dividerPaint = new Paint();
        dividerPaint.setAntiAlias(true);
        dividerPaint.setStrokeWidth(dividerWidth);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);

        if (locale == null) {
            locale = getResources().getConfiguration().locale;
        }
    }

    public void setViewPager(ViewPager pager) {
        this.pager = pager;
        if (pager.getAdapter() == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        pager.removeOnPageChangeListener(pageListener);
        pager.addOnPageChangeListener(pageListener);
        notifyDataSetChanged();
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.delegatePageListener = listener;
    }

    public void notifyDataSetChanged() {
        tabsContainer.removeAllViews();
        int tabCount = pager.getAdapter().getCount();
        for (int i = 0; i < tabCount; i++) {
            if (pager.getAdapter() instanceof IconTabProvider) {
                addIconTab(i, ((IconTabProvider) pager.getAdapter()).getPageIconResId(i));
            } else {
                addTextTab(i, pager.getAdapter().getPageTitle(i));
            }
        }

        updateTabStyles();
        postInvalidate();

        getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    getViewTreeObserver().removeGlobalOnLayoutListener(this);
                } else {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                currentPosition = pager.getCurrentItem();
                scrollToChild(currentPosition, 0);
            }
        });


    }

    private void addTextTab(final int position, CharSequence title) {
        TextView tab = new TextView(getContext());
        tab.setText(title);
        tab.setGravity(Gravity.CENTER);
        tab.setIncludeFontPadding(false);
        tab.setMaxLines(1);
        tab.setSingleLine();
        tab.setEllipsize(TextUtils.TruncateAt.END);
        if (position == currentPosition) {
            tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabSelectedTextSize);
            tab.setTextColor(tabSelectedTextColor);
            tab.getPaint().setFakeBoldText(true);
        } else {
            tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabDefaultTextSize);
            tab.setTextColor(tabDefaultTextColor);
            tab.getPaint().setFakeBoldText(false);
        }
        if (tabForegroundResId != 0 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            tab.setForeground(getResources().getDrawable(tabForegroundResId, null));
        }
        if (tabBackgroundResId != 0) {
            tab.setBackgroundResource(tabBackgroundResId);
        }
        addTab(position, tab);
    }

    private void addIconTab(final int position, int resId) {
        ImageButton tab = new ImageButton(getContext());
        tab.setImageResource(resId);
        addTab(position, tab);
    }

    private void addTab(final int position, final View tab) {
        if (position == 0) {
            if (mTabClickListener != null) {
                mTabClickListener.onClick(position);
            }
        }
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onTabClick(position);
            }
        });

        if (position == 0) {
            if (tabsContainer.getChildCount() == 1) {
                tab.setPadding(edgeLeft, 0, edgeRight, 0);
            } else {
                tab.setPadding(edgeLeft, 0, tabPadding, 0);
            }
        } else if (position == tabsContainer.getChildCount() - 1) {
            tab.setPadding(tabPadding, 0, edgeRight, 0);
        } else {
            tab.setPadding(tabPadding, 0, tabPadding, 0);
        }
        tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
    }

    public void onTabClick(int position) {
        if (position == currentPosition) {
            return;
        }
        currentPosition = position;
        pager.setCurrentItem(position, true);
        if (mTabClickListener != null) {
            mTabClickListener.onClick(position);
        }
        updateTabStyles();
    }

    public interface TabClickListener {
        void onClick(int position);
    }

    public void setTabClickListener(TabClickListener listener) {
        mTabClickListener = listener;
    }

    private void updateTabStyles() {
        int tabCount = tabsContainer.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            View v = tabsContainer.getChildAt(i);
            if (v instanceof TextView) {
                final TextView tab = (TextView) v;
                tab.setTypeface(tabTypeface, tabTypefaceStyle);
                if (currentPosition == i) {
                    if (tab.getTextSize() != tabSelectedTextSize) {
                        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabSelectedTextSize);
                    }
                    if (tab.getCurrentTextColor() != tabSelectedTextColor) {
                        tab.setTextColor(tabSelectedTextColor);
                    }
                    tab.getPaint().setFakeBoldText(true);
                } else {
                    if (tab.getTextSize() != tabDefaultTextSize) {
                        tab.setTextSize(TypedValue.COMPLEX_UNIT_PX, tabDefaultTextSize);
                    }
                    if (tab.getCurrentTextColor() != tabDefaultTextColor) {
                        tab.setTextColor(tabDefaultTextColor);
                    }
                    tab.getPaint().setFakeBoldText(false);
                }
                if (i == 0) {
                    if (tabsContainer.getChildCount() == 1) {
                        if (tab.getPaddingLeft() != edgeLeft || tab.getPaddingRight() != edgeRight) {
                            tab.setPadding(edgeLeft, 0, edgeRight, 0);
                        }
                    } else {
                        if (tab.getPaddingLeft() != edgeLeft || tab.getPaddingRight() != tabPadding) {
                            tab.setPadding(edgeLeft, 0, tabPadding, 0);
                        }
                    }
                } else if (i == tabsContainer.getChildCount() - 1) {
                    if (tab.getPaddingLeft() != tabPadding || tab.getPaddingRight() != edgeRight) {
                        tab.setPadding(tabPadding, 0, edgeRight, 0);
                    }
                } else {
                    if (tab.getPaddingLeft() != tabPadding || tab.getPaddingRight() != tabPadding) {
                        tab.setPadding(tabPadding, 0, tabPadding, 0);
                    }
                }
            }
        }
    }


    private void scrollToChild(int position, int offset) {
        if (tabsContainer.getChildCount() == 0) {
            return;
        }
        int newScrollX = tabsContainer.getChildAt(position).getLeft() + offset;
        if (position > 0 || offset > 0) {
            newScrollX -= scrollOffset;
        }
        if (newScrollX != lastScrollX) {
            lastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int tabCount = tabsContainer.getChildCount();
        if (isInEditMode() || tabCount <= 0) {
            return;
        }
        isAlreadyDraw = true;

        final int height = getHeight();

        // draw indicator line
        if (indicatorHeight > 0 && dragPosition >= 0 && dragPosition < tabsContainer.getChildCount()) {
            rectPaint.setColor(indicatorColor);
            // default: line below current tab
            View currentTab = tabsContainer.getChildAt(dragPosition);
            float lineLeft = currentTab.getLeft() + getPaddingLeft();
            float lineRight = currentTab.getRight() + getPaddingLeft();
            // if there is an offset, start interpolating left and right coordinates between current and next tab
            if (currentPositionOffset > 0f && dragPosition < tabCount - 1) {
                View nextTab = tabsContainer.getChildAt(dragPosition + 1);
                final float nextTabLeft = nextTab.getLeft() + getPaddingLeft();
                final float nextTabRight = nextTab.getRight() + getPaddingLeft();
                lineLeft = lineLeft + (nextTabLeft - lineLeft) * currentPositionOffset;
                lineRight = lineRight + (nextTabRight - lineRight) * currentPositionOffset;
            }
            float left, right, top, bottom;
            if (currentPosition == 0) {
                left = lineLeft + edgeLeft;
                if (tabCount == 1) {
                    right = lineRight - edgeRight;
                } else {
                    right = lineRight - tabPadding;
                }
                top = height - indicatorHeight - indicatorBottomMargin;
                bottom = height - indicatorBottomMargin;
            } else if (currentPosition == tabCount - 1) {
                left = lineLeft + tabPadding;
                right = lineRight - edgeRight;
                top = height - indicatorHeight - indicatorBottomMargin;
                bottom = height - indicatorBottomMargin;
            } else {
                left = lineLeft + tabPadding;
                right = lineRight - tabPadding;
                top = height - indicatorHeight - indicatorBottomMargin;
                bottom = height - indicatorBottomMargin;
            }
            float delta = 0f;
            if (indicatorLength > 0) {
                delta = (indicatorLength - (right - left)) / 2;
            }
            rectF.left = left - delta;
            rectF.right = right + delta;
            rectF.top = top;
            rectF.bottom = bottom;
            canvas.drawRoundRect(rectF, indicatorCorner, indicatorCorner, rectPaint);
        }

        // draw underline
        if (underlineColor != Color.TRANSPARENT && underlineHeight > 0) {
            rectPaint.setColor(underlineColor);
            View last = tabsContainer.getChildAt(tabCount - 1);
            int width = last.getRight() + getPaddingRight();
            if (underlineHeight == 1) {
                canvas.drawLine(0, height, width, height, rectPaint);
            } else {
                canvas.drawRect(0, height - underlineHeight, width, height, rectPaint);
            }
        }

        // draw divider
        if (dividerColor != Color.TRANSPARENT) {
            dividerPaint.setColor(dividerColor);
            for (int i = 0; i < tabCount - 1; i++) {
                View tab = tabsContainer.getChildAt(i);
                float x = tab.getRight() + getPaddingLeft();
                canvas.drawLine(x, dividerPadding, x, height - dividerPadding, dividerPaint);
            }
        }
    }

    private class PageListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            dragPosition = position;
            currentPositionOffset = positionOffset;

            if (tabsContainer.getChildCount() > position) {
                scrollToChild(position, (int) (positionOffset * tabsContainer.getChildAt(position).getWidth()));
            }
            invalidate();
            if (delegatePageListener != null) {
                delegatePageListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                scrollToChild(pager.getCurrentItem(), 0);
            }
            if (delegatePageListener != null) {
                delegatePageListener.onPageScrollStateChanged(state);
            }
        }

        @Override
        public void onPageSelected(int position) {
            currentPosition = position;
            updateTabStyles();
            if (delegatePageListener != null) {
                delegatePageListener.onPageSelected(position);
            }
        }

    }

    public void setIndicatorColor(int indicatorColor) {
        this.indicatorColor = indicatorColor;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setIndicatorColorResource(int resId) {
        this.indicatorColor = getResources().getColor(resId);
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getIndicatorColor() {
        return this.indicatorColor;
    }

    public void setIndicatorHeight(int indicatorLineHeightPx) {
        this.indicatorHeight = indicatorLineHeightPx;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getIndicatorHeight() {
        return indicatorHeight;
    }


    public void setIndicatorLength(int indicatorLength) {
        this.indicatorLength = indicatorLength;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setIndicatorCorner(int indicatorCorner) {
        this.indicatorCorner = indicatorCorner;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setIndicatorBottomMargin(int indicatorMargin) {
        this.indicatorBottomMargin = indicatorMargin;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setUnderlineColor(int underlineColor) {
        this.underlineColor = underlineColor;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setUnderlineColorResource(int resId) {
        this.underlineColor = getResources().getColor(resId);
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getUnderlineColor() {
        return underlineColor;
    }

    public void setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setDividerColorResource(int resId) {
        this.dividerColor = getResources().getColor(resId);
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public void setDividerWidth(int dividerWidth) {
        this.dividerWidth = dividerWidth;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getDividerColor() {
        return dividerColor;
    }

    public void setUnderlineHeight(int underlineHeightPx) {
        if (isAlreadyDraw) {
            if (this.underlineHeight != underlineHeightPx) {
                this.underlineHeight = underlineHeightPx;
                invalidate();
            }
        } else {
            this.underlineHeight = underlineHeightPx;
            invalidate();
        }
    }

    public int getUnderlineHeight() {
        return underlineHeight;
    }

    public void setDividerPadding(int dividerPaddingPx) {
        this.dividerPadding = dividerPaddingPx;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getDividerPadding() {
        return dividerPadding;
    }

    /**
     * @param padding 左右边缘的距离
     */
    public void setEdgeLeftRight(int padding, boolean update) {
        this.edgeLeft = padding;
        this.edgeRight = padding;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    /**
     * @param edgeLeft 左边缘的距离
     */
    public void setEdgeLeft(int edgeLeft, boolean update) {
        this.edgeLeft = edgeLeft;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    /**
     * @param edgeRight 右边缘的距离
     */
    public void setEdgeRight(int edgeRight, boolean update) {
        this.edgeRight = edgeRight;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public void setScrollOffset(int scrollOffsetPx) {
        this.scrollOffset = scrollOffsetPx;
        if (isAlreadyDraw) {
            invalidate();
        }
    }

    public int getScrollOffset() {
        return scrollOffset;
    }

    public void setShouldExpand(boolean shouldExpand) {
        this.shouldExpand = shouldExpand;
        requestLayout();
    }

    public boolean getShouldExpand() {
        return shouldExpand;
    }


    public void setDefaultTextSize(int textSizePx, boolean update) {
        this.tabDefaultTextSize = textSizePx;
        this.tabSelectedTextSize = textSizePx;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public int getTextSize() {
        return tabDefaultTextSize;
    }

    public void setSelectedTextSize(int textSizePx, boolean update) {
        this.tabSelectedTextSize = textSizePx;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public void setDefaultTextColor(@ColorInt int textColor, boolean update) {
        this.tabDefaultTextColor = textColor;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public int getDefaultTextColor() {
        return tabDefaultTextColor;
    }

    public void setSelectedTextColor(@ColorInt int textColor, boolean update) {
        this.tabSelectedTextColor = textColor;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public int getSelectedTextColor() {
        return tabSelectedTextColor;
    }


    public void setTypeface(Typeface typeface, int style) {
        this.tabTypeface = typeface;
        this.tabTypefaceStyle = style;
        updateTabStyles();
        if (isAlreadyDraw) {
            postInvalidate();
        }
    }

    public void setTabBackgroundRes(int resId) {
        this.tabBackgroundResId = resId;
    }

    public int getTabBackgroundRes() {
        return tabBackgroundResId;
    }

    public void setTabForegroundRes(int resId) {
        this.tabForegroundResId = resId;
    }

    public int getTabForegroundRes() {
        return tabForegroundResId;
    }

    /**
     * @param paddingPx 两个tab间的距离的二分之一
     */
    public void setTabPaddingLeftRight(int paddingPx, boolean update) {
        this.tabPadding = paddingPx;
        if (update) {
            updateTabStyles();
            if (isAlreadyDraw) {
                postInvalidate();
            }
        }
    }

    public int getTabPaddingLeftRight() {
        return tabPadding;
    }

    public int getTabCount() {
        return tabsContainer.getChildCount();
    }

    public float getTotalTxtMeasureWidth() {
        float width = 0;
        for (int i = 0; i < tabsContainer.getChildCount(); i++) {
            View child = tabsContainer.getChildAt(i);
            if (child instanceof TextView) {
                TextView tv = (TextView) child;
                CharSequence text = tv.getText();
                if (!TextUtils.isEmpty(text)) {
                    float w = tv.getPaint().measureText(text.toString());
                    width += w;
                }
            }
        }
        return width;
    }



    /*<---------------------- 处理滑动 ---------------------->*/

    private float mDownX;
    private float mDownY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        ViewParent parent = getParent();
        if (mScrollListener == null || parent == null) {
            return super.dispatchTouchEvent(event);
        }
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                //不允许父布局拦截事件
                parent.requestDisallowInterceptTouchEvent(true);
                break;
            case MotionEvent.ACTION_MOVE:
                float nowX = event.getX();
                float nowY = event.getY();
                int orientation = getOrientation(nowX - mDownX, nowY - mDownY);
                switch (orientation) {
                    case 't':
                    case 'b':
                        if (mScrollListener.canScrollParentY()) {
                            //滑动父布局
                            parent.requestDisallowInterceptTouchEvent(false);
                            return false;
                        } else {
                            //不允许父布局拦截事件 自己滑动
                            parent.requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    private int getOrientation(float dx, float dy) {
        if (Math.abs(dx) > Math.abs(dy)) {
            //X轴移动
            return dx > 0 ? 'r' : 'l';//右,左
        } else {
            //Y轴移动
            return dy > 0 ? 'b' : 't';//下,上
        }
    }


    private ScrollListener mScrollListener;

    public void setScrollListener(ScrollListener scrollListener) {
        mScrollListener = scrollListener;
    }

    public interface ScrollListener {
        boolean canScrollParentY();
    }


}
