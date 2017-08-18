package com.flyingideal.utility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Sets {

    /**
     * 两个Set求并集
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<T>(set1);
        set.addAll(set2);
        return set;
    }

    /**
     * 两个Set求交集
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> set = new HashSet<T>(set1);
        set.retainAll(set2);
        return set;
    }

    /**
     * 从superSet中剔除subSet中已经存在于superSet中的所有数据
     * @param superSet
     * @param subSet
     * @param <T>
     * @return
     */
    public static <T> Set<T> difference(Set<T> superSet, Set<T> subSet) {
        Set<T> set = new HashSet<T>(superSet);
        set.removeAll(subSet);
        return set;
    }

    /**
     * 求两个Set除交集之外的所有元素
     * @param set1
     * @param set2
     * @param <T>
     * @return
     */
    public static <T> Set<T> complement(Set<T> set1, Set<T> set2) {
        return difference(union(set1, set2), intersection(set1, set2));
    }


    public static void main(String[] args) {
        Set<Integer> set1 = new HashSet<Integer>(Arrays.asList(1,2,3,4,5));
        Set<Integer> set2 = new HashSet<Integer>(Arrays.asList(3,4,5,6,7));
        System.out.println(union(set1, set2));
        System.out.println(intersection(set1, set2));
        System.out.println(difference(set1, set2));     //[1,2]
        System.out.println(complement(set1, set2));     //[1,2,6,7]
    }
}
