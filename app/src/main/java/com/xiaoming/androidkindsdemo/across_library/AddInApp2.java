package com.xiaoming.androidkindsdemo.across_library;

import com.xiaoming.acrossslibrary.Add;

public class AddInApp2 {

    public void add() {
       /* AddApi addApi = new AddApi();
        AddApi.add();*/

        Add add = new Add();
        add.add(1, 3);
    }
}
