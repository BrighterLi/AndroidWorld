package com.widget.listview.pagingrequest;

import java.util.ArrayList;
import java.util.List;

public class DataService {

    public List<String> getData(int startPosition, int offset) {
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 20; i++) {
            data.add("分页加载的数据 " + i);
        }
        return data;
    }
}
