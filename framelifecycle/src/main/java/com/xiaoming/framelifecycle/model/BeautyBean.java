package com.xiaoming.framelifecycle.model;

import java.io.Serializable;
import java.util.List;

public class BeautyBean implements Serializable {
    public boolean error;
    public List<ResultBean> results;

    public static class ResultBean {
        public String id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public String source;
    }
}
