package com.widget.listview.listviewdemo.demo3;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.widget.R;

import java.util.List;

//自定义适配器
public class WeatherAdapter3 extends ArrayAdapter<Weather3> {
    private int resourceId;

    //将上下文、ListView子项布局的id、列表数据 传递进来
    public WeatherAdapter3(@NonNull Context context, int textViewResourceId, List<Weather3> obj) {
        super(context, textViewResourceId,obj);
        resourceId = textViewResourceId;
    }

    //重写 getView() 方法。这个方法在每个子项被滚到屏幕内的时候会被调用。
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //获取当前项的Weather实例
        Weather3 weather = getItem(position);

        //使用 LayoutInflater 来为这个子项加载我们传入的布局
        //LayoutInflater的inflate()方法接收3个参数：需要实例化布局资源的id、ViewGroup类型视图组对象、false
        //false表示只让父布局中声明的layout属性生效，但不会为这个view添加父布局
        View view;
        ViewHolder viewHolder;
        //如果convertView为空，则使用LayoutInflater()去加载布局
        if(convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent,false);
            viewHolder = new ViewHolder();
            //通过ViewHolder获取控件实例
            viewHolder.weatherImage = view.findViewById(R.id.image_weather);
            viewHolder.weatherName = view.findViewById(R.id.tv_weather_name);
            //将ViewHolder存储在view中
            view.setTag(viewHolder);
        } else {
            //否则，重用convertView
            view = convertView;
            //重新获取ViewHolder（利用View的getTag()方法，把ViewHolder重新取出)
            viewHolder = (ViewHolder) view.getTag();
        }
       /* //获取控件实例
        ImageView imageView = view.findViewById(R.id.image_weather);
        TextView name = view.findViewById(R.id.tv_weather_name);

        //设置图片和文字
        imageView.setImageResource(weather.getImageId());
        name.setText(weather.getName());*/

        //ViewHolder优化，对控件实例进行了缓存
        viewHolder.weatherImage.setImageResource(weather.getImageId());
        viewHolder.weatherName.setText(weather.getName());
        return view;
    }

    //定义ViewHolder内部类，用于对控件实例进行缓存
    static class ViewHolder{
        ImageView weatherImage;
        TextView weatherName;
    }
}
