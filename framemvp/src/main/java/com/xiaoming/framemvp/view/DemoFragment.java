package com.xiaoming.framemvp.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.xiaoming.framemvp.R;
import com.xiaoming.framemvp.presenter.DemoContract;

//把view的操作都写在这里，把view的操作彻底独立出来
public class DemoFragment extends Fragment implements View.OnClickListener, DemoContract.View {
    DemoContract.Presenter mPresenter;
    Button mBt;
    TextView mTv;

    public static DemoFragment newInstance() {
        DemoFragment fragment = new DemoFragment();
        return fragment;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt:
                mPresenter.demoDosomething();
                break;
            default:
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_demo, container, false);
        mTv = root.findViewById(R.id.tv);
        mBt = root.findViewById(R.id.bt);
        mBt.setOnClickListener(this);
        return root;
    }

    @Override
    public void setPresenter(DemoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void setName(String name) {
        mTv.setText(name);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }
}
