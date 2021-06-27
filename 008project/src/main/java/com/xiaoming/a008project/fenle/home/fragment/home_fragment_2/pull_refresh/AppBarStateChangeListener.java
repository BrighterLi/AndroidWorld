package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Desc: AppBarLayout 展开状态监听
 */
public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener{
    public enum State {
        EXPANDED,   // 展开
        COLLAPSED,  // 闭合
        INTERMEDIATE,   //展开过程中
        GONE,   //不可见,不可用
    }

    private State mCurrentState = State.COLLAPSED;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (offset == 0) {
            if (mCurrentState != State.EXPANDED) {
                onStateChanged(appBarLayout, State.EXPANDED);
            }
            mCurrentState = State.EXPANDED;
        } else if (Math.abs(offset) >= appBarLayout.getTotalScrollRange()) {
            if (mCurrentState != State.COLLAPSED) {
                onStateChanged(appBarLayout, State.COLLAPSED);
            }
            mCurrentState = State.COLLAPSED;
        } else {
            if (mCurrentState != State.INTERMEDIATE) {
                onStateChanged(appBarLayout, State.INTERMEDIATE);
            }
            mCurrentState = State.INTERMEDIATE;
        }
    }

    public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
}

