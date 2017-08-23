package org.cz.security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Base64;

/**
 * 公钥加密，私钥解密；私钥加密，公钥解密
 * 加密算法：RSA
 * Created by chenzhe8 on 2017/6/20.
 */
public class EncryptDemo {
    public static void main(String[] args) throws Exception {
        KeyStore ks = ManageKeystore.getKeystore("d:/CA/certs/server.p12","123456");
        PrivateKey privateKey = ManageKeystore.getPrivateKey(ks,"1","123456");
        PublicKey publicKey = ManageKeystore.getPublicKey(ManageKeystore.getCertificate(ks,"1"));

        String message = "这段数据即将被公钥加密";
        System.out.println("公钥加密前数据："+message);
        byte[] encrypted = encryptByPublicKey(message.getBytes("utf-8"),publicKey);
        System.out.println("公钥加密后数据base64："+ Base64.getEncoder().encodeToString(encrypted));
        System.out.println("私钥解密后数据："+new String(decryptByPrivateKey(encrypted,privateKey),"utf-8"));

        String message2 = "这段数据即将被私钥加密";
        System.out.println("私钥加密前数据："+message2);
        byte[] encrypted2 = encryptByPrivateKey(message2.getBytes("utf-8"),privateKey);
        System.out.println("私钥加密后数据base64："+ Base64.getEncoder().encodeToString(encrypted2));
        System.out.println("公钥解密后数据："+new String(decryptByPublicKey(encrypted2,publicKey),"utf-8"));
    }

    /**
     * 公钥加密
     * @param data 待加密数据
     * @param publicKey 公钥
     * @return 加密后数据
     */
    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     * @param data 待解密数据
     * @param privateKey 私钥
     * @return 解密后数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥加密
     * @param data 待加密数据
     * @param privateKey 私钥
     * @return 加密后数据
     */
    public static byte[] encryptByPrivateKey(byte[] data,PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(privateKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE,privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 公钥解密
     * @param data 解密前数据
     * @param publicKey 公钥
     * @return 解密后数据
     */
    public static byte[] decryptByPublicKey(byte[] data,PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(publicKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE,publicKey);
        return cipher.doFinal(data);
    }
}
