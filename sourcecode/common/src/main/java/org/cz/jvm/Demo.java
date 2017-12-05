package org.cz.jvm;

import org.cz.jaxb.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws NoSuchMethodException {
        Method defineClass = ClassLoader.class.getDeclaredMethod("defineClass", String.class,byte[].class, int.class, int.class);
        defineClass.setAccessible(true);
        File file = new File("F:\\development\\mytest\\sourcecode\\common\\target\\classes\\org\\cz\\jaxb\\Main.class");
        byte[] bcs = new byte[(int)file.length()];
        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
            while ((in.read(bcs))!=-1){

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(in!=null){
                try {
                    in.close();
                }catch (IOException e){

                }
            }
        }


        while (true){
            Scanner scanner = new Scanner(System.in);
            if("true".endsWith(scanner.next())){
                try {
                    defineClass.invoke(Main.class.getClassLoader(),"Main",bcs,0,bcs.length);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
