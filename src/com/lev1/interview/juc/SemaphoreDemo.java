package com.lev1.interview.juc;


import java.util.concurrent.Semaphore;

/**
 * Semaphore信号量主要用于两个目的
 * 一个是用于多个共享资源的互斥使用
 * 另一个用于并发线程数的控制
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);// 模拟三个车位

        for (int i = 0; i < 6; i++) {   // 总共6辆车抢三个车位
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢到车位");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "停车1秒之后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
