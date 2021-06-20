package com.widget.material_design.coordinatorlayout.coordinatorlayout2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class CommonRecyclerHolder extends ViewHolder {
    private SparseArray<View> views;
    private Context context;

    private CommonRecyclerHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        this.views = new SparseArray(8);
    }

    public static CommonRecyclerHolder getRecyclerHolder(Context context, View itemView) {
        return new CommonRecyclerHolder(context, itemView);
    }

    public SparseArray<View> getViews() {
        return this.views;
    }

    public <T extends View> T getView(int viewId) {
        View view = (View)this.views.get(viewId);
        if (view == null) {
            view = this.itemView.findViewById(viewId);
            this.views.put(viewId, view);
        }

        return (T)view;
    }

    public CommonRecyclerHolder setText(int viewId, String text) {
        TextView tv = (TextView)this.getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonRecyclerHolder setTextColor(int viewId, int color) {
        TextView textView = (TextView)this.getView(viewId);
        textView.setTextColor(color);
        return this;
    }

    public CommonRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = (ImageView)this.getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    public CommonRecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = (ImageView)this.getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    public CommonRecyclerHolder setOnRecyclerItemClickListener(int viewId, OnClickListener listener) {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }
}

