package org.cz.enhance;

/**
 * Created by chenzhe8 on 2017/5/27.
 */
public class Hellottt {
    public String test() throws Exception{
        Thread.currentThread ().sleep(1000);
        return "test";
    }

    public void test2() throws Exception{
        Thread.currentThread ().sleep(1000);
        System.out.println("test2");
    }
}
