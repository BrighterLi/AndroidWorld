package com.widget.listview.backtotop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.widget.R;

import java.util.List;

public class MyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData;

    public MyListViewAdapter(Context context, List<String> data) {
        this.mContext = context;
        this.mData = data;
    }

    @Override
    public int getCount() {
        return this.mData == null ? 0 : this.mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyListViewAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new MyListViewAdapter.ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.view_list_item, parent, false);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.list_item_tv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (MyListViewAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.bindData(mData.get(position));
        return convertView;
    }


    private static class ViewHolder {
        public TextView tv;

        public void bindData(String str) {
            tv.setText(str);
        }
    }
}
