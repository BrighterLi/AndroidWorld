package com.widget.scroll.doublescroll.scrollviewrecyclerview;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.widget.R;

import java.util.ArrayList;

public class ThreeFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainView=inflater.inflate(R.layout.fragment_three,container,false);
        initView();
        return mainView;
    }

    private void initView(){
        mRecyclerView=mainView.findViewById(R.id.recyclerview);
        mLayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ArrayList<String> data=new ArrayList<>();
        for(int i=0;i<20;i++){
            data.add("列表项"+i);
        }
        mAdapter=new RecyclerViewAdapter(getActivity(),data);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
