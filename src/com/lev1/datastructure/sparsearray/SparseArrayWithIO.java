package com.lev1.datastructure.sparsearray;

import java.io.*;

public class SparseArrayWithIO {
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

        output(sparseArr);

        int sparseArrFromFile[][] = input();

        System.out.println("稀疏数组~~~");
        //遍历稀疏数组打印转换结果
        for (int arr[] : sparseArrFromFile) {
            System.out.printf("%d\t%d\t%d\t", arr[0], arr[1], arr[2]);
            System.out.println();
        }

        //还原原始数组
        //1.定义原始数组
        int chessArr1[][] = new int[sparseArrFromFile[0][0]][sparseArrFromFile[0][1]];

        //2.原始数组赋值
        for (int i = 1; i < sparseArrFromFile.length; i++) {
            chessArr1[sparseArrFromFile[i][0]][sparseArrFromFile[i][1]] = sparseArrFromFile[i][2];
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

    static void output(int data[][]) {
        try {
            FileWriter output = new FileWriter("d:" + File.separator + "map.data");
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[i].length; j++) {
                    output.write(data[i][j] + "\t");
                }
                output.write("\r\n");
            }
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static int[][] input() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("d:" + File.separator + "map.data"));
            String line;
            int count = 0;
            while ((line = reader.readLine())!= null){
                count++;
            }
            int sparseArr[][] = new int[count][3];
            reader.close();
            BufferedReader reader1 = new BufferedReader(new FileReader("d:" + File.separator + "map.data"));
            int row = 0;
            while ((line = reader1.readLine())!=null){
                String str[] = line.split("\t");
                sparseArr[row][0] = Integer.parseInt(str[0]);
                sparseArr[row][1] = Integer.parseInt(str[1]);
                sparseArr[row][2] = Integer.parseInt(str[2]);
                row++;
            }
            reader1.close();
            return sparseArr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
