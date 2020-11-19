package com.widget.recyclerview.linearrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.widget.R;


public class LinearRecyclerViewAdapter extends RecyclerView.Adapter<LinearRecyclerViewAdapter.LinearViewHolder> {
   //声明引用
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //创建一个构造函数
    public LinearRecyclerViewAdapter(Context context) {
        this.mContext = context;
        //利用LayoutInflater把控件所在的布局文件加载到当前类中
        this.mLayoutInflater = mLayoutInflater.from(context);
    }

    //返回一个ViewHolder
    @Override
    public LinearRecyclerViewAdapter.LinearViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载item的布局文件
        LinearRecyclerViewAdapter.LinearViewHolder viewHolder = new LinearViewHolder(mLayoutInflater.inflate(R.layout.layout_linear_rv_item, parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LinearRecyclerViewAdapter.LinearViewHolder holder, final int position) {
        //通过holder设置TextView的内容,设置ImageView的内容
        holder.textView.setText("Hello bright");
        holder.imageView.setImageResource(R.drawable.liner_rv_pic);

        //直接在LinearAdapter的onBindViewHolder()方法中直接实现监听器接口，然后绑定监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示点击位置
                Toast.makeText(mContext, "点击位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //item的数量设为25
    @Override
    public int getItemCount() {
        return 25;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder{
        //声明layout_linear_lv_item布局控件的变量
        private TextView textView;
        private ImageView imageView;

        //获取控件对象
        public LinearViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.linear_rv_item_title_id);
            imageView = itemView.findViewById(R.id.linear_rv_item_pic_id);
        }
    }
}
