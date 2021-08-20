package com.xiaoming.databinding.demo2;

import com.xiaoming.framearouter.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Student extends BaseObservable {
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }
}
