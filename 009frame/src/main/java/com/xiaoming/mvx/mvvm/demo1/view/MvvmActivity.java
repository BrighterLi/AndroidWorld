package com.xiaoming.mvx.mvvm.demo1.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.xiaoming.framearouter.R;
import com.xiaoming.framearouter.databinding.ActivityMvvmBinding;
import com.xiaoming.mvx.mvvm.demo1.model.Callback;
import com.xiaoming.mvx.mvvm.demo1.model.UserInfo;
import com.xiaoming.mvx.mvvm.demo1.viewmodel.SampleViewModel;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


//Android MVVM实战Demo完全解析: https://blog.csdn.net/zhouxu88/article/details/78284198
//一个简单的mvvm架构例子: https://www.pianshen.com/article/1397295584/
//Android MVVM实战Demo完全解析: https://blog.csdn.net/zhouxu88/article/details/78284198
public class MvvmActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView tvAge, tvName;
    private SampleViewModel mSampleViewModel;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mvvm);
        ActivityMvvmBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm);//这是引入DataBinding后自动生成的类
        mSampleViewModel = new SampleViewModel(binding);
        mSampleViewModel.mViewDataBinding.setMvvmActivity(this);//需要与xml文件做绑定
        button = findViewById(R.id.btn);
        editText = findViewById(R.id.edt);
        tvName = findViewById(R.id.tv_name);
        tvAge = findViewById(R.id.tv_age);
        imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    private void setDataToView(String input) {
        mSampleViewModel.getUserInfo(input, new Callback<UserInfo>() {
            @Override
            public void onCallBack(UserInfo userInfo) {
                mSampleViewModel.mViewDataBinding.setUserInfo(userInfo);
            }
        });

    }


    public void set(String input) {
        System.out.println("点击设置" + input);
        imm.hideSoftInputFromWindow(getWindow().peekDecorView().getWindowToken(), 0);
        setDataToView(input);
    }

}
