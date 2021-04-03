package com.xiaoming.androidpoints.serialization.parcelable;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.xiaoming.androidpoints.R;

//反序列化
public class UnSerializationActivity extends AppCompatActivity {
    private TextView mTvShowParcelable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_serialization);

        mTvShowParcelable = findViewById(R.id.tv_show_parcelable);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //Parcelable反序列化
        Person person = extras.getParcelable("person");
        String userName = person.getUserName();
        String passWord = person.getPassWord();
        mTvShowParcelable.setText("反序列化得到userName：" + userName);
        Log.d("UnSerializationActivity", "bright#userName：" + userName + "\n" + "passWord" + passWord);
    }
}
