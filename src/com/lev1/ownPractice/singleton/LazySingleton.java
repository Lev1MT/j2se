package com.lev1.ownPractice.singleton;

/**
 *  懒汉式单例
 *  优点：第一次调用时初始化，避免内存浪费。
 *  缺点：必须加锁synchronized才能保证单例，但加锁会影响效率
 */
public class LazySingleton {
    private static LazySingleton ls;
    private LazySingleton(){}
    public static /* synchronized */ LazySingleton getInstance(){   // 如果在此处加synchronized关键字 单例获取效率较低
        if (ls == null) {
            synchronized (LazySingleton.class) {    // 此处增加synchronized关键字，性能较高
                if (ls == null) {                   // 双重校验锁（DCL,即double-checked locking）
                    ls = new LazySingleton();
                }
            }
        }
        return ls;
    }
}
