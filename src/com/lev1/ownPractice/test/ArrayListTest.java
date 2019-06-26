package com.lev1.ownPractice.test;

import java.util.ArrayList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {
        List<String> list;
        list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add("a");
        }
        System.out.println(list);
    }
}
