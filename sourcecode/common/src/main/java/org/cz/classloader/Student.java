package org.cz.classloader;

/**
 * Created by chenzhe8 on 2017/6/29.
 */
public class Student {
    static {
        System.out.println("=====加载Student类=====");
    }
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void doSomething(){

    }
}
