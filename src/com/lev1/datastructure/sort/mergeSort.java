package com.lev1.datastructure.sort;

import java.util.Arrays;

public class mergeSort {

    public static void main(String[] args) {
        int[] a = {2, 3, 6, 7, 1, 4, 5, 8};
        int[] b = new int[a.length];
        mergeSort(a, 0, a.length - 1, b);
        System.out.println(Arrays.toString(a));

        // 测试使用插入排序的shell排序执行速度
        int testArr[] = new int[80000];
        int temp[] = new int[testArr.length];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = (int) (Math.random() * 10000);// 生成一个0-10000的随机数
        }
        long mergeStart = System.currentTimeMillis();
        mergeSort(testArr, 0, testArr.length - 1,temp);
        long mergeEnd = System.currentTimeMillis();
        System.out.println("快速排序排序总共用时:" + (mergeEnd - mergeStart) + "毫秒");
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (right + left) / 2;
            // 向左递归分解
            mergeSort(arr, left, mid, temp);
            // 向右递归分解
            mergeSort(arr, mid + 1, right, temp);
            // 合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * arr数组分解之后合并步骤
     *
     * @param arr   原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  辅助数组，用于存放中间数据
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int foot = 0;// 记录temp数组当前插入位置索引
        int i = left;// 初始化i，左边有序序列的初始索引
        int j = mid + 1;// 初始化j，右边有序序列的初始索引


        // 第一步:
        // 先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到有一边的数据处理完毕
        while (i <= mid && j <= right) {
            // 如果左边数组的当前元素小于等于右边数组当前元素，则将左边数组元素拷贝到temp，反之亦然
            if (arr[i] <= arr[j]) {
                temp[foot] = arr[i];
                i += 1;
                foot += 1;
            } else {
                temp[foot] = arr[j];
                j += 1;
                foot += 1;
            }
        }

        // 第二步
        // 将有剩余数据的数组数据全部填充到temp
        while (i <= mid) {
            temp[foot] = arr[i];
            i += 1;
            foot += 1;
        }
        while (j <= right) {
            temp[foot] = arr[j];
            j += 1;
            foot += 1;
        }

        // 第三步
        // 将temp数组的元素拷贝到arr中
        int t = 0;
        int tempLeft = left;// 因为每一次分拆后的数组不一定是是合并所有元素，每一次分拆后的元素合并个数不一样
//        System.out.println("tempLeft:" + tempLeft + "right:" + right);
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
