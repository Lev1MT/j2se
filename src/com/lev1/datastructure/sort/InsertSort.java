package com.lev1.datastructure.sort;

import java.util.Arrays;

public class InsertSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

        // 测试插入排序执行速度
        int testArr[] = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long bubbleStart = System.currentTimeMillis();
        insertSort(testArr);
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("插入排序总共用时:" + (bubbleEnd - bubbleStart) + "毫秒");
    }

    public static void insertSort(int[] arr) {
        int count = 0;
        int insertVal = 0;
        int insertIdx = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIdx = i - 1;

            // 保证insertIdx不会越界
            while (insertIdx >= 0 && insertVal < arr[insertIdx]) {
                arr[insertIdx + 1] = arr[insertIdx];
                insertIdx--;
            }
            if (insertIdx + 1 != i) {
                arr[insertIdx + 1] = insertVal;
            }
//            System.out.println("第"+i+"趟排序后的结果:"+ Arrays.toString(arr));
        }
    }
}
