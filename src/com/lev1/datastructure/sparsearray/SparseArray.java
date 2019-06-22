package com.lev1.datastructure.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        int[][] charseArr = new int[11][11];
        charseArr[1][2] = 1;
        charseArr[2][3] = 2;

        //输出原始数组
        for (int[] array: charseArr) {
            for (int data : array){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
