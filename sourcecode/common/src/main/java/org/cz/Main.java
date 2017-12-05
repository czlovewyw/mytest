package org.cz;

import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner in = new Scanner(System.in);
        String s = "";
        while(in.hasNext()){
            s+=new String(in.next().getBytes(),"utf-8");
        }
        System.out.println(s+", Hello!");
    }
}
