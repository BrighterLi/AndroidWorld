package com.xiaoming.widgetlistview.listview2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.xiaoming.widgetlistview.R;

import java.util.List;

//自定义适配器
public class WeatherAdapter extends ArrayAdapter<Weather> {
    private int resourceId;

    //将上下文、ListView子项布局的id、数据 传递进来
    public WeatherAdapter(@NonNull Context context, int textViewResourceId, List<Weather> obj) {
        super(context, textViewResourceId,obj);
        resourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取当前项的Weather实例
        Weather weather = getItem(position);
        //LayoutInflater的inflate()方法接收3个参数：需要实例化布局资源的id、ViewGroup类型视图组对象、false
        //false表示只让父布局中声明的layout属性生效，但不会为这个view添加父布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
        //获取实例
        ImageView imageView = view.findViewById(R.id.image_weather);
        TextView name = view.findViewById(R.id.tv_weather_name);
        //设置图片和文字
        imageView.setImageResource(weather.getImageId());
        name.setText(weather.getName());
        //return super.getView(position, convertView, parent);
        return view;
    }
}
