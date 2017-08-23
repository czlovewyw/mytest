package org.cz.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenzhe8 on 2017/7/17.
 */
public class StringOomMock {
    static String  base = "string";
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        try {
            while (true){
                String str = base + base;
                base = str;
                list.add(str.intern());
//                System.out.println(str);
                Thread.sleep(500);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}