package com.xiaoming.mvx.mvc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.xiaoming.MainActivity;
import com.xiaoming.framearouter.R;
import com.xiaoming.mvx.mvc.Model.SearchNumModelImpl;

//https://www.jianshu.com/p/3e7573126514
public class MvcActivity extends AppCompatActivity implements SearchNumListener, View.OnClickListener{
    private EditText mEtName;
    private Button mBtSearch;
    private TextView mTvNum;
    private SearchNumModelImpl mSearchNumModelImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);

        initView();
        mSearchNumModelImpl = new SearchNumModelImpl(MvcActivity.this);
        ActivityCompat.requestPermissions(MvcActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, 1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search: //View交互：User点击Button
                // Control:响应点击，发送name给Model
                mSearchNumModelImpl.getNum(mEtName.getText().toString(), this);
                break;
        }
    }

    @Override
    public void onSuccess(String num) {
        //Model:通过名字查询号码，返回给View显示
        mTvNum.setText(num);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "no number", Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mEtName = findViewById(R.id.et_name);
        mBtSearch =findViewById(R.id.bt_search);
        mTvNum = findViewById(R.id.tv_number);

        mBtSearch.setOnClickListener(this);
    }
}
