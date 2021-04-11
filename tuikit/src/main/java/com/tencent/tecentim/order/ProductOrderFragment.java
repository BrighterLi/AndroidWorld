package com.tencent.tecentim.order;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tencent.tecentim.R;
import com.tencent.tecentim.messagehelper.custommessage.CustomCardMessage;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProductOrderFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_product_order, container, false);
        initView();
        return mainView;
    }

    private void initView() {
        mRecyclerView = mainView.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        ArrayList<CustomCardMessage> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
           CustomCardMessage message = new CustomCardMessage();
           message.salePrice = "500";
           message.title = "1111111111111111111";
           message.monthPay = "30.12";
           message.imgUrl = "url";
           data.add(message);
        }
        //context;data
        mAdapter = new RecyclerViewAdapter(getActivity(), data);
        //view-layoutmanager
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }
}
