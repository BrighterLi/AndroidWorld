package com.tencent.tecentim.order;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ImageView;

import com.tencent.tecentim.R;
import com.tencent.tecentim.order.tabLayout.XTabLayout;
import com.tencent.tecentim.utils.ScreenUtil;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class ProductOrderActivity extends FragmentActivity {
    private LinearLayout mProductOrderLl;
    private ImageView mOrderClose;

    private XTabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentStatePagerAdapter mPageAdapter;
    private ArrayList<String> mTitleList;
    private ArrayList<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_order);
        mProductOrderLl = findViewById(R.id.product_order);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, ScreenUtil.getWindowHeight(this) - (int) ScreenUtil.dip2px(this, 383), 0, 0);
        mProductOrderLl.setLayoutParams(layoutParams);

        mOrderClose = findViewById(R.id.iv_order_close);
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewPager);
        mOrderClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addTabView();
    }

    private void initTab() {
        mPageAdapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return mFragmentList.get(i);
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitleList.get(position);
            }


            @Override
            public int getCount() {
                return mTitleList.size();
            }
        };

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setOffscreenPageLimit(mTitleList.size());
        mTabLayout.setupWithViewPager(mViewPager);

    }

    private void addTabView() {
        mTitleList = new ArrayList<>();
        mFragmentList = new ArrayList<>();
        mTitleList.add("我的订单");
        //mTitleList.add("");
        //mTitleList.add("");

        ProductOrderFragment fragment = new ProductOrderFragment();
        //ProductOrderFragment fragment2 = new ProductOrderFragment();
        //ProductOrderFragment fragment3 = new ProductOrderFragment();

        mFragmentList.add(fragment);
        //mFragmentList.add(fragment2);
        //mFragmentList.add(fragment3);

        initTab();
    }
}
