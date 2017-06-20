package org.cz.enhance;

import javassist.*;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhe8 on 2017/5/27.
 */
public class Enhance2 {
    public static void main(String[] args) throws Exception {

        // 获取本地类加载器
        ClassLoader classLoader = Enhance2.class.getClassLoader();
        // 获取要修改的类
        Class<?> clazz = classLoader.loadClass("org.cz.enhance.Hellottt");

        // 实例化类型池对象
        ClassPool classPool = ClassPool.getDefault();
        // 设置类搜索路径
        classPool.appendClassPath(new ClassClassPath(clazz));
        // 从类型池中读取指定类型
        CtClass ctClass = classPool.get(clazz.getName());

        // 获取String类型参数集合
        CtClass[] paramTypes = {classPool.get(String.class.getName())};
        // 获取指定方法名称
        CtMethod method = ctClass.getDeclaredMethod("test2");
        // 赋值方法到新方法中
        CtMethod newMethod = CtNewMethod.copy(method, ctClass, null);
        // 修改源方法名称
        String oldName = method.getName() + "$Impl";
        method.setName(oldName);

        // 修改原方法
        newMethod.setBody("{System.out.println(\"执行前\");" + oldName + "($$);System.out.println(\"执行后\");}");
        // 将新方法添加到类中
        ctClass.addMethod(newMethod);

        ctClass.setName("Hello2");
        // 加载重新编译的类
        clazz = ctClass.toClass();      // 注意，这一行会将类冻结，无法在对字节码进行编辑
        // 执行方法
        clazz.getMethod("test2").invoke(clazz.newInstance());
        ctClass.defrost();  // 解冻一个类，对应freeze方法
    }

}
