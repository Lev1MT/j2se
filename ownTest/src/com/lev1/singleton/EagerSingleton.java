package com.lev1.singleton;

/**
 *  饿汉式单例
 *  优点：实现容易，线程安全，没有锁，执行效率高
 *  缺点：在类加载时就初始化，浪费内存，容易产生垃圾对象
 */
public class EagerSingleton {
    private final String name = "饿汉式";
    private static final EagerSingleton es = new EagerSingleton();
    private EagerSingleton(){
        System.out.println("饿汉式单例创建成功");
    }  // 单例的核心，构造私有化
    public static EagerSingleton getInstance(){
        return es;
    }

    public String getName() {
        return name;
    }
}
