package com.xiaoming.a005algorithm.perceptron;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.a005algorithm.R;

//https://blog.csdn.net/wang19950207/article/details/78693107
public class PerceptronActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perceeptron);

        startPerceptron();
    }

    private void startPerceptron() {
        //学习率和训练次数可以根据情况人为调整
        Perceptron perceptron = new Perceptron(0.4f, 500);
        perceptron.fit(DataSource.genStudyData());
        perceptron.printCorrectRate();

        System.out.println("bright#perceptron.predict(-1)：" + perceptron.predict(-1) + "\n");
        System.out.println("bright#perceptron.preditc(126)：" + perceptron.predict(126) + "\n");
    }
}
