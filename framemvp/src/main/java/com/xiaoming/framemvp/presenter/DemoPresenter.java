package com.xiaoming.framemvp.presenter;

import com.xiaoming.framemvp.DemoFragment;
import com.xiaoming.framemvp.modle.Data;

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
