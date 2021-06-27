package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh;

import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.HomeFragment2;

public interface InnerViewScrollState {
    /**
     *
     * @return true, 自身下滑事件会被拦截
     * 在{@link HomeFragment2}中下滑时会由父控件下滑
     *
     */
    boolean scrollToTop();
}

