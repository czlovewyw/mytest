package org.cz.classloader;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

/**
 * 转化字符编码（unicode字节序需要再研究下）
 * Created by chenzhe8 on 2017/6/30.
 */
public class T {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = "你好".getBytes();
        System.out.println(bytesToHexString(bytes));
        System.out.println(new String(new String(bytes,"utf8").getBytes("gb2312"),"gbk"));
        System.out.println(bytesToHexString(new String(bytes,"utf8").getBytes("gb2312")));

        System.out.println("===============");
        System.out.println(bytesToHexString("你好".getBytes("utf8")));
        System.out.println(bytesToHexString("你好".getBytes("gbk")));

        String s ="===========全量用户同步完成==========";
        //===========鍏ㄩ噺鐢ㄦ埛鍚屾瀹屾垚==========
        System.out.println(new String(s.getBytes(),"gbk"));
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();

    }
}
