package com.lev1.interview.others;

import java.util.*;

public class FindDuplicates {

    public static void main(String[] args) {
        int[] nums1 = {1,2, 4, 5, 9};
        int[] nums2 = {2, 4, 9, 11};
        int[] result = findDuplicates(nums1, nums2);
        int[] result2 = findDuplicates2(nums1, nums2);
        System.out.println(Arrays.toString(result));
        System.out.println(Arrays.toString(result2));
    }

    public static int[] findDuplicates(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        Object obj = new Object();
        Map<Integer, Object> mapA = new HashMap<>();
        Map<Integer, Object> mapB = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            mapA.put(a[i], obj);
        }
        for (int j = 0; j < b.length; j++) {
            mapB.put(b[j], obj);
        }

        Set<Map.Entry<Integer, Object>> set = mapA.entrySet();

        for (Map.Entry<Integer, Object> entry : set) {
            if (mapB.containsKey(entry.getKey())) {
                list.add(entry.getKey());
            }
        }
        int[] result = new int[list.size()];
        for (int n = 0; n < list.size(); n++) {
            result[n] = list.get(n);
        }
        return result;
    }

    public static int[] findDuplicates2(int[] a, int[] b) {
        List<Integer> list = new ArrayList<>();
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] < b[j]) {
                i++;
            } else if (b[j] < a[i]) {
                j++;
            } else {
                list.add(a[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[list.size()];
        for (int n = 0; n < list.size(); n++) {
            result[n] = list.get(n);
        }
        return result;
    }
}
