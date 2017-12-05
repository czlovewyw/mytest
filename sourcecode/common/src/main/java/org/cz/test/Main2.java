package org.cz.test;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
            dAM1();
    }

    public static void dAM1() {
        int[][] a = {
                { 12, 26, 89, 78, 45, 65, 36 },
                { 2, 6, 90, 34, 16, 27, 5 },
                { 13, 3, 4, 29, 33, 37, 17 },
                { 1, 9, 19, 49, 57, 22, 11 },
                { 7, 82, 35, 21, 24, 59, 60 } };
        for (int i = 0; i < a.length; i++) { // 二维数组的长度
            for (int j = 0; j < a[i].length; j++) { // 每个一维数组的长度
                int n = j + 1;
                for (int m = i; m < a.length; m++) {
                    for (; n < a[i].length; n++) {
                        if (a[i][j] > a[m][n]) {
                            int max = a[m][n];
                            a[m][n] = a[i][j];
                            a[i][j] = max;
                        }
                    }
                    n = 0; // 此处是给n从第二个一维数组开始取0这个坐标
                }
            }
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                System.out.print(a[i][j] + "   ");
            }
            System.out.println();
        }
    }
}
