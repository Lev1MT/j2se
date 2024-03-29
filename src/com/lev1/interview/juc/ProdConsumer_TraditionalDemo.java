package com.lev1.interview.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 题目：一个初始值为0的变量，两个线程对其进行交替操作，一个加1一个减1，来5轮
 *
 * 1. 线程    操作    资源类
 * 2. 判断    干活    通知
 * 3. 防止虚假唤醒机制
 */
public class ProdConsumer_TraditionalDemo {

    public static void main(String[] args) {
        ShareData data = new ShareData();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    data.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}

class ShareData{    // 资源类
    private Integer number = 0;
    private Lock lock = new ReentrantLock();
    private Condition  condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            while (number!=0){
                // 等待，不能生产
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement(){
        lock.lock();
        try {
            while (number==0){
                // 等待，不能消费
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            // 通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}