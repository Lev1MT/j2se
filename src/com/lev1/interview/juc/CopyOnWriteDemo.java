package com.lev1.interview.juc;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteDemo {

    public static void main(String[] args) {
//        copyOnWriteTest();
//        arrryListTest();
        synchronizedArrayListTest();
    }

    // 这里会出现java.util.ConcurrentModificationException
    // 并发修改导致的异常
    public static void arrryListTest(){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }

    // 使用JUC包下的CopyOnWriteArrayList可以解决线程安全问题
    // 因为它通过用volatile来修饰数组，并且对相关写操作加锁
    public static void copyOnWriteTest(){
        CopyOnWriteArrayList<String> list  = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }

    // 使用
    public static void synchronizedArrayListTest(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        System.out.println(list.getClass());
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            }).start();
        }
    }
}
