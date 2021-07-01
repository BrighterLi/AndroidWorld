package com.xiaoming.a008project.fenle.test.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.xiaoming.a008project.R;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//GridLayout来控制同时一行显示多列和1列
//GridLayoutManager的 setSpanSizeLookup方法使用:https://blog.csdn.net/xiaolaohuqwer/article/details/87868838
public class RecyclerViewDemoActivity extends AppCompatActivity {
    //@BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        //ButterKnife.bind(this);
        mRecyclerView = findViewById(R.id.recyclerView);

        initView();
    }

    private void initView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                // 显示的列数 = spanCount / spanSize ;


                if (position% 5 == 4){
                    //SpanSize 返回4 代表该行只显示1列    列数 = 4/4 =1
                    return 4;
                }else{
                    //SpanSize 返回1代表该行显示4列    列数 = 4/1 =4
                    return 1;
                }
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);

        for (int i = 0; i < 100; i++) {
            datas.add("测试数据  " + i);
        }
        DataAdapter dataAdapter = new DataAdapter(this, datas);
        mRecyclerView.setAdapter(dataAdapter);

    }


    class DataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private Context mContext;
        private List<String> mDatas;
        private LayoutInflater mLayoutInflater;

        private DataAdapter(Context context, List<String> datas) {
            mContext = context;
            mDatas = datas;
            mLayoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RecyclerView.ViewHolder viewHolder = new ViewHolder(mLayoutInflater.inflate(R.layout.item_data, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.bindPosition(position);

        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            //@BindView(R.id.tv_content)
            private TextView mTvContent;
            public ViewHolder(View itemView) {
                super(itemView);
                //ButterKnife.bind(this, itemView);
                mTvContent = itemView.findViewById(R.id.tv_content);
            }

            public void bindPosition(int position) {
                String title = mDatas.get(position);
                mTvContent.setText(title);
            }
        }
    }

}