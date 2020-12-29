package com.xiaoming.a005algorithm.basealgorithm.search;

//二分查找
public class BinarySearch {

    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    public static int doBinarySearch(int n, int v, int[] a) {
        int index = n+1;
        int middle = n / 2;
        int firstIndex = 0;
        int lastIndex = n;
        while(lastIndex > firstIndex) {
            if(v == a[middle]) {
                index = middle + 1;
                return index;
            } else if(v < a[middle]) {
                lastIndex = middle;
                middle = (firstIndex + middle) / 2;
            } else {
                firstIndex = middle;
                middle = (middle + lastIndex) / 2;
            }
        }
        return index;
    }
}
