package com.xiaoming.androidpoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView;

import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.core.app.Fragment;
import androidx.core.app.FragmentPagerAdapter;
import androidx.core.view.ViewPager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.ArrayMap;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaoming.androidpoints.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import android.widget.TextView;

import static com.xiaoming.androidpoints.aaautils.ScreenUtil.dip2px;

//Android开发之复杂布局嵌套(ScrollView+TabLayout+ViewPager+RecyclerView)导致冲突的解决办法
//https://blog.csdn.net/hq942845204/article/details/88844272

public class UiFrame3Activity extends AppCompatActivity {
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
    private ArrayMap<Integer,Integer> scrollMap = new ArrayMap<>();
    //当前页面
    private int currentTab=0;
    //当前页面的滑动距离
    private int currentScrollY=0;
    //用于判断，当前页面的导航栏是否悬浮
    private boolean isTabLayoutSuspend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_frame3);

        mScrollView=findViewById(R.id.interceptScrollView);
        tabLayout=findViewById(R.id.tabLayout);
        //reflex(tabLayout);
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
                if (scrollY >= DpUtils.dp2px(UiFrame3Activity.this, 60)&&tabLayout.getParent()==container_normal) {
                    container_normal.removeView(tabLayout);
                    container_top.addView(tabLayout); //放到外面滑动列表外顶部悬空
                    viewPlace.setVisibility(View.INVISIBLE);
                    isTabLayoutSuspend=true;
                } else if(scrollY < DpUtils.dp2px(UiFrame3Activity.this, 60)&&tabLayout.getParent()==container_top){
                    container_top.removeView(tabLayout); //放到外部滑动列表内部
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
                    if(scrollMap.get(i)==0||scrollMap.get(i)<DpUtils.dp2px(UiFrame3Activity.this,60)){
                        mScrollView.scrollTo(0,DpUtils.dp2px(UiFrame3Activity.this,60));
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

    //？改变tabLayout指示器宽度
    public void reflex(final TabLayout tabLayout){
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), 10);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width ;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
