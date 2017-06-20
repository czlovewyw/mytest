package org.cz.enhance;

import javassist.*;
import org.omg.CORBA.ObjectHelper;
import org.springframework.util.Assert;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhe8 on 2017/5/27.
 */
public class Enhance {
    public static void modifyMethod(CtMethod method, CtClass clazz) throws Exception{

        //从原方法复制产生一个新的方法
        CtMethod newMethod = CtNewMethod.copy(method, clazz, null);

        //重命名原方法
        String methodName = method.getLongName();
        String oldName = method.getName()+ "$Impl";

        method.setName(oldName);
        StringBuilder body = new StringBuilder();
        body.append( "{long start = System.currentTimeMillis();" );

        //如果有返回值，则记录返回值，没有则不记录
        if(method.getReturnType()==CtClass. voidType){
            body.append( oldName+ "($$);");
        } else{
            body.append( "Object result = " +oldName+"($$);" );
        }
        body.append( " long end = System.currentTimeMillis();"
                + "System.out.println(\"" +methodName+ "\""+
                "\"time used:\"+" + "(end - start));" );

        //如果有返回值，则添加return 语句
        if(method.getReturnType()==CtClass. voidType){
            body.append( "}");
        } else{
            body.append( "return result;}" );
        }
        newMethod.setBody(body.toString());
        clazz.addMethod(newMethod);


    }

    public static void main(String[] args) throws Exception {
        // 获取本地类加载器
        ClassLoader classLoader = Enhance.class.getClassLoader();
        // 获取要修改的类
        Class<?> clazz = classLoader.loadClass("org.cz.enhance.Hellottt");

        // 实例化类型池对象
        ClassPool classPool = ClassPool.getDefault();
        // 设置类搜索路径
        classPool.appendClassPath(new ClassClassPath(clazz));
        // 从类型池中读取指定类型
        CtClass ctClass = classPool.get(clazz.getName());
        // 获取指定方法名称
        CtMethod method = ctClass.getDeclaredMethod("test");
        modifyMethod(method,ctClass);

//        System.out.println(new Hellottt().test());
    }

//    private static ClassLoader getLocaleClassLoader() throws Exception {
//        List<URL> classPathURLs = new ArrayList<>();
//        // 加载.class文件路径
//        classPathURLs.add(classesPath.toURI().toURL());
//
//        // 获取所有的jar文件
//        File[] jarFiles = libPath.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".jar");
//            }
//        });
//        Assert.assertFalse(ObjectHelper.isArrayNullOrEmpty(jarFiles));
//
//        // 将jar文件路径写入集合
//        for (File jarFile : jarFiles) {
//            classPathURLs.add(jarFile.toURI().toURL());
//        }
//
//        // 实例化类加载器
//        return new URLClassLoader(classPathURLs.toArray(new URL[classPathURLs.size()]));
//    }
}
