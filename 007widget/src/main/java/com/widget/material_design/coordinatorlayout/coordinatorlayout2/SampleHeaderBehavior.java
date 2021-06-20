package com.widget.material_design.coordinatorlayout.coordinatorlayout2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;

public class SampleHeaderBehavior extends CoordinatorLayout.Behavior<TextView> {

    private int mOffsetTopAndBottom;
    private int mLayoutTop;

    public SampleHeaderBehavior() {
    }

    public SampleHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, TextView child, int layoutDirection) {

        parent.onLayoutChild(child,layoutDirection);
        mLayoutTop = child.getTop();
        return true;
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        return true;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

        int consumedy = 0; // 记录我们消费的距离
        int offset = mOffsetTopAndBottom - dy;
        int minOffset = -getChildScrollRang(child);
        int maxOffset = 0;
        offset = offset < minOffset ? minOffset : (offset > maxOffset ? maxOffset : offset);
        ViewCompat.offsetTopAndBottom(child, offset - (child.getTop() - mLayoutTop));
        consumedy = mOffsetTopAndBottom - offset;
        // 将本次滚动到的位置记录下来
        mOffsetTopAndBottom = offset;
        consumed[1] = consumedy;
    }

    // 获取childView最大可滑动距离
    private int getChildScrollRang(View childView) {
        if (childView == null) {
            return 0;
        }
        return childView.getHeight();
    }

    @Override
    public boolean onNestedPreFling(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY);
    }
}
