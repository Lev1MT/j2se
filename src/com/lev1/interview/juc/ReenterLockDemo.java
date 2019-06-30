package com.lev1.interview.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过sendSMS()和sendEmail()可以看出synchronized是一个典型的可重入锁
 * 通过get()和set()可以看出ReentrantLock也是一个典型的可重入锁
 */
public class ReenterLockDemo implements Runnable {

    Lock lock = new ReentrantLock();

    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t invoked sendEmail()");
    }

    public static void main(String[] args) {
        ReenterLockDemo reenterLockDemo = new ReenterLockDemo();
        new Thread(() -> {
            reenterLockDemo.sendSMS();
        }, "t1").start();
        new Thread(() -> {
            reenterLockDemo.sendSMS();
        }, "t2").start();
        new Thread(reenterLockDemo, "t3").start();
        new Thread(reenterLockDemo, "t4").start();
    }

    @Override
    public void run() {
        get();
    }

    public void get() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t invoked get()");
            set();
        } finally {
            lock.unlock();
        }
    }

    public void set() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "\t invoked set()");
        } finally {
            lock.unlock();
        }
    }
}
