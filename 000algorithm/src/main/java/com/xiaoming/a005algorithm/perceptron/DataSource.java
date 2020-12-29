package com.xiaoming.a005algorithm.perceptron;

public class DataSource {
    //生成训练数据
    public static int[][] genStudyData() {
        //取 -100 ~ 100 之间的整数，大于0的设为模式 y = 1，反之为 y = -1
        int[][] data = new int[201][2];

        for(int i = -100, j = 0; i <= 100; i++, j++) {
            data[j][0] = i;
            data[j][1] = i >= 0 ? 1 : -1;
        }

        return data;
    }

    //生成训练数据
    public static int[][] genStudyData2() {
        //取 1~250 之间的整数，大于125的设为模式 y = 1，反之为 y = -1
        int[][] data = new int[250][2];

        for (int i = 1, j = 0; i <= 250; i++, j++) {
            data[j][0] = i;
            data[j][i] = i > 125 ? 1 : -1;
        }
        return data;
    }
}
