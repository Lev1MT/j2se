package com.lev1.testEnum;

public enum Color {
    RED("红色", 1),    //红色
    GREEN("绿色", 2),  //绿色
    BLUE("蓝色",3);    //蓝色

    private String name;
    private int index;

    private Color(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "Color{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
