package com.lev1.datastructure.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

        // 测试选择排序执行速度
        int testArr[] = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long bubbleStart = System.currentTimeMillis();
        selectSort(testArr);
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("选择排序总共用时:"+(bubbleEnd-bubbleStart)+"毫秒");
    }

    public static void selectSort(int[] arr) {
        //选择排序，时间复杂度O(n^2)
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] =arr[i];
                arr[i] = min;
            }
//            System.out.println("第"+(i+1)+"趟排序后结果为："+ Arrays.toString(arr));
        }
    }
}
