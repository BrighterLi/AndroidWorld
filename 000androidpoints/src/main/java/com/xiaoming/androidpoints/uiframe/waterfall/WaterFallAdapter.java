package com.xiaoming.androidpoints.uiframe.waterfall;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xiaoming.androidpoints.R;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.LinearViewHolder>{

    private Context mContext;
    private OnItemClickListener mlistener;  //分配空间
    //private List<String> list;
    public WaterFallAdapter(Context context, OnItemClickListener listener){
        this.mContext=context;             //内容
        this.mlistener=listener;          //传输位置
    }
    @NonNull
    @Override
    public WaterFallAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.activity_water_fall_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull WaterFallAdapter.LinearViewHolder viewHolder, final int i) {
        if(i % 2 !=0){
            viewHolder.imageView.setImageResource(R.drawable.waterfall1);  //位置为奇数
        }
        else{
            viewHolder.imageView.setImageResource(R.drawable.waterfall2); //内容
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {  //监听器按键
            @Override
            public void onClick(View v) {
                mlistener.onClick(i);       //监听器
            }
        });
    }

    @Override
    public int getItemCount() {
        return 80;
    }
    class LinearViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public LinearViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv);
        }
    }
    public interface OnItemClickListener{  //字节型监听器
        void onClick(int pos);

    }
}
