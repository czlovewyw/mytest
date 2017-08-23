package org.cz;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.*;
import java.util.Base64;

/**
 * 序列化反序列化
 * Created by chenzhe8 on 2017/6/28.
 */
public class JDKSerialize implements Serializable{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        JDKSerialize JDKSerialize = new JDKSerialize();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(byteArrayOutputStream);
        os.writeObject(JDKSerialize);
        os.writeObject("this is JDKSerialize test");
        String token = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());

        System.out.println(token);
        byte[] tokenArr = Base64.getDecoder().decode(token.getBytes());
        ObjectInputStream in = new ObjectInputStream(new ByteInputStream(tokenArr,tokenArr.length));
        JDKSerialize JDKSerialize2 = (JDKSerialize)in.readObject();
        String str = (String)in.readObject();
        System.out.println(str);
    }
}
