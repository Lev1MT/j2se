package com.lev1.interview.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}


/**
 * 模拟缓存操作
 * 读-读能共存
 * 写-读不能共存
 * 写-写不能共存
 * 写操作：原子+独占
 */
class MyCache {
    public volatile Map<String, Object> map = new HashMap<>();
    // JUC下的读写锁
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        try {
            // 使用读锁
            readWriteLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t 开始写入：" + key);
            Thread.sleep(300);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key) {
        try {
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "\t 开始读取");
            Thread.sleep(300);
            Object object = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + object);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}