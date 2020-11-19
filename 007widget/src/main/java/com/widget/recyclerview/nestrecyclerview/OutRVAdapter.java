package com.widget.recyclerview.nestrecyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.widget.R;

import java.util.List;

public class OutRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
   private Context mContext;
   private List<Fruit> mFruitList;

    public OutRVAdapter(Context context, List<Fruit> fruitList) {
        this.mContext = context;
        this.mFruitList = fruitList;
    }

    //通过这个函数判断当前是第几个item，将序号返回后加载相应的布局
    @Override
    public int getItemViewType(int position) {
        int flag = 0;
        if(position == 0) {
            flag = 1;
        } else {
            flag = 2;
        }
        return flag;
    }

    //这里通过viewType判断加载何种布局，并返回相对应的holder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if(viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.out_top_inner_rv_item, parent, false);
            holder = new TopViewHolder(view);
        } else if(viewType ==2) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.out_rv_item,parent,false);
            holder = new BottomViewHolder(view);
        }
        return holder;
    }

    //判断是谁的的是谁家的holder，从而将数据传到布局中
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof TopViewHolder) {
            setTop((TopViewHolder) holder, position);
        } else if(holder instanceof BottomViewHolder) {
            setBottom((BottomViewHolder) holder, position);
        }
    }

    //返回item的量
    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    //这个就是设置顶部RecyclerView的样式，这里将顶部RecyclerView样式设置成横滑动
    private void setTop(TopViewHolder holder, int position){
       if(position == 0) {
           TopInnerRVAdapter topInnerRVAdapter = new TopInnerRVAdapter(mContext,mFruitList);
           LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
           layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
           holder.recyclerView.setLayoutManager(layoutManager);
           holder.recyclerView.setHasFixedSize(false);
           holder.recyclerView.setAdapter(topInnerRVAdapter);
       }
    }

    //把名字和图片传进去
    private void setBottom(BottomViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position - 1);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }


    //一个RecyclerView中有几种内部item就有几个内部类，这是第一种内部item，
    //也就是GIF种ImageView和TextView横向排列的item
    class BottomViewHolder extends RecyclerView.ViewHolder{
        private ImageView fruitImage;
        private TextView fruitName;

        public BottomViewHolder(View itemView) {
            super(itemView);
            fruitImage = itemView.findViewById(R.id.out_rv_item_image);
            fruitName = itemView.findViewById(R.id.out_rv_item_text);
        }
    }

    //这是第二种，也就是顶部横向RecyclerView
    //这个RecyclerView的适配器就是上面那个部分的代码
    class TopViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;

        public TopViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.out_inner_rv_top_item_rv);
        }
    }
}
