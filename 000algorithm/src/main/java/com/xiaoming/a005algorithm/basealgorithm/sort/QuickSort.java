package com.xiaoming.a005algorithm.basealgorithm.sort;

public class QuickSort {

    //快排
    public void quickSort(int low, int high, int[] data) {
        if (low < high) {
            int baseLocation = getBaseLocation(low, high, data);
            //第二步，采用递归的方式处理基准左右两堆
            quickSort(low, baseLocation - 1, data);//对低基准位段进行递归排序
            quickSort(baseLocation + 1, high, data);//对高基准位段进行递归排序
        }
    }

    //第一步，查找一个基准位
    public int getBaseLocation(int low, int high, int[] data) {
        int initBaseData = data[low]; //设定一个初始基准
        while (low < high) {
            while (low < high && data[high] >= initBaseData) {//从右向左查找
                high--;
            }
            if (low < high) {
                data[low] = data[high]; //小于基准的移到低端
            }

            while (low < high && data[low] <= initBaseData) { //从左向右查找
                low++;
            }
            if (low < high) {
                data[high] = data[low]; //大于基准的移到高端
            }
        }
        data[low] = initBaseData; //基准位置不再变化时,基准位为low
        return low;
    }

}
