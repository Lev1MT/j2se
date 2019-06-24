package com.lev1.ownPractice.interview;

public class Singleton {
    public static void main(String[] args) {
        Singleton1 s = Singleton1.INSTANCE;
        s.setAge(1);
        s.setName("张三");
        new Thread(() ->{
            System.out.println(Thread.currentThread().getName()+"姓名"+Singleton1.INSTANCE.getName()+"年龄"+Singleton1.INSTANCE.getAge());
        },"线程1").start();

        new Thread(() ->{
            Singleton1.INSTANCE.setAge(20);
            System.out.println(Thread.currentThread().getName()+"姓名"+Singleton1.INSTANCE.getName()+"年龄"+Singleton1.INSTANCE.getAge());
        },"线程2").start();

        new Thread(() ->{
            Singleton1.INSTANCE.setAge(30);
            System.out.println(Thread.currentThread().getName()+"姓名"+Singleton1.INSTANCE.getName()+"年龄"+Singleton1.INSTANCE.getAge());
        },"线程3").start();

        new Thread(() ->{
            Singleton1.INSTANCE.setAge(40);
            System.out.println(Thread.currentThread().getName()+"姓名"+Singleton1.INSTANCE.getName()+"年龄"+Singleton1.INSTANCE.getAge());
        },"线程4").start();

        System.out.println(Thread.currentThread().getName()+"姓名"+Singleton1.INSTANCE.getName()+"年龄"+Singleton1.INSTANCE.getAge());
    }
}

class Singleton1{
    private int age;
    private String name;
    private Singleton1(){}
    public static final Singleton1 INSTANCE = new Singleton1();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Singleton2{
    private Singleton2(){}

    private static class Inner{
        public static final Singleton2 INSTANCE = new Singleton2();
    }

    public static Singleton2 getInstance(){
        return Inner.INSTANCE;
    }

}

class Singleton3{
    private static Singleton3 instance;
    private Singleton3(){}

    public static Singleton3 getInstance(){
        if (instance == null){
            synchronized (Singleton3.class){
                if (instance == null) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}