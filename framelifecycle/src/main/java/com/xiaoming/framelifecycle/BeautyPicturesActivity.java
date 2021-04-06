package com.xiaoming.framelifecycle;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.xiaoming.framelifecycle.http.Network;
import com.xiaoming.framelifecycle.model.BeautyBean;
import com.xiaoming.framelifecycle.rx.SafeObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class BeautyPicturesActivity extends BaseAppActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, BeautyPicturesActivity.class);
        return intent;
    }

    private RecyclerView mRecyclerView;
    private MyAdapter mMyAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pictures;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mMyAdapter = new MyAdapter(this);
        mRecyclerView.setAdapter(mMyAdapter);
        getDataSafe();
        setTitle(getTitle() + "-Beauty");
    }

    private void getDataSafe() {
        Network.getApiService().getBeauty(20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SafeObserver<BeautyBean>(getLifecycle()) {
                    @Override
                    public void onNext(BeautyBean beautyBean) {
                        //可以安全地执行UI操作
                        if (!beautyBean.error) {
                            mMyAdapter.refreshData(beautyBean);
                        }
                    }
                });
    }


    private static class MyAdapter extends RecyclerView.Adapter {
        private Context mContext;
        private BeautyBean mBeautyBean;

        public MyAdapter(Context context) {
            mContext = context;
        }

        public void refreshData(BeautyBean bean) {
            mBeautyBean = bean;
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_beauty, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (mBeautyBean == null) {
                return;
            }
            BeautyBean.ResultBean resultBean = mBeautyBean.results.get(position);
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            myViewHolder.mTextView.setText(resultBean.desc);
            Glide.with(mContext)
                    .load(resultBean.url)
                    .into(myViewHolder.mImageView);
            Log.d("BeanPicturesA", "bright#resultBean.url：" + resultBean.url);
        }

        @Override
        public int getItemCount() {
            if(mBeautyBean == null) {
                return 0;
            } else {
                return mBeautyBean.results.size();
            }
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.iv_beauty);
            mTextView = itemView.findViewById(R.id.tv_desc);
        }
    }
}
