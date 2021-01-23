package com.widget.listview.listviewdemo.demo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.widget.R;


//ListView 基本用法
public class ListView1Activity extends AppCompatActivity {
    private ListView listView;
    private String[] arrayData = {"Sunny","Cloudy","Few Clouds","Partly Cloudy","Overcast","Windy","Calm","Light Breeze",
            "Moderate","Fresh Breeze","Strong Breeze","High Wind","Gale","Strong Gale","Storm","Violent Storm","Hurricane",
            "Tornado","Tropical Storm","Shower Rain","Heavy Shower Rain","Thundershower","Heavy Thunderstorm","Thundershower with hail",
            "Light Rain","Moderate Rain","Heavy Rain","Extreme Rain","Drizzle Rain","Storm","Heavy Storm","Severe Storm","Freezing Rain",
            "Light to moderate rain","Moderate to heavy rain","Heavy rain to storm","Storm to heavy storm","Heavy to severe storm",
            "Rain","Light Snow","Moderate Snow","Heavy Snow","Snowstorm","Sleet","Rain And Snow","Shower Snow","Snow Flurry",
            "Light to moderate snow","Moderate to heavy snow","Heavy snow to snowstorm","Snow","Mist","Foggy","Haze","Sand","Dust",
            "Duststorm","Sandstorm","Dense fog","Strong fog","Moderate haze","Heavy haze","Severe haze","Heavy fog","Extra heavy fog",
            "Hot","Cold","Unknown"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view1);

        listView = findViewById(R.id.lv1);
        //通过泛型来指定要适配的数据类型，然后在构造函数中把适配的数据传入。
        //android.R.layout.simple_list_item_1是ListView内置的一个子项布局，里面只有一个TextView，可显示一段文本
        //arrayData表示要适配的数据
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayData);
        //将构建好的适配器对象传进去
        listView.setAdapter(arrayAdapter);
    }
}
