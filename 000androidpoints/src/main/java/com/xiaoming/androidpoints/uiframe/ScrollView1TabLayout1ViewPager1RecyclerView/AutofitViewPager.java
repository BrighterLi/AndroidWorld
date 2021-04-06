package com.xiaoming.androidpoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by brightli on 2020/9/23
 */
public class AutofitViewPager extends ViewPager {

    public AutofitViewPager(@NonNull Context context) {
        this(context,null);
    }

    public AutofitViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                requestLayout();//保证每次选中当前页时，计算高度，达到高度自适应效果
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //重写onMeasure，解决高度显示为0，同时高度动态显示为当前子项的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec,
                    MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if(i==getCurrentItem()){
                height=h;
            }
        }
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height,
                MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}

