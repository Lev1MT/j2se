package com.lev1.interview.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......
 * 重复10次
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.printA();
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.printB();
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                resource.printC();
            }
        },"CC").start();
    }
}

class ShareResource{
    private int number = 1; //A:1,B:2,C:3
    private Lock lock = new ReentrantLock();

    // 三个线程精确唤醒，所以需要三个Condition
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printA(){
        lock.lock();
        try {
            while (number != 1){
                c1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 执行完成一定要更改 number 值，否则会发生死锁
            number = 2;
            // 精确唤醒等待中的B线程
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB(){
        lock.lock();
        try {
            while (number != 2){
                c2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC(){
        lock.lock();
        try {
            while (number != 3){
                c3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}