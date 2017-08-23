package org.cz.jvm;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenzhe8 on 2017/7/17.
 */
public class PermGenOomMock{
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, InterruptedException {
        test2();
    }

    public static void test2(){
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("d:/tmp").toURI().toURL();

            while (true){
                for (int i=0;i<100000;i++) {
                    URL[] urls = {url};
                    ClassLoader loader = new URLClassLoader(urls);
                    classLoaderList.add(loader);
                    Class.forName("org.cz.jvm.StringOomMock",true,loader);
//                    loader.loadClass("org.cz.jvm.StringOomMock");
                }
                System.out.println("========================"+new Date());
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            System.out.println("===========发生异常============");
            e.printStackTrace();
        }
    }

    public static void test1(){
        try {
            System.out.println("perm start");

            List  insList = new ArrayList();
            URL url = new File("d:/tmp").toURI().toURL();
            for (int i = 0; i < 1000000; i++) {
                URL[] urls = {url};
                URLClassLoader urlClassloader = new URLClassLoader(urls, null);
                Class<?> logfClass = Class.forName("org.cz.jvm.StringOomMock", true,urlClassloader);
                insList.add(logfClass);
//            Method getLog = logfClass.getMethod("getLogger", String.class);
//            Object result = getLog.invoke(logfClass, "TestMemOutSimpJob");
                //      insList.add(result);
                // System.out.println(i + ": " + result);
            }

            //  xxx();
            System.out.println("perm end");
        } catch (Exception e) {
            System.out.println("perm error"+e);
            e.printStackTrace();
        }
    }
}
