package com.xiaoming.a005algorithm.basealgorithm.search;

//二分查找
public class BinarySearch {

    //要求：请实现有重复数字的有序数组的二分查找。
    //输出在数组中第一个大于等于查找值的位置，如果数组中不存在这样的数，则输出数组长度加一。
    //输出位置从1开始计算
    //https://www.nowcoder.com/practice/7bc4a1c7c371425d9faa9d1b511fe193?tpId=117&&tqId=35030

    /**
     * 二分查找
     * @param n int整型 数组长度
     * @param v int整型 查找值
     * @param a int整型一维数组 有序数组
     * @return int整型
     */
    //说明：里面暂未考虑该有序数组有重复数字，且只考虑相等的
    //非递归法
    public static int doBinarySearch1(int n, int v, int[] a) {
        int low = 0;
        int high = n-1;
        if(v < a[0] || v > a[high]) {
            return n + 1;
        }
        while(high >= low) {
            int middle =(low + high)/ 2;
            if(v > a[middle]) {
                low = middle + 1;
            } else if(v < a[middle]) {
                high = middle - 1;
            } else {
                return middle + 1;
            }
        }
        return n+1;
    }

    //说明：里面暂未考虑该有序数组有重复数字，且只考虑相等的
    // 非递归法
    public static int doBinarySearch2(int n, int v, int[] a) {
        int low = 0;
        int high = n-1;
        if(v < a[0] || v > a[high]) {
            return n + 1;
        }
        while(high >= low) {
            int middle =(low + high)/ 2;
            if(v > a[middle]) {
                low = middle + 1;
            } else if(v < a[middle]) {
                high = middle - 1;
            } else {
                //找到这个位置后看前面的数字是否有相同的，有的话改变这个位置数值
                //这部分未进行二分，是直接查找
                for(int i = middle;i>0;i--) {
                    if(a[i] != a[middle])
                        return i+2;
                }
            }
        }
        return n+1;
    }


    //说明：里面暂未考虑该有序数组有重复数字，考虑大于或相等的
    // 非递归法
    public static int doBinarySearch3(int n, int v, int[] a) {
        int low = 0;
        int high = n-1;
        if(v > a[high]) {
            return n + 1;
        }
        if(v<=a[0]) {
            return 1;
        }
        while(high > low) {
            int middle =(low + high)/ 2;
            if(v > a[middle]) {
                low = middle + 1;
            } else {
                //找到这个位置后看前面的数字是否有相同或者大于的，有的话改变这个位置数值
                //这部分未进行二分，是直接查找
                for(int i = middle;i>0;i--) {
                    if(a[i] < v)
                        return i+2;
                }
            }
        }
        return n+1;
    }


    //说明：里面暂未考虑该有序数组有重复数字，考虑大于或相等的
    // 非递归法
    public static int doBinarySearch4(int n, int v, int[] a) {
        int low = 0;
        int high = n-1;
        if(v > a[high]) {
            return n + 1;
        }
        if(v<=a[0]) {
            return 1;
        }
        while(high > low) {
            int middle =(low + high)/ 2;
            if(v > a[middle]) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }
        return low + 1;
    }

}
