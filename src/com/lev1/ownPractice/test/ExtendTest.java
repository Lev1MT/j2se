package com.lev1.ownPractice.test;

public class ExtendTest {


}


class Art{
    public Art(int i) {
        System.out.println("Art被创建"+i);
    }
}

class Drawing extends Art{

    public Drawing(int i) {
        super(i);
    }
}