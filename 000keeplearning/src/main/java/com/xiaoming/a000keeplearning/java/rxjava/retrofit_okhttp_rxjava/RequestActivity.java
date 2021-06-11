package com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.arch.core.util.Function;
import androidx.core.util.Consumer;
import androidx.lifecycle.Observer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaoming.a000keeplearning.R;
import com.xiaoming.a000keeplearning.java.rxjava.download.DownloadActivity;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.IReqeustNetwor;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.LoginReqeust;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.LoginResponse;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.MyRetrofit;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterRequest;
import com.xiaoming.a000keeplearning.java.rxjava.retrofit_okhttp_rxjava.retrofit_okhttp.RegisterResponse;

/**
 * TODO
 *  Retrofit + RxJava
 *  需求：
 *  1.请求服务器注册操作
 *  2.注册完成之后，更新注册UI
 *  3.马上去登录服务器操作
 *  4.登录完成之后，更新登录的UI
 *
 * wy.RxJava配合Retrofit。
 * RxJava + Retrofit （请求网络OkHttp  ---- Retorfit  --- Observable）
 *
 * 1.OkHttp 请求网络 （Retorfit）
 * 2.Retorfit 返回一个结果 （Retorfit） --- Observable
 * 3.最终的结果 是RxJava中的 被观察者 上游 Observable
 * 4.一行代码写完需求流程： 从上往下
 *    1.请求服务器，执行注册操作（耗时）切换异步线程
 *    2.更新注册后的所有 注册相关UI - main  切换主线程
 *    3.请求服务器，执行登录操作（耗时）切换异步线程
 *    4.更新登录后的所有 登录相关UI - main  切换主线程
 *
 * 5.看RxJava另外一种的执行流程
 *   初始点 开始点 订阅
 *   1.onSubscribe
 *   2.registerAction(new RegisterRequest())
 *   3..doOnNext 更新注册后的 所有UI
 *   4.flatMap执行登录的耗时操作
 *   5.订阅的观察者 下游 onNext 方法，更新所有登录后的UI
 *   6.progressDialog.dismiss()
 */

public class RequestActivity extends AppCompatActivity {
    private final String TAG = RequestActivity.class.getSimpleName();

    private TextView tv_register_ui;
    private TextView tv_login_ui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        tv_register_ui = findViewById(R.id.tv_login_ui);
        tv_login_ui = findViewById(R.id.tv_login_ui);
    }
    // TODO 方式一 分开写
    @SuppressLint("CheckResult")
    public void request(View view) {
        // 1.请求服务器注册操作
        // 2.注册完成之后，更新注册UI
        /*MyRetrofit.createRetrofit().create(IReqeustNetwor.class)
                .registerAction(new RegisterRequest())
                .compose(DownloadActivity.rxud())
                .subscribe(new Consumer<RegisterResponse>() {
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // 更新注册相关的所有UI
                        // .....
                    }
                });*/

        // 分开写

        // 3.马上去登录服务器操作
        // 4.登录完成之后，更新登录的UI
       /* MyRetrofit.createRetrofit().create(IReqeustNetwor.class)
                .loginAction(new LoginReqeust())
                .compose(DownloadActivity.rxud())
                .subscribe(new Consumer<LoginResponse>() {
                    @Override
                    public void accept(LoginResponse loginResponse) throws Exception {
                        // 更新登录相关的所有UI
                        // .....
                    }
                });*/
    }


    /**
     * TODO ================= 下面是一行代码完成整个需求
     */

    private ProgressDialog progressDialog;
    Disposable disposable;

    public void request2(View view) {

        /**
         * 一行代码 实现需求
         * 需求：
         *   还有弹出加载
         *  * 1.请求服务器注册操作
         *  * 2.注册完成之后，更新注册UI
         *  * 3.马上去登录服务器操作
         *  * 4.登录完成之后，更新登录的UI
         */
        MyRetrofit.createRetrofit().create(IReqeustNetwor.class)
                .registerAction(new RegisterRequest()) // todo 1.请求服务器注册操作   // todo 2
                .subscribeOn(Schedulers.io()) // 给上面 异步
                .observeOn(AndroidSchedulers.mainThread()) // 给下面分配主线程
                /*.doOnNext(new Consumer<RegisterResponse>() { // todo 3
                    @Override
                    public void accept(RegisterResponse registerResponse) throws Exception {
                        // todo 2.注册完成之后，更新注册UI
                    }
                })*/
                // todo 3.马上去登录服务器操作
                .observeOn(Schedulers.io()) // 给下面分配了异步线程
               /* .flatMap(new Function<RegisterResponse, ObservableSource<LoginResponse>>() { // todo 4
                    @Override
                    public ObservableSource<LoginResponse> apply(RegisterResponse registerResponse) throws Exception {
                        Observable<LoginResponse> loginResponseObservable = MyRetrofit.createRetrofit().create(IReqeustNetwor.class)
                                .loginAction(new LoginReqeust());
                        return loginResponseObservable;
                    }
                })*/
                .observeOn(AndroidSchedulers.mainThread()) ;// 给下面 执行主线程
               /* .subscribe(new Observer<LoginResponse>() {

                    // 一定是主线程，为什么，因为 subscribe 马上调用onSubscribe
                    @Override
                    public void onSubscribe(Disposable d) {
                        // TODO 1
                        progressDialog = new ProgressDialog(RequestActivity.this);
                        progressDialog.show();

                        // UI 操作

                        disposable = d;
                    }

                    @Override
                    public void onNext(LoginResponse loginResponse) { // todo 5
                        // TODO 4.登录完成之后，更新登录的UI
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    // todo 6
                    @Override
                    public void onComplete() {
                        // 杀青了
                        if (progressDialog != null) {
                            progressDialog.dismiss();
                        }
                    }
                });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 必须这样写，最起码的标准
        if (disposable != null)
            if (!disposable.isDisposed())
                disposable.dispose();
    }
}
