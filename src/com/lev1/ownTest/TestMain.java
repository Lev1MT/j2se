package com.lev1.ownTest;

public class TestMain {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
            }, "Thread" + i).start();
        }
        System.out.println(Thread.currentThread().getName());
//        for (int i = 1; i < 101; i++){
//            if (i%7 == 0){
//                continue;
//            }
//            if (i%3 != 0 && i%5 != 0){
//                System.out.println(i);
//            }
//        }
    }
}

abstract class Test<T extends Test<T>> {
    public T code() {
        return self();
    }

    protected abstract T self();

}