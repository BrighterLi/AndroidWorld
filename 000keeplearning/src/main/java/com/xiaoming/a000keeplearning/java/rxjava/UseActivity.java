package com.xiaoming.a000keeplearning.java.rxjava;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;
import com.xiaoming.a000keeplearning.R;
import com.xiaoming.a000keeplearning.java.rxjava.api.WangAndroidApi;
import com.xiaoming.a000keeplearning.java.rxjava.bean.ProjectBean;
import com.xiaoming.a000keeplearning.java.rxjava.bean.ProjectItem;
import com.xiaoming.a000keeplearning.java.rxjava.download.DownloadActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

public class UseActivity extends AppCompatActivity {
    private final static String TAG = UseActivity.class.getSimpleName();

    private WangAndroidApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use);
        api = HttpUtil.getOnlineCookieRetrofit().create(WangAndroidApi.class);

        // 功能防抖 + 网络嵌套
        // antiShakeActon();
        antiShakeActonUpdate();
    }

    /**
     * TODO Retrofit+RxJava 查询 项目分类  (总数据查询)
     *
     * @param view
     */
    public void getProjectAction(View view) {
        // 获取网络API
        api.getProject()
                .subscribeOn(Schedulers.io()) // 上面 异步
                .observeOn(AndroidSchedulers.mainThread()) // 下面 主线程
                .subscribe(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Exception {
                        Log.d(TAG, "accept: " + projectBean); // UI 可以做事情
                    }
                });
    }

    /**
     * TODO Retrofit+RxJava 查询  项目分类的49 去 获取项目列表数据  (Item)
     *
     * @param view
     */
    public void getProjectListAction(View view) {
        // 注意：这里的 294 是项目分类 所查询出来的数据
        // 上面的项目分类会查询出："id": 294,"id": 402,"id": 367,"id": 323,"id": 314, ...

        // id 写死的
        api.getProjectItem(1, 294)
                // .....
                .subscribeOn(Schedulers.io()) // 上面 异步
                .observeOn(AndroidSchedulers.mainThread()) // 下面 主线程
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        Log.d(TAG, "getProjectListAction: " + projectItem);
                    }
                });

    }

    /**
     * RxJava
     * RxJs
     * Rxxxxx
     * RxBinding  防抖
     *
     * TODO 功能防抖 + 网络嵌套（这种是负面教程，嵌套的太厉害了）
     * 2层嵌套
     * 6层
     */
    @SuppressLint("CheckResult")
    private void antiShakeActon() {
        // 注意：（项目分类）查询的id，通过此id再去查询(项目列表数据)

        // 对那个控件防抖动？
        Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

        RxView.clicks(bt_anti_shake).throttleFirst(2000, TimeUnit.MILLISECONDS);
        /*RxView.clicks(bt_anti_shake).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                api.getProject() // 查询主数据
                        .compose(DownloadActivity.rxud())
                        .subscribe(new Consumer<ProjectBean>() {
                            @Override
                            public void accept(ProjectBean projectBean) throws Exception {
                                for (ProjectBean.DataBean dataBean : projectBean.getData()) { // 10
                                    // 查询item数据
                                    api.getProjectItem(1, dataBean.getId())
                                            .compose(DownloadActivity.rxud())
                                            .subscribe(new Consumer<ProjectItem>() {
                                                @Override
                                                public void accept(ProjectItem projectItem) throws Exception {
                                                    Log.d(TAG, "getProjectListAction: " + projectItem);
                                                }
                                            } );
                                }
                            }
                        });
            }
        });// 2秒钟之内 响应你一次*/
    }

    /**
     * TODO 功能防抖 + 网络嵌套 (解决嵌套的问题) flatMap
     */
    @SuppressLint("CheckResult")
    private void antiShakeActonUpdate() {
        // 注意：项目分类查询的id，通过此id再去查询(项目列表数据)

        // 对那个控件防抖动？
        Button bt_anti_shake = findViewById(R.id.bt_anti_shake);

        RxView.clicks(bt_anti_shake)
                .throttleFirst(2000, TimeUnit.MILLISECONDS) // 2秒钟之内 响应你一次

                // 我只给下面 切换 异步
                .observeOn(Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return api.getProject(); // 主数据
                    }
                })

                // 第一步不能map 因为  api Observbale<Bean>  TODO 小作业 同学们自己尝试
                /*.map(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return api.getProject(); // 主数据;
                    }
                })*/

                .flatMap(new Function<ProjectBean, ObservableSource<ProjectBean.DataBean>>() {
                    @Override
                    public ObservableSource<ProjectBean.DataBean> apply(ProjectBean projectBean) throws Exception {
                        return Observable.fromIterable(projectBean.getData()); // 我自己搞一个发射器 发多次 10
                    }
                })
                .flatMap(new Function<ProjectBean.DataBean, ObservableSource<ProjectItem>>() {
                    @Override
                    public ObservableSource<ProjectItem> apply(ProjectBean.DataBean dataBean) throws Exception {
                        return api.getProjectItem(1, dataBean.getId());
                    }
                })

                .observeOn(AndroidSchedulers.mainThread()) // 给下面切换 主线程
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        // 如果我要更新UI  会报错2  不会报错1
                        Log.d(TAG, "accept: " + projectItem);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

