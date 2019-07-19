package com.lev1.interview.others;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程A、B，分别打印线程名字10次，顺序为ABABABAB....
 */
public class MyPrintDemo {

    public static void main(String[] args) throws Exception {
        MyPrintA printA = new MyPrintA();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printA.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printA.printB();
            }
        }, "B").start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println();
        System.out.println("============分割线==============");

        MyPrintB printB = new MyPrintB();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printB.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                printB.printB();
            }
        }, "B").start();
    }
}

class MyPrintA {
    private boolean flag;   // 当flag为false时，打印A，true时，打印B

    public synchronized void printA() {
        while (flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = true;
        this.notifyAll();
    }

    public synchronized void printB() {
        while (!flag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print(Thread.currentThread().getName());
        flag = false;
        this.notifyAll();
    }
}

class MyPrintB {
    private boolean flag;   // 当flag为false时，打印A，true时，打印B
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (flag) {
                condition.await();
            }
            System.out.print(Thread.currentThread().getName());
            flag = true;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (!flag) {
                condition.await();
            }
            System.out.print(Thread.currentThread().getName());
            flag = false;
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class MyPrintC{
    private AtomicInteger integer = new AtomicInteger();

}