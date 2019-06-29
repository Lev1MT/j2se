package com.lev1.datastructure.sort;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        // 测试使用基数排序的执行速度
        int testArr[] = new int[80000];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long radixStart = System.currentTimeMillis();
        radixSort(testArr);
        long redixEnd = System.currentTimeMillis();
        System.out.println("基数排序总共用时:" + (redixEnd - radixStart) + "毫秒");
    }

    // 由于基数排序是典型的用空间换时间的算法
    // 所以基数排序会耗费额外的内存空间，所以当数组长度过大的时候，可能会出现OutOfMemoryError错误
    public static void radixSort(int[] arr) {

        // 获取数组中最大元素
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // 得到最大元素位数
        int maxLength = (max + "").length();

        // 定义一个二维数组，表示有10个桶，每个桶的就是一个一位数组
        // 说明
        // 1.二维数组包含10个一位数组
        // 2.因为无法确定元素中对应位数相等的有多少，所以初始化bucket中桶的大小为arr.length
        // 3.很明确，基数排序是用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        // 定义一个一位数组记录每个桶中每次放入的元素个数
        int[] bucketElementCounts = new int[10];

        // 通过循环来处理每一轮排序
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素个位数的值
                int digitOfElement = arr[j] / n % 10;
                // 放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                // 更新记录元素个数的数组
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0;
            // 按顺序取出桶中所有数据
            for (int k = 0; k < bucket.length; k++) {
                if (bucketElementCounts[k] > 0) {
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
                // 每一个桶中数据取出之后必须bucketElementCounts[k] = 0,否则会影响下一轮排序
                bucketElementCounts[k] = 0;
            }
//            System.out.println(Arrays.toString(arr));
        }

        /*
        // 第一轮排序
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素个位数的值
            int digitOfElement = arr[j] % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            // 更新记录元素个数的数组
            bucketElementCounts[digitOfElement]++;
        }

        int index = 0;
        // 按顺序取出桶中所有数据
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] > 0){
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            // 每一个桶中数据取出之后必须bucketElementCounts[k] = 0,否则会影响下一轮排序
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        // 第二轮排序
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素个位数的值
            int digitOfElement = arr[j]/10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            // 更新记录元素个数的数组
            bucketElementCounts[digitOfElement]++;
        }

        index = 0;
        // 按顺序取出桶中所有数据
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] > 0){
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            // 每一个桶中数据取出之后必须bucketElementCounts[k] = 0,否则会影响下一轮排序
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));

        // 第三轮排序
        for (int j = 0; j < arr.length; j++) {
            // 取出每个元素个位数的值
            int digitOfElement = arr[j]/10/10 % 10;
            // 放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            // 更新记录元素个数的数组
            bucketElementCounts[digitOfElement]++;
        }

        index = 0;
        // 按顺序取出桶中所有数据
        for (int k = 0; k < bucket.length; k++) {
            if (bucketElementCounts[k] > 0){
                for (int l = 0; l < bucketElementCounts[k]; l++) {
                    arr[index++] = bucket[k][l];
                }
            }
            // 每一个桶中数据取出之后必须bucketElementCounts[k] = 0,否则会影响下一轮排序
            bucketElementCounts[k] = 0;
        }
        System.out.println(Arrays.toString(arr));*/
    }
}
