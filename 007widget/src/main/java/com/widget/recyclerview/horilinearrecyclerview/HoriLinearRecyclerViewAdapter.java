package com.widget.recyclerview.horilinearrecyclerview;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.widget.R;

public class HoriLinearRecyclerViewAdapter extends RecyclerView.Adapter<HoriLinearRecyclerViewAdapter.HoriRecyclerViewHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    //创建一个构造函数
    public HoriLinearRecyclerViewAdapter(Context context) {
        this.mContext = context;
        //利用LayoutInflater把控件所在的布局文件加载到当前类中
        //获取LayoutInflater的对象
        mLayoutInflater = LayoutInflater.from(context);
    }

    //返回一个ViewHolder
    @Override
    public HoriLinearRecyclerViewAdapter.HoriRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        HoriLinearRecyclerViewAdapter.HoriRecyclerViewHolder horiRecyclerViewHolder = new HoriRecyclerViewHolder(
                mLayoutInflater.inflate(R.layout.layout_hori_linear_rv_item, parent, false)
        );
        return horiRecyclerViewHolder;
    }

    //通过holder设置itemView的具体内容
    @Override
    public void onBindViewHolder(HoriLinearRecyclerViewAdapter.HoriRecyclerViewHolder holder, int position) {
        holder.textView.setText("Hello bright");
        holder.imageView.setImageResource(R.drawable.liner_rv_pic);
    }

    //列表数目
    @Override
    public int getItemCount() {
        return 25;
    }

    class HoriRecyclerViewHolder extends RecyclerView.ViewHolder{
        //声明layout_hori_linear_rv_item的内容
        private TextView textView;
        private ImageView imageView;

        //获取控件对象
        public HoriRecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.hori_linear_rv_item_title_id);
            imageView = itemView.findViewById(R.id.hori_linear_rv_item_pic_id);
        }
    }
}
