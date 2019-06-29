package com.lev1.datastructure.sort;

import java.util.Arrays;

public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, -2};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

        // 测试冒泡排序执行速度
        int testArr[] = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long bubbleStart = System.currentTimeMillis();
        bubbleSort(testArr);
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("冒泡排序总共用时:"+(bubbleEnd-bubbleStart)+"毫秒");
    }

    /**
     * 冒泡排序)
     *
     * @param arr 需要排序的数组
     */
    public static void bubbleSort(int[] arr) {
        // 冒泡排序，时间复杂度O(n^2)
        int temp = 0;
        boolean flag = false;// 优化冒泡排序，发现有一趟排序之后没有任何一个数据交换，则退出循环
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                break;
            } else {
                flag = false; //如果发生交换，重置flag值
            }
//            System.out.println("第"+(i+1)+"趟排序后的结果:"+ Arrays.toString(arr));
        }
    }
}
