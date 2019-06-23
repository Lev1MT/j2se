package com.lev1.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);//创建一个队列
        char key = ' ';// 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("a(add):增加元素");
            System.out.println("g(get):获取元素");
            System.out.println("e(exit):退出程序");
            System.out.println("h(head):获取头元素");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("请输入元素值：");
                    queue.add(scanner.nextInt());
                    break;
                case 'g':
                    System.out.println("获取到的值为：" + queue.get());
                    break;
                case 'h':
                    System.out.println("队列头元素值为：" + queue.getHead());
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序已退出!");
    }
}


class ArrayQueue {
    private int maxSize;//队列长度，数组最大值
    private int rear;// 队列尾
    private int front;// 队列头
    private int[] arr;// 用于存放数据的数组，模拟队列

    // 创建构造方法
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        rear = -1;
        front = -1;
    }

    // 判断队满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    // 判断队空
    public boolean isEmpty() {
        return rear == front;
    }

    // 向队列中插值
    public void add(int a) {
        if (isFull()) {
            System.out.println("队列已满~~");
            return;
        }
        rear++;
        arr[rear] = a;
    }

    // 从队列中取出元素
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无法取出元素");
        }
        front++;
        return arr[front];
    }

    // 遍历队列中元素
    public void show() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据~~");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 查看队首元素
    public int getHead() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空，没有数据~~");
        }
        return arr[rear];
    }
}