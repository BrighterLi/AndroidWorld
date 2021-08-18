package com.xiaoming.mvx.mvp.presenter;


import com.xiaoming.mvx.mvp.modle.Data;
import com.xiaoming.mvx.mvp.view.DemoFragment;

public class DemoPresenter implements DemoContract.Presenter{
    DemoFragment mView;

    public DemoPresenter(DemoFragment view) {
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void demoDosomething() {
        mView.setName(Data.getInstance().getName());
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
