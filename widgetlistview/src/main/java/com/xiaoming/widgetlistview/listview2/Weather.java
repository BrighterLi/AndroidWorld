package com.xiaoming.widgetlistview.listview2;

//实体类
public class Weather {
    private String name;
    private int imageId;

    public Weather(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
