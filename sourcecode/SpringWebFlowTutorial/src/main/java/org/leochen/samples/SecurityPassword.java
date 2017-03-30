package org.leochen.samples;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

/**
 * User: leochen
 * Date: 11-12-5
 * Time: 下午4:20
 */
public class SecurityPassword {
    public static void main(String[] args){
        ApplicationContext context=new ClassPathXmlApplicationContext("spring/applicationContext-base.xml");
        PasswordEncoder passwordEncoder = context.getBean("passwordEncoder", PasswordEncoder.class);
//        SaltSource saltSource = context.getBean("saltSource",SaltSource.class);

        System.out.println("username=keith,password="+passwordEncoder.encodePassword("melbourne","keith"));
        System.out.println("username=erwin,password="+passwordEncoder.encodePassword("leuven","erwin"));
        System.out.println("username=jeremy,password="+passwordEncoder.encodePassword("atlanta","jeremy"));
        System.out.println("username=scott,password="+passwordEncoder.encodePassword("rochester","scott"));
        System.out.println("username=admin,password="+passwordEncoder.encodePassword("admin","admin"));

//      keith/melbourne
//		erwin/leuven
//		jeremy/atlanta
//		scott/rochester
    }
}
