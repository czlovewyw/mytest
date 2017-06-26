package org.cz.reflect;

/**
 * Created by chenzhe8 on 2017/6/26.
 */
public class Student {
    private String name;
    private String address;

    public Student() {
    }

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
