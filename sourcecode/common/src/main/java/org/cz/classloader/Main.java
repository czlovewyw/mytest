package org.cz.classloader;

import org.cz.reflect.*;

import java.util.Scanner;

/**
 * Created by chenzhe8 on 2017/6/29.
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String classname = scanner.next();
            try {
                Class.forName(classname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        Student.doSomething();
        new Student().doSomething();
    }
}
