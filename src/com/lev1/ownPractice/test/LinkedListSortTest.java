package com.lev1.ownPractice.test;

import java.util.*;

public class LinkedListSortTest {
    public static void main(String[] args) {
        LinkedListSortPOJO pojo1 = new LinkedListSortPOJO(2, "张三");
        LinkedListSortPOJO pojo2 = new LinkedListSortPOJO(6, "张三");
        LinkedListSortPOJO pojo3 = new LinkedListSortPOJO(3, "张三");
        List<LinkedListSortPOJO> list = new LinkedList<>();
        list.add(pojo1);
        list.add(pojo2);
        list.add(pojo3);
        linkedListSort(list);
    }

    public static void linkedListSort(List<LinkedListSortPOJO> list){
        Collections.sort(list);
    }
}

class LinkedListSortPOJO implements Comparable<LinkedListSortPOJO> {

    private int id;
    private String name;

    public LinkedListSortPOJO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(LinkedListSortPOJO o) {
        return o.getId()- this.id;
    }
}