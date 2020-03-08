package com.xiaoming.widgetrecyclerview.twoitemsrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.xiaoming.widgetrecyclerview.R;

public class TwoItemsRecyclerViewAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    //声明引用
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private RecyclerView.OnItemTouchListener mListener;

    //创建一个构造函数  RecyclerView.OnItemTouchListener ?
    public TwoItemsRecyclerViewAdapter(Context mContext, RecyclerView.OnItemTouchListener mListener) {
        this.mContext = mContext;
        //利用LayoutInflater把控件所在的布局文件加载到当前类当中
        mLayoutInflater = LayoutInflater.from(mContext);
        //从外部传进来一个OnItemClickListener子类的变量
        this.mListener = mListener;
    }

    //此方法要返回一个ViewHolder
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == 0) {
            return new ViewHolder(mLayoutInflater.inflate(R.layout.layout_rv_two_item1, parent, false));
        } else {
            return new ViewHolder2(mLayoutInflater.inflate(R.layout.layout_rv_two_item2, parent, false));
        }
    }

    //通过holder设置TextView的内容
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(getItemViewType(position) == 0) {
            ((ViewHolder)holder).textView.setText("bright");
            ((ViewHolder) holder).imageView.setImageResource(R.drawable.liner_rv_pic);
        } else {
            ((ViewHolder2) holder).imageView.setImageResource(R.drawable.linear_rv_pic2);
        }

        //点击
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //点击后显示点击位置
                Toast.makeText(mContext, "点击位置：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //列表item数量
    @Override
    public int getItemCount() {
        return 25;
    }

    //此方法可以知道当前的item的位置，可以根据不同的位置设置不同的ViewHolder
    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0) {  //位置是偶数
            return 0;
        } else {  // 位置是奇数
          return 1;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //声明layout_rv_two_item1布局控件的变量
        private TextView textView;
        private ImageView imageView;

        //获取布局中的控件
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.rv_two_item1_title_id);
            imageView = itemView.findViewById(R.id.rv_two_item1_pic_id);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        // 声明layout_rv_two_item2布局控件的变量
        private ImageView imageView;

        public ViewHolder2(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.rv_two_item2_pic_id);
        }
    }

    //?
    //定义一个接口
    public interface OnItemClickListener {
        //接口默认都是抽象的方法，且类型都是public
        void onClick(int position);
    }
}
