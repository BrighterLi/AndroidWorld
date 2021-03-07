package com.widget.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.widget.R;

import java.util.List;

//适配器
public class MyPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mData;

    public MyPagerAdapter(Context context ,List<String> list) {
        mContext = context;
        mData = list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = View.inflate(mContext, R.layout.item_base,null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        TextView tv2 = view.findViewById(R.id.tv2);
        tv.setText(mData.get(position));
        tv2.setText(mData.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // super.destroyItem(container,position,object); 这一句要删除，否则报错
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
