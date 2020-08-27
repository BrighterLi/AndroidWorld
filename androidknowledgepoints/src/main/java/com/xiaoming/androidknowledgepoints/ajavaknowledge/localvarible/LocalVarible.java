package com.xiaoming.androidknowledgepoints.ajavaknowledge.localvarible;

public class LocalVarible {
    String str;

    public void main() {
        str = "abcd";
        changeValue(str);
    }

    private  void changeValue(String str) {
        str = "efgh";
    }
}
