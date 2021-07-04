package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.xiaoming.a008project.R;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header.CollapsibleHeader;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.PtrAppbarFrameLayout;
import com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.pull_refresh.PtrCommonHeader;
import com.xiaoming.a008project.fenle.test.recyclerview.RecyclerViewDemoActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import in.srain.cube.views.wt.PtrFrameLayout;
import in.srain.cube.views.wt.PtrHandler;


public class HomeFragment2 extends Fragment {
    RecyclerView mRecyclerView;
    private List<String> datas = new ArrayList<>();

    private View mRootView;
    private PtrAppbarFrameLayout mPtrRootFrame;
    private PtrCommonHeader mPtrCommonHeader;
    private AppBarLayout mAppBarLayout;
    private CollapsibleHeader collapsibleHeader;


    public HomeFragment2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_home_2, container, false);
        initView();
        return mRootView;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void initView() {
        mPtrRootFrame = mRootView.findViewById(R.id.mPtrRootFrame);
        mPtrCommonHeader = mRootView.findViewById(R.id.mPtrFloorHeader);
        mAppBarLayout = mRootView.findViewById(R.id.appBarLayout);
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
        initPullToRefresh();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4);
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

        for (int i = 0; i < 200; i++) {
            datas.add("测试数据  " + i);
        }
        HomeFragment2.DataAdapter dataAdapter = new HomeFragment2.DataAdapter(getActivity(), datas);
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
            RecyclerView.ViewHolder viewHolder = new HomeFragment2.DataAdapter.ViewHolder(mLayoutInflater.inflate(R.layout.item_data, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            HomeFragment2.DataAdapter.ViewHolder viewHolder = (HomeFragment2.DataAdapter.ViewHolder) holder;
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

    private void initPullToRefresh() {
        mPtrRootFrame.setResistance(2.0f); //设置下拉的阻尼系数，值越大感觉越难下拉
        int offset = mPtrCommonHeader.getRefreshOffset();
        mPtrRootFrame.setOffsetToRefresh(offset);
//        mPtrRootFrame.setMaxPullDownY(offset + ScreenUtil.dip2pxInt(getContext(), 10));
        mPtrRootFrame.setOffsetToKeepHeaderWhileLoading(offset);
        mPtrRootFrame.setDurationToClose(300); //设置下拉回弹的时间
        mPtrRootFrame.setDurationToCloseHeader(500); //设置刷新完成，头部回弹时间，注意和前一个进行区别
        mPtrRootFrame.setPullToRefresh(false); //设置下拉过程中执行刷新，我们一般设置为false
        mPtrRootFrame.setKeepHeaderWhenRefresh(true); //设置刷新的时候是否保持头部
        mPtrRootFrame.disableWhenHorizontalMove(true);
        mPtrRootFrame.addPtrUIHandler(mPtrCommonHeader);
        mPtrCommonHeader.setScrollThreshold(430);
        mPtrRootFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;

            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                //将显示banner的粘性消息清除
                //pullDownRefreshing();
                //刷新头
                //collapsibleHeader.requestData(true, HerderDataListener.TAG_REFRESH);
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 1000);

            }
        });
        mPtrRootFrame.setOffsetToGoDown(0);
        mPtrRootFrame.setEnabled(false);
        mPtrCommonHeader.bindData();
    }

}
