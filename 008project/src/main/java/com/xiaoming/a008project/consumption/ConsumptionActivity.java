package com.xiaoming.a008project.consumption;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextPaint;
import android.util.ArrayMap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.xiaoming.a008project.R;
import com.xiaoming.a008project.consumption.fragment.OneFragment;
import com.xiaoming.a008project.consumption.fragment.ThreeFragment;
import com.xiaoming.a008project.consumption.fragment.TwoFragment;
import com.xiaoming.a008project.consumption.utils.DpUtils;
import com.xiaoming.a008project.consumption.utils.ScreenUtil;
import com.xiaoming.a008project.consumption.view.InterceptScrollView;
import com.xiaoming.a008project.consumption.view.MyTabStrip;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionActivity extends AppCompatActivity {
    public static final int DEFAULT_BG_COLOR = 0xFFF8F9FA;

    private InterceptScrollView mScrollView;
    //private TabLayout tabLayout;
    private MyTabStrip tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter mPageAdapter;
    private LinearLayout container_top;
    private LinearLayout container_normal;
    private View viewPlace;

    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();

    //存放页面和滑动距离的Map
    private ArrayMap<Integer, Integer> scrollMap = new ArrayMap<>();
    //当前页面
    private int currentTab = 0;
    //当前页面的滑动距离
    private int currentScrollY = 0;
    //用于判断，当前页面的导航栏是否悬浮
    private boolean isTabLayoutSuspend;

    private int mNavHeight;
    private int mEdgePadding;
    private int mTabPadding;
    private int mTabMinPadding;
    private int mWindowWidth;
    private int mTabDefaultTxtSize;
    private int mTabSelectedTxtSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        initView();
        //initTabLayout();
        //refreshTabLayout(titleList);
    }

    private void initView() {
        mScrollView = findViewById(R.id.interceptScrollView);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        container_top = findViewById(R.id.container_top);
        container_normal = findViewById(R.id.container_normal);
        viewPlace = findViewById(R.id.view_place);
        //tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        initTabLayout();

        titleList.clear();
        titleList.add("标签一");
        titleList.add("标签二");
        titleList.add("标签三");
        refreshTabLayout(titleList);

        fragmentList.clear();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());

        for (int i = 0; i < titleList.size(); i++) {
            scrollMap.put(i, 0);
        }


        mPageAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragmentList.get(i);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public int getCount() {
                return titleList.size();
            }
        };

        viewPager.setAdapter(mPageAdapter);
        viewPager.setOffscreenPageLimit(titleList.size());
        //tabLayout.setupWithViewPager(viewPager);
        tabLayout.setViewPager(viewPager);

        mScrollView.setScrollChangedListener(new InterceptScrollView.ScrollChangedListener() {
            @Override
            public void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                currentScrollY = scrollY;
                if (scrollY >= DpUtils.dp2px(ConsumptionActivity.this, 60) && tabLayout.getParent() == container_normal) {
                    container_normal.removeView(tabLayout);
                    container_top.addView(tabLayout);
                    viewPlace.setVisibility(View.INVISIBLE);
                    isTabLayoutSuspend = true;
                } else if (scrollY < DpUtils.dp2px(ConsumptionActivity.this, 60) && tabLayout.getParent() == container_top) {
                    container_top.removeView(tabLayout);
                    container_normal.addView(tabLayout, 1);
                    viewPlace.setVisibility(View.GONE);
                    isTabLayoutSuspend = false;
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                //如果导航栏悬浮
                if (isTabLayoutSuspend) {
                    //当前页面的滑动距离为0或者小于60dp，那么只需滑动60dp，让导航栏悬浮即可
                    if (scrollMap.get(i) == 0 || scrollMap.get(i) < DpUtils.dp2px(ConsumptionActivity.this, 60)) {
                        mScrollView.scrollTo(0, DpUtils.dp2px(ConsumptionActivity.this, 60));
                    } else {//如果页面滑动的距离大于60dp，那么直接滑动对应的距离即可
                        mScrollView.scrollTo(0, scrollMap.get(i));
                    }
                } else {//如果导航栏没有悬浮
                    mScrollView.scrollTo(0, currentScrollY);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == 1) {//手指按下时，记录当前页面
                    currentTab = viewPager.getCurrentItem();
                } else if (i == 2) {//手指抬起时
                    if (currentTab != viewPager.getCurrentItem()) {
                        //如果滑动成功，也就是说翻页成功，那么保存之前页面的滑动距离
                        scrollMap.put(currentTab, currentScrollY);
                    }
                }
            }
        });
    }

    private void initTabLayout() {
        int defaultColor = 0xFF84888F;
        int selectedColor = 0xFF050C1C;
        int lineColor = 0xFFE0E0E0;

        mWindowWidth = ScreenUtil.getWindowWidth(this);
        //mEdgePadding = ScreenUtil.dip2pxInt(this, 16);
        mEdgePadding = ScreenUtil.dip2pxInt(this, 0);
        mTabPadding = ScreenUtil.dip2pxInt(this, 10);
        mTabMinPadding = ScreenUtil.dip2pxInt(this, 5);
        mTabDefaultTxtSize = ScreenUtil.sp2px(this, 15);
        mTabSelectedTxtSize = ScreenUtil.sp2px(this, 15);
        //tabLayout = new MyTabStrip(this);
        //tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setBackgroundColor(0xFFFFFFF);
        // tabLayout.setTabBackgroundRes(R.drawable.selector_background_ripple);
        tabLayout.setUnderlineHeight(0);
        tabLayout.setUnderlineColor(lineColor);

        //tabLayout.setIndicatorColor(GrayManager.getInstance().grayColor(0xFFFF3D77));
        tabLayout.setIndicatorLength(ScreenUtil.dip2pxInt(this, 14));
        tabLayout.setIndicatorHeight(ScreenUtil.dip2pxInt(this, 3));
        tabLayout.setIndicatorCorner(ScreenUtil.dip2pxInt(this, 2));
        tabLayout.setIndicatorBottomMargin(ScreenUtil.dip2pxInt(this, 6));
        tabLayout.setIndicatorColor(0xFFFF3D77);

        //tabLayout.setDividerColor(0xFFE4E5E7);
        tabLayout.setDividerWidth(ScreenUtil.dip2pxInt(this, 1));
        tabLayout.setDividerPadding(ScreenUtil.dip2pxInt(this, 15));

        tabLayout.setScrollOffset(ScreenUtil.getWindowWidth(this) * 3 / 10);
        tabLayout.setDefaultTextColor(defaultColor, false);
        tabLayout.setSelectedTextColor(selectedColor, false);
        tabLayout.setDefaultTextSize(mTabDefaultTxtSize, false);
        tabLayout.setSelectedTextSize(mTabSelectedTxtSize, false);
        tabLayout.setTabPaddingLeftRight(mTabPadding, false);
        tabLayout.setEdgeLeftRight(mEdgePadding, false);
        mNavHeight = ScreenUtil.dip2pxInt(ConsumptionActivity.this, 50);
        //tabLayout.setBackgroundColor(Color.YELLOW);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNavHeight);
        params.leftMargin = mEdgePadding;
        tabLayout.setLayoutParams(params);

        /*tabLayout.setScrollListener(new MyTabStrip.ScrollListener() {
            @Override
            public boolean canScrollParentY() {
                *//*RecyclerView innerRv = getCrtInnerRv();
                return innerRv == null || RecyclerViewUtil.isOnTop(innerRv);*//*
            }
        });*/
        /*ConsumeRelativeLayout relativeLayout = new ConsumeRelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        relativeLayout.setBackgroundColor(DEFAULT_BG_COLOR);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNavHeight);
        params.leftMargin = mEdgePadding;
        tabLayout.setLayoutParams(params);
        relativeLayout.addView(tabLayout);
        mRvConsumeRoot.addHeaderView(relativeLayout);*/
    }

    private void refreshTabLayout(List<String> titleList) {
        int size = titleList == null ? 0 : titleList.size();
        // [2,6]数目内tab均匀分布
        if (size >= 2 && size <= 6) {
            // 测量文字宽度
            TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            paint.density = getResources().getDisplayMetrics().density;
            paint.setTextSkewX(0);
            paint.setFakeBoldText(false);
            paint.setTextSize(mTabDefaultTxtSize);
            ArrayList<Float> widthList = new ArrayList<>(size);
            int maxIndex = 0;
            for (int i = 0; i < size; i++) {
                String bean = titleList.get(i);
                float w = ((bean == null) ? 0 : paint.measureText(bean));
                widthList.add(w);
                if (i > 0) {
                    if (w > widthList.get(i - 1)) {
                        maxIndex = i;
                    }
                }
            }
            // 测量最大总长度
            String bean = titleList.get(maxIndex);
            if (bean != null && bean != null) {
                paint.setFakeBoldText(true);
                paint.setTextSize(mTabSelectedTxtSize);
                float w = paint.measureText(bean);
                widthList.remove(maxIndex);
                widthList.add(w);
            }
            int totalTxtMeasureWidth = 0;
            for (Float w : widthList) {
                totalTxtMeasureWidth += w;
            }
            // 计算单个边距
            float padding = (mWindowWidth - mEdgePadding * 2 - totalTxtMeasureWidth) / (size * 2f);
            padding = Math.max((int) padding, mTabMinPadding);
            tabLayout.setTabPaddingLeftRight((int) padding, false);
            tabLayout.setEdgeLeft((int) padding, false);
            tabLayout.setEdgeRight((int) (mEdgePadding + padding), false);
        } else if (size > 6) {//最右端正好显示完第6.5个tab
            // 只有左边距
            TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            paint.density = getResources().getDisplayMetrics().density;
            paint.setTextSkewX(0);
            paint.setFakeBoldText(false);
            paint.setTextSize(mTabDefaultTxtSize);
            float with = paint.measureText("推荐") * 5.5f;
            paint.setFakeBoldText(true);
            paint.setTextSize(mTabSelectedTxtSize);
            with += paint.measureText("推荐");
            float padding = (mWindowWidth - mEdgePadding - with) / 6f / 2f;
            tabLayout.setTabPaddingLeftRight((int) padding, false);
            tabLayout.setEdgeLeft(0, false);
            tabLayout.setEdgeRight(mEdgePadding, false);
        } else {// 一个
            tabLayout.setPadding(0, 0, 0, 0);
            TextPaint paint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
            paint.density = getResources().getDisplayMetrics().density;
            paint.setTextSkewX(0);
            paint.setFakeBoldText(true);
            paint.setTextSize(mTabSelectedTxtSize);
            float with = paint.measureText("推荐");
            float padding = (mWindowWidth - with) / 2;
            tabLayout.setTabPaddingLeftRight(0, false);
            tabLayout.setEdgeLeft((int) padding - mEdgePadding, false);
            tabLayout.setEdgeRight((int) padding, false);
        }
    }
}
