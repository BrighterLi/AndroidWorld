package com.widget.listview.listviewdemo.demo2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;


//ListView 自定义Item
public class ListView2Activity extends AppCompatActivity {

    private List<Weather> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        ListView listView = findViewById(R.id.lv2);
        //初始化天气数据
        initWeather();

        //适配器，将上下文、ListView子项布局的id、列表数据 传递进来
        WeatherAdapter weatherAdapter = new WeatherAdapter(this, R.layout.list_view2_item, weatherList);
        listView.setAdapter(weatherAdapter);
    }

    private void initWeather() {
        Weather weather1 = new Weather("sunny", R.drawable.ic_launcher_background);
        Weather weather2 = new Weather("cloudy", R.drawable.ic_launcher_background);
        Weather weather3 = new Weather("few cloudy", R.drawable.ic_launcher_background);
        Weather weather4 = new Weather("windy", R.drawable.ic_launcher_background);
        Weather weather5 = new Weather("calm", R.drawable.ic_launcher_background);
        Weather weather6 = new Weather("overcast", R.drawable.ic_launcher_background);
        Weather weather7 = new Weather("light breeze", R.drawable.ic_launcher_background);
        Weather weather8 = new Weather("fresh breeze", R.drawable.ic_launcher_background);
        Weather weather9 = new Weather("strong breeze", R.drawable.ic_launcher_background);
        Weather weather10 = new Weather("high wind", R.drawable.ic_launcher_background);

        weatherList.add(weather1);
        weatherList.add(weather2);
        weatherList.add(weather3);
        weatherList.add(weather4);
        weatherList.add(weather5);
        weatherList.add(weather6);
        weatherList.add(weather7);
        weatherList.add(weather8);
        weatherList.add(weather9);
        weatherList.add(weather10);
    }
}