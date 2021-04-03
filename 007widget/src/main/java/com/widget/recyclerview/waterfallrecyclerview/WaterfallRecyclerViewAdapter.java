package com.widget.recyclerview.waterfallrecyclerview;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.widget.R;


public class WaterfallRecyclerViewAdapter extends RecyclerView.Adapter<WaterfallRecyclerViewAdapter.WaterfallViewHolder> {
    //声明引用
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //创建一个构造函数
    public WaterfallRecyclerViewAdapter(Context context) {
        this.mContext = context;
        //利用LayoutInflater把控件所在的布局文件加载到当前类当中
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    //返回ViewHolder
    @Override
    public WaterfallRecyclerViewAdapter.WaterfallViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //返回一个ViewHolder类型的数据，返回ViewHolder子类的对象，把布局文件加载到当前类返回
        WaterfallRecyclerViewAdapter.WaterfallViewHolder viewHolder = new WaterfallRecyclerViewAdapter.WaterfallViewHolder(mLayoutInflater.inflate(R.layout.layout_waterfall_rv_item,parent,false));
        return viewHolder;
    }

    //设置item具体的值
    @Override
    public void onBindViewHolder(WaterfallRecyclerViewAdapter.WaterfallViewHolder holder, final int position) {
        if(position % 2 == 0) {
            holder.imageView.setImageResource(R.drawable.liner_rv_pic);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_launcher_background);
        }

        //设置监听器，短点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "短点击位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });

        //长点击
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(mContext, "长点击位置：" + position, Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }

    //返回Item个数
    @Override
    public int getItemCount() {
        return 25;
    }

    class WaterfallViewHolder extends RecyclerView.ViewHolder{
        //声明引用
        private ImageView imageView;

        public WaterfallViewHolder(View itemView) {
            super(itemView);
            //获取控件对象
            imageView = itemView.findViewById(R.id.waterfall_rv_item_pic_id);
        }
    }
}
