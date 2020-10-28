package com.xiaoming.a008project.consumption.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xiaoming.a008project.R;
import com.xiaoming.a008project.consumption.view.RecyclerViewAdapter;

import java.util.ArrayList;

public class OneFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView = inflater.inflate(R.layout.fragment_one, container, false);
        initView();
        return mainView;
    }

    private void initView() {
        mRecyclerView = mainView.findViewById(R.id.recyclerview);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            data.add("列表项" + i);
        }
        //context;data
        mAdapter = new RecyclerViewAdapter(getActivity(), data);
        //view-layoutmanager
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }
}
