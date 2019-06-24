package com.lev1.ownPractice.testEnum;

import java.util.Arrays;

public class testEnumMain {
    public static void main(String[] args) {
        Cat c1 = new Cat("英短",Color.RED);
        Cat c2 = new Cat("美短",Color.BLUE);
        System.out.println(c1.toString()+"、"+c2.toString());
    }

    static void testEnum(){
        System.out.println(Color.valueOf("RED"));
        System.out.println(Enum.valueOf(Color.class,"RED"));
        System.out.println(Color.GREEN.compareTo(Color.RED));
        System.out.println(Color.GREEN.name());
        System.out.println(Arrays.toString(Color.values()));
        for (Color c : Color.values()){
            System.out.print(c.getName()+"、");
        }
    }
}
