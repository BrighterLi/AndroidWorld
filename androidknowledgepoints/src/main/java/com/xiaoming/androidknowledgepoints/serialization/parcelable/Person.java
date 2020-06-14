package com.xiaoming.androidknowledgepoints.serialization.parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Person implements Parcelable {
    private static final String TAG = "Person";
    private String userName;
    private String passWord;

    public Person() {
        Log.d(TAG, "bright#Person()");
    }

    public Person(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
        Log.d(TAG, "bright#Person() called with: " + "userName = [" + userName + "], passWord = [" + passWord + "]");
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * public static final一个都不能少，内部对象CREATOR的名称也不能改变，必须全部大写。
     * 重写接口中的两个方法：
     * createFromParcel(Parcel in) 实现从Parcel容器中读取传递数据值,封装成Parcelable对象返回逻辑层，
     * newArray(int size) 创建一个类型为T，长度为size的数组，供外部类反序列化本类数组使用。
     */
    public static final Creator<Person> CREATOR = new Creator<Person>() {
        /**
         * 从序列化后的对象中创建原始对象
         */
        @Override
        public Person createFromParcel(Parcel in) {
            Person person = new Person();
            person.userName = in.readString();
            person.passWord = in.readString();
            return person;
        }

        /**
         * 创建指定长度的原始对象数组
         */
        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 将当前对象写入序列化结构中
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(passWord);
    }
}
