package com.lev1.interview.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile是jvm提供的轻量级同步机制
 * 特点：保证可见性、禁止指令重排、不保证原子性
 * 1.验证volatile的可见性
 * 2.验证volatile不保证原子性
 * 2.1 原子性什么意思？
 * 不可分割，完整性，也就是在某个线程执行具体业务的时候，中间不能被加塞或者被分割。需要整体完整
 * 要么同时成功，要么同时失败
 * 使用JUC包下的AtomicInteger可以保证原子性
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();
//        volatileAtomicVerify();
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.atomicAddPlus();
                }
            }).start();
        }
        // Thread.yield(),线程让步，即当前线程让出cpu执行时间，让其他线程执行
        // 大于2，是因为有两个默认线程，一个是main线程，一个是gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.activeCount());// 验证java默认线程
        System.out.println("计算后的number为：" + myData.integer);
    }

    // 验证volatile不保证原子性
    // 20个线程同时对number进行操作，但结果到不了20000
    private static void volatileAtomicVerify() {
        MyData myData = new MyData();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    myData.addPlus();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面的20个线程全部计算完成，再用main线程获取最终的结果值
        // Thread.sleep()无法保证20个线程在5s之内一定完成，所以采用Thread.yield()
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        // Thread.yield(),线程让步，即当前线程让出cpu执行时间，让其他线程执行
        // 大于2，是因为有两个默认线程，一个是main线程，一个是gc线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(Thread.activeCount());// 验证java默认线程
        System.out.println("计算后的number为：" + myData.number);
    }


    // 验证volatile的可见性
    private static void seeOkByVolatile() {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myData.addTo60();
            System.out.println(Thread.currentThread().getName() + "\t updated number value:" + myData.number);
        }, "AA").start();
        while (myData.number == 0) {
            // main线程一直在这里等待循环，直到number值不再为0
        }
        System.out.println("更改可见");
    }
}

class MyData {
    // 这里如果不加volatile，main线程会一直循环等待，因为AA线程对number的修改main线程不可见
    public volatile int number = 0;

    // 使用AtomicInteger来保证原子性
    AtomicInteger integer = new AtomicInteger(0);

    public void atomicAddPlus() {
        integer.getAndIncrement();
    }

    public void addTo60() {
        this.number = 60;
    }

    public void addPlus() {
        this.number++;
    }
}
