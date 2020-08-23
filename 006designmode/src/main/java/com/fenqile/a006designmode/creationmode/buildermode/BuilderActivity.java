package com.fenqile.a006designmode.creationmode.buildermode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fenqile.a006designmode.R;

public class BuilderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_builder);

        createPerson();
    }

    //调用
    private Person createPerson() {
        Person person = new Person.Builder().setName("bright")
                .setAge("20")
                .setTitle("Manager")
                .build();
        return person;
    }
}
