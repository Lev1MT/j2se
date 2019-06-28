package com.lev1.datastructure.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {5, 3, 9, 2, 4};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length - 1);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

        // 测试使用插入排序的shell排序执行速度
        int testArr[] = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long bubbleStart = System.currentTimeMillis();
        quickSort(testArr, 0, testArr.length - 1);
        long bubbleEnd = System.currentTimeMillis();
        System.out.println("快速排序排序总共用时:" + (bubbleEnd - bubbleStart) + "毫秒");
    }

    public static void quickSort(int[] arr, int left, int right) {

        int l = left;
        int r = right;
        // 中轴值
        int pivot = arr[(right + left) / 2];
        int temp = 0;
        while (l < r) {
            //从数组左边开始一直找，直到找到一个大于等于pivot的值
            while (arr[l] < pivot) {
                l += 1;
            }
            //从数组右边一直找，直到找到一个小于等于pivot的值
            while (arr[r] > pivot) {
                r -= 1;
            }

            // 如果l>=r，说明左边的数全部小于右边的数，退出循环
            if (l >= r) {
                break;
            }

            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完之后，发现这个arr[l] == pivot，r-- 前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完之后，发现这个arr[r] == pivot，l++ 后移
            if (arr[r] == pivot) {
                l += 1;
            }
//            System.out.println(Arrays.toString(arr));
        }

        // 如果l==r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }

        // 如果中轴值左边有数据，递归
        if (right > l) {
            quickSort(arr, l, right);
        }
        // 如果中轴值右边有数据，递归
        if (left < r) {
            quickSort(arr, left, r);
        }
    }
}
