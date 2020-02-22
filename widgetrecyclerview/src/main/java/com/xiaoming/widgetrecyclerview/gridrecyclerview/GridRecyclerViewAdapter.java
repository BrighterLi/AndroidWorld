package com.xiaoming.widgetrecyclerview.gridrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.xiaoming.widgetrecyclerview.R;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.GridViewHolder> {
    //声明引用
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //创建一个构造函数
    public GridRecyclerViewAdapter(Context context) {
        this.mContext =context;
        //利用LayoutInflater把控件所在的布局文件加载到当前类当中
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    //返回一个ViewHolder
    @Override
    public GridRecyclerViewAdapter.GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回一个ViewHolder类型的数据，返回ViewHolder子类的对象，把布局文件加载到当前类返回
        GridRecyclerViewAdapter.GridViewHolder viewHolder = new GridViewHolder(mLayoutInflater.inflate(R.layout.layout_grid_rv_item,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridRecyclerViewAdapter.GridViewHolder holder, int position) {
        //设置itemView的控件具体的值
        holder.textView.setText("Hello bright");
        holder.imageView.setImageResource(R.drawable.liner_rv_pic);
    }

    //返回列表item个数
    @Override
    public int getItemCount() {
        return 25;
    }

    class GridViewHolder extends RecyclerView.ViewHolder{
        //声明引用
        private TextView textView;
        private ImageView imageView;

        public GridViewHolder(View itemView) {
            super(itemView);
            //获取控件对象
            textView = itemView.findViewById(R.id.grid_rv_item_title_id);
            imageView = itemView.findViewById(R.id.grid_rv_item_pic_id);
        }
    }
}

