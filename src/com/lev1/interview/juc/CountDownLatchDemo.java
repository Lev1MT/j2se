package com.lev1.interview.juc;


import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch 类似于计数器，可以实现计数器功能
 * 让一些线程堵塞直到另一个线程完成一系列操作后才被唤醒。
 * CountDownLatch 主要有两个方法，
 * 当一个或多个线程调用 await 方法时，调用线程会被堵塞，
 * 其他线程调用 countDown 方法会将计数减一（调用 countDown 方法的线程不会堵塞），
 * 当计数其值变为零时，因调用 await 方法被堵塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch count = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 被灭");
                // 每次执行之后count-1，直到count变为0
                count.countDown();
            },CountryEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }
        // 如果count还未变为0，则继续等待
        try {
            count.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("所有国家被灭，秦一统华夏");
    }
}

/**
 * 使用countryEnum可以实现类似于数据库存储功能
 */
enum CountryEnum {
    ONE(0, "齐"),
    TWO(1, "楚"),
    THREE(2, "燕"),
    FOUR(3, "赵"),
    FIVE(4, "魏"),
    SIX(5, "韩");

    private Integer retCode;

    private String retMessage;

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public Integer getRetCode() {
        return retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public static CountryEnum foreach_CountryEnum(int i) {
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum country : countryEnums) {
            if (country.getRetCode() == i) {
                return country;
            }
        }
        return null;
    }
}