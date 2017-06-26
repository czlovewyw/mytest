package org.cz.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by chenzhe8 on 2017/6/26.
 */
public class NewInstanceTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Student.class;

        //如果clazz没有无参构造函数则报错
        Student student = (Student) clazz.newInstance();
        System.out.println(student);

        //同上
        Constructor c = clazz.getDeclaredConstructor(new Class[0]);
        c.setAccessible(true);
        Student student2 = (Student) c.newInstance(new Object[0]);
        System.out.println(student2);
    }
}
