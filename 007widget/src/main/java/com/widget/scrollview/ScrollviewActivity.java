package com.widget.scrollview;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.ArrayMap;
import android.widget.LinearLayout;

import java.util.ArrayList;

import com.widget.R;


public class ScrollviewActivity extends AppCompatActivity {

    private InterceptScrollView mScrollView;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter mPageAdapter;
    private LinearLayout container_top;
    private LinearLayout container_normal;
    private View viewPlace;

    private ArrayList<String> titleList = new ArrayList<>();
    private ArrayList<Fragment> fragmentList=new ArrayList<>();

    //存放页面和滑动距离的Map
    private ArrayMap<Integer,Integer> scrollMap=new ArrayMap<>();
    //当前页面
    private int currentTab=0;
    //当前页面的滑动距离
    private int currentScrollY=0;
    //用于判断，当前页面的导航栏是否悬浮
    private boolean isTabLayoutSuspend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);

        mScrollView=findViewById(R.id.interceptScrollView);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        container_top = findViewById(R.id.container_top);
        container_normal = findViewById(R.id.container_normal);
        viewPlace=findViewById(R.id.view_place);

        titleList.clear();
        titleList.add("标签一");
        titleList.add("标签二");
        titleList.add("标签三");

        fragmentList.clear();
        fragmentList.add(new OneFragment());
        fragmentList.add(new TwoFragment());
        fragmentList.add(new ThreeFragment());

        for(int i=0;i<titleList.size();i++){
            scrollMap.put(i,0);
        }

        mPageAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        tabLayout.setupWithViewPager(viewPager);

        mScrollView.setScrollChangedListener(new InterceptScrollView.ScrollChangedListener() {
            @Override
            public void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                currentScrollY=scrollY;
                if (scrollY >= DpUtils.dp2px(ScrollviewActivity.this, 60)&&tabLayout.getParent()==container_normal) {
                    container_normal.removeView(tabLayout);
                    container_top.addView(tabLayout);
                    viewPlace.setVisibility(View.INVISIBLE);
                    isTabLayoutSuspend=true;
                } else if(scrollY < DpUtils.dp2px(ScrollviewActivity.this, 60)&&tabLayout.getParent()==container_top){
                    container_top.removeView(tabLayout);
                    container_normal.addView(tabLayout, 1);
                    viewPlace.setVisibility(View.GONE);
                    isTabLayoutSuspend=false;
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
                if(isTabLayoutSuspend){
                    //当前页面的滑动距离为0或者小于60dp，那么只需滑动60dp，让导航栏悬浮即可
                    if(scrollMap.get(i)==0||scrollMap.get(i)<DpUtils.dp2px(ScrollviewActivity.this,60)){
                        mScrollView.scrollTo(0,DpUtils.dp2px(ScrollviewActivity.this,60));
                    }else{//如果页面滑动的距离大于60dp，那么直接滑动对应的距离即可
                        mScrollView.scrollTo(0,scrollMap.get(i));
                    }
                }else{//如果导航栏没有悬浮
                    mScrollView.scrollTo(0,currentScrollY);
                }

            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if(i==1){//手指按下时，记录当前页面
                    currentTab=viewPager.getCurrentItem();
                }else if(i==2){//手指抬起时
                    if(currentTab!=viewPager.getCurrentItem()){
                        //如果滑动成功，也就是说翻页成功，那么保存之前页面的滑动距离
                        scrollMap.put(currentTab,currentScrollY);
                    }
                }
            }
        });
    }
}
