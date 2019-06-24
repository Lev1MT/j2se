package com.lev1.ownPractice.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        EagerSingleton es = EagerSingleton.getInstance();
        if (es instanceof EagerSingleton){
            System.out.print("饿汉式单例构建成功。"+"姓名：");
            System.out.println(es.getName());
        }
    }
}
