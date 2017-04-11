package org.cz;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by chenzhe8 on 2017/3/30.
 */
public class T {
    public static void qqq() {
        List<String> list = test3(10_000_000);
        long t = System.currentTimeMillis();
        long total = 0L;
        for (int i = 0; i < list.size(); i++) {
            int v = Integer.parseInt(list.get(i));
            if (v % 2 == 1) {
                total++;
            }
        }
        System.out.println(System.currentTimeMillis() - t);
        System.out.println(total);
    }
    public static void qqq2() {
        List<String> list = test3(10_000_000);
        long t = System.currentTimeMillis();
        long total = list.parallelStream().filter(s -> {
            int v = Integer.parseInt(s);
            return v % 2 == 1;
        }).count();
        System.out.println(System.currentTimeMillis() - t);
        System.out.println(total);
    }

    public static List test3(int num){
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }
}
