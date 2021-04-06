package com.widget.recyclerview.nestrecyclerview;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.widget.R;

import java.util.List;

public class TopInnerRVAdapter extends RecyclerView.Adapter<TopInnerRVAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;

    //每个类标配的构造函数
    public TopInnerRVAdapter(Context context, List<Fruit> fruitList) {
        this.mContext = context;
        this.mFruitList = fruitList;
    }

    @Override
    public TopInnerRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.top_inner_rv_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TopInnerRVAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText("横向展示：" + fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    //有几种内部item，就有几个内部类
    //这里的是ImageView和TextView纵向排列的布局
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView fruitImage;
        private TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.top_inner_rv_image);
            fruitName = itemView.findViewById(R.id.top_inner_rv_text);
        }
    }
}
