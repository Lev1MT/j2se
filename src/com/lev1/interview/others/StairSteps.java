package com.lev1.interview.others;

public class StairSteps {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(steps1(40));
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        long start1 = System.currentTimeMillis();
        System.out.println(steps2(40));
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);
    }

    static int steps1(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("楼梯步数必须大于0");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int step = steps1(n - 2) + steps1(n - 1);
        return step;
    }

    static int steps2(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("楼梯步数必须大于0");
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int sum = 0;
        int one = 1;
        int two = 2;
        for (int i = 3; i <= n; i++) {
            sum = one + two;
            one = two;
            two = sum;
        }
        return sum;
    }
}
