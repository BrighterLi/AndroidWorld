package com.xiaoming.widgetlistview.listview3;

//实体类
public class Weather3 {
    private String name;
    private int imageId;

    public Weather3(String name, int imageId) {
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
