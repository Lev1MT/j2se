package com.lev1.datastructure.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int arr[] = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0, -2};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        shellSort1(arr);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

        // 测试普通shell排序执行速度
//        int testArr[] = new int[80000];
//        for (int i = 0; i < testArr.length; i++) {
//            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
//        }
//        long bubbleStart = System.currentTimeMillis();
//        shellSort1(testArr);
//        long bubbleEnd = System.currentTimeMillis();
//        System.out.println("shellSort1排序总共用时:" + (bubbleEnd - bubbleStart) + "毫秒");

        // 测试使用插入排序的shell排序执行速度
        int testArr2[] = new int[80000];
        for (int i = 0; i < testArr2.length; i++) {
            testArr2[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long bubbleStart1 = System.currentTimeMillis();
        shellSort2(testArr2);
        long bubbleEnd1 = System.currentTimeMillis();
        System.out.println("shellSort2排序总共用时:" + (bubbleEnd1 - bubbleStart1) + "毫秒");
    }


    /**
     * 交换型shell排序，效率不高，因为是用交换法对分组后的数据进行排序
     * shell排序的平均时间复杂度为nlogn，最坏时间复杂度为n^s(1<s<2)
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int temp = 0;
        int count = 0;
        // 根据分析进行循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第"+(++count)+"轮排序结果为："+ Arrays.toString(arr));
        }

        //分析过程
        // 第一轮排序
        /*for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0 ; j-=5) {
                if (arr[j] > arr[j+5]){
                    int temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }
            }
        }
        System.out.println("第一轮排序结果为："+ Arrays.toString(arr));

        // 第二轮排序
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 2; j >= 0 ; j-=2) {
                if (arr[j] > arr[j+2]){
                    int temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }
            }
        }
        System.out.println("第二轮排序结果为："+ Arrays.toString(arr));

        // 第三轮排序
        for (int i = 1; i < arr.length; i++) {
            for (int j = i-1; j >= 0 ; j-=1) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        System.out.println("第三轮排序结果为："+ Arrays.toString(arr));*/
    }

    /**
     * 优化版希尔排序，使用插入法对分组的数据进行排序
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    // 保证insertIdx不会越界
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }

    }
}
