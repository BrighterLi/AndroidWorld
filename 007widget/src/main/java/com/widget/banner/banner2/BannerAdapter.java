package com.widget.banner.banner2;

import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import androidx.viewpager.widget.PagerAdapter;

public abstract class BannerAdapter<T> extends PagerAdapter {

    private Queue<View> viewPool = new LinkedList<>();
    private List<T> mList;
    private boolean isUseCache = true;
    private boolean isLoop = true;

    public BannerAdapter(List<T> list) {
        mList = list;
    }

    public BannerAdapter(List<T> list, boolean isUseCache) {
        mList = list;
        this.isUseCache = isUseCache;
    }

    public void setLoop(boolean loop) {
        isLoop = loop;
    }

    public void setData(List<T> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mList;
    }

    public int getDataSize() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getCount() {
        if (isLoop) {
            if (getDataSize() == 0) {
                return 0;
            }
            return Banner.VIEWPAGER_TOTAL_NUMBER;
        } else {
            return getDataSize();
        }
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        if (isUseCache) {
            viewPool.offer((View) object);
        }
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mList != null && mList.size() > 0) {
            position = position % mList.size();
            T t = mList.get(position);

            View v;

            if (isUseCache && viewPool.size() > 0) {
                v = viewPool.poll();
            } else {
                v = createView(container, t, position);
            }

            bindData(v, t, position);
            container.addView(v);
            return v;
        } else {
            return new View(container.getContext());
        }
    }

    public abstract View createView(ViewGroup container, T t, int position);

    public abstract void bindData(View view, T t, int position);

}

