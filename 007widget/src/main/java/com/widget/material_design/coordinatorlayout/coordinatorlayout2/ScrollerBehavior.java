package com.widget.material_design.coordinatorlayout.coordinatorlayout2;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class ScrollerBehavior extends CoordinatorLayout.Behavior<RecyclerView> {

    public ScrollerBehavior() {
    }

    public ScrollerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RecyclerView child, View dependency) {
        ViewCompat.offsetTopAndBottom(child, (dependency.getBottom() - child.getTop()));
        return false;
    }
}
