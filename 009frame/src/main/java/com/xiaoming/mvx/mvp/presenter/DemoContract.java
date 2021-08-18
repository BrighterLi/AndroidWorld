package com.xiaoming.mvx.mvp.presenter;

//接口管理类
public interface DemoContract {
    interface Presenter extends BasePresenter {
        void demoDosomething();
    }
    interface View extends BaseView<DemoContract.Presenter> {
        void setName(String name);
    }
}
