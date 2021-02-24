package com.xiaoming.a005algorithm.basealgorithm.sort;

//给定一个数组，请你编写一个函数，返回该数组排序后的形式。
//  https://www.nowcoder.com/practice/2baf799ea0594abd974d37139de27896?
//  示例1 输入[5,2,3,1,4] 返回值 [1,2,3,4,5]
//  分析：使用冒泡升序排序报运行超时，应该是冒泡排序不符合时间要求；应该使用更高效的排序方法

public class BubbleSort {

    public int[] MySort (int[] arr) {
        // write code here
        if(arr == null) {
            return arr;
        }
        int size = arr.length;
        if(size == 0) {
            return arr;
        }

        //冒泡排序，升序
        for(int i=size-1; i>0; i--) {
            for(int j=0; j<i; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

}
