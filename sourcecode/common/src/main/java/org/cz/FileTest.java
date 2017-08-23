package org.cz;

import java.io.*;

/**
 * Created by chenzhe8 on 2017/7/10.
 */
public class FileTest {
    public static void main(String[] args) throws IOException {
//        FileOutputStream os = new FileOutputStream("d:\\cztst.txt");
//        PrintWriter printWriter = new PrintWriter(os);
//        printWriter.println("this is a test");
//        printWriter.close();

        FileInputStream in = new FileInputStream("d:\\cztst.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String s = reader.readLine();
        System.out.println(s);
    }
}
