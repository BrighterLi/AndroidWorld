package com.xiaoming.widgetanimation.lottie;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.xiaoming.widgetanimation.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LottieNetworkAnimActivity extends AppCompatActivity {
    private LottieAnimationView mLottieNetworkView;
    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_network_anim);

        mLottieNetworkView = findViewById(R.id.lottie_network_view);
        lordUrl("http://www.chenailing.cn/EmptyState.json");
    }

    private void lordUrl(String url) {
        Request request;
        try {
            request = new Request.Builder()
                    .url(url)
                    .build();
        }catch (IllegalArgumentException e) {
            return;
        }

        if(mOkHttpClient == null) {
            mOkHttpClient = new OkHttpClient();
        }
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()) {

                }

                try {
                    //JSONObject jsonObject = new JSONObject(response.body().string());
                    LottieComposition.Factory.fromJsonString(response.body().string(), new OnCompositionLoadedListener() {

                        @Override
                        public void onCompositionLoaded(@Nullable LottieComposition composition) {
                            setComposition(composition);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setComposition(LottieComposition composition) {
        mLottieNetworkView.setProgress(0);
        mLottieNetworkView.loop(true);
        mLottieNetworkView.setComposition(composition);
        mLottieNetworkView.playAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mLottieNetworkView.cancelAnimation();
    }
}
