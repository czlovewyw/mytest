package org.cz.test;

import java.util.Scanner;
public class Paixu{
    static void sort2(int n,int m){
        System.out.println("随机产生的"+n+"行"+m+"列数组:");
        int [][] a = new int[n][m];
        for (int i = 0;i < a.length; i++) {
            for (int j = 0;j < a[i].length; j++) {
                a[i][j] = (int)(Math.random()*110)-10;


            }
        }
        for (int i = 0;i < a.length; i++) {
            for (int j = 0;j < a[i].length; j++) {
                System.out.print(a[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
        for (int k = 0;k < n*m; k++) {
            for (int i = 0;i < n; i++) {
                for (int j = 0;j < m; j++) {
                    if ((j+1)%a[i].length != 0) {
                        if (a[i][j] > a[i][j+1]) {
                            int temp = a[i][j];
                            a[i][j] = a[i][j+1];
                            a[i][j+1] = temp;
                        }
                    } else {
                        if (i+1 != a.length) {
                            if (a[i][j] > a[i+1][(j+1)%a[i].length]) {
                                int temp = a[i][j];
                                a[i][j] = a[i+1][(j+1)%a[i].length];
                                a[i+1][(j+1)%a[i].length] = temp;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("排序后");
        for (int i = 0;i < a.length; i++) {
            for (int j = 0;j < a[i].length; j++) {
                System.out.print(a[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        System.out.println("please input n and m:");
        Scanner scan = new Scanner(System.in);
        try{
            int a=scan.nextInt();
            int b=scan.nextInt();
            sort2(a,b);
        }catch(Exception e){
            System.out.println("捕获到异常，异常信息是"+e.getMessage());
        }
    }
}