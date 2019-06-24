package com.lev1.datastructure.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        int[][] chessArr = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][5] = 1;

        //输出原始数组
        System.out.println("原始数组~~~");
        for (int[] array : chessArr) {
            for (int data : array) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组思路
        //1.先遍历原始数组得到洗漱数组的行数
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr.length;
        sparseArr[0][1] = chessArr[0].length;
        sparseArr[0][2] = sum;

        //3.遍历原始数组，给稀疏数组赋值
        int foot = 0;
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if (chessArr[i][j] != 0) {
                    foot++;
                    sparseArr[foot][0] = i;
                    sparseArr[foot][1] = j;
                    sparseArr[foot][2] = chessArr[i][j];
                }
            }
        }

        System.out.println("稀疏数组~~~");
        //遍历稀疏数组打印转换结果
        for (int arr[] : sparseArr) {
            System.out.printf("%d\t%d\t%d\t", arr[0], arr[1], arr[2]);
            System.out.println();
        }

        //还原原始数组
        //1.定义原始数组
        int chessArr1[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2.原始数组赋值
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr1[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //打印原始数组
        System.out.println("还原后的原始数组~~~");
        for (int[] array : chessArr1) {
            for (int data : array) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
