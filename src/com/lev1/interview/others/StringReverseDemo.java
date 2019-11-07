package com.lev1.interview.others;

public class StringReverseDemo {

    public static void main(String[] args) {
        System.out.println(reverse("fedcba"));
    }

    public static String reverse(String str) {
        char temp;
        char[] result = new char[str.length()];
        int n = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            temp = str.charAt(i);
            result[n++] = temp;
        }

        return new String(result);
    }
}
