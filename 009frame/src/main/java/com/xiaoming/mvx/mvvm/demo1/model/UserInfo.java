package com.xiaoming.mvx.mvvm.demo1.model;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

//JavaBean
public class UserInfo extends BaseObservable {
    private int age;
    private String name;

    public UserInfo() {
    }

    public UserInfo(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Bindable//注解用于关联布局文件的相关控件
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Bindable//注解用于关联布局文件的相关控件
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

}
