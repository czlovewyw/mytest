package org.cz.security;

import sun.security.rsa.RSAPrivateCrtKeyImpl;
import sun.security.rsa.RSAPrivateKeyImpl;
import sun.security.rsa.RSAPublicKeyImpl;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * Created by chenzhe8 on 2017/7/12.
 */
public class License {
    public static BigInteger moudle;
    public static BigInteger pub;
    public static void main(String[] args) {
        String license = "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";
        System.out.println(decry(Base64.getDecoder().decode(license.getBytes())));
    }
    public static String decry(byte[] temp) {

        if (temp == null) {
            return null;
        }

        Cipher cipher;
        StringBuffer result = new StringBuffer();
        BufferedInputStream inputReader = null; // 将输入用Base64算法解码

        try {

            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            byte[] buf = new byte[128];
            int bufl;
            inputReader = new BufferedInputStream(new ByteArrayInputStream(temp));
            byte[] b = new byte[8];
            inputReader.read(b);
            Integer a = Integer.valueOf(new String(b, LicConstParameter.DEFAULT_CHARSET));
            b = new byte[a];
            inputReader.read(b);
            pub = new BigInteger(new String(b, LicConstParameter.DEFAULT_CHARSET));
            b = new byte[8];
            inputReader.read(b);
            a = Integer.valueOf(new String(b, LicConstParameter.DEFAULT_CHARSET));
            b = new byte[a];
            inputReader.read(b);
            moudle = new BigInteger(new String(b, LicConstParameter.DEFAULT_CHARSET));
            RSAPublicKey publickKey = new RSAPublicKeyImpl(moudle, pub);
            cipher.init(Cipher.DECRYPT_MODE, publickKey);

            while ((bufl = inputReader.read(buf)) != -1) {
                byte[] encText = null;
                byte[] newArr = null;
                if (buf.length == bufl) {
                    newArr = buf;
                } else {
                    newArr = new byte[bufl];
                    for (int i = 0; i < bufl; i++) {
                        newArr[i] = buf[i];
                    }
                }

                encText = cipher.doFinal(newArr);
                result.append(new String(encText, LicConstParameter.DEFAULT_CHARSET));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

    public static String encry(byte[] temp) throws InvalidKeyException {
//        RSAPrivateKey privateKey = new RSAPrivateCrtKeyImpl();
        return null;
    }
}
