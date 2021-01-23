package com.widget.listview.listviewdemo.demo3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.widget.R;

import java.util.ArrayList;
import java.util.List;


//ListView 优化
public class ListView3Activity extends AppCompatActivity {

    private List<Weather3> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view3);
        ListView listView = findViewById(R.id.lv3);
        //初始化天气数据
        initWeather();

        //适配器，将上下文、ListView子项布局的id、列表数据 传递进来
        WeatherAdapter3 weatherAdapter = new WeatherAdapter3(this, R.layout.list_view3_item, weatherList);
        listView.setAdapter(weatherAdapter);
    }

    private void initWeather() {
        Weather3 weather1 = new Weather3("sunny", R.drawable.ic_launcher_background);
        Weather3 weather2 = new Weather3("cloudy", R.drawable.ic_launcher_background);
        Weather3 weather3 = new Weather3("few cloudy", R.drawable.ic_launcher_background);
        Weather3 weather4 = new Weather3("windy", R.drawable.ic_launcher_background);
        Weather3 weather5 = new Weather3("calm", R.drawable.ic_launcher_background);
        Weather3 weather6 = new Weather3("overcast", R.drawable.ic_launcher_background);
        Weather3 weather7 = new Weather3("light breeze", R.drawable.ic_launcher_background);
        Weather3 weather8 = new Weather3("fresh breeze", R.drawable.ic_launcher_background);
        Weather3 weather9 = new Weather3("strong breeze", R.drawable.ic_launcher_background);
        Weather3 weather10 = new Weather3("high wind", R.drawable.ic_launcher_background);

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
