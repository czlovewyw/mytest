package org.cz.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.*;
import java.security.cert.*;
import java.security.cert.Certificate;
import java.util.Base64;

/**
 * 密钥库操作demo
 * Created by chenzhe8 on 2017/6/20.
 */
public class ManageKeystore {
    public static void main (String[] args) throws Exception {
//        KeyStore ks = getKeystore("d:\\test.keystore","123456");
        KeyStore ks = getKeystore("d:\\tomcat.keystore","88075998");
        PrivateKey privateKey = getPrivateKey(ks,"wyw","123456");
        System.out.println(Base64.getEncoder().encodeToString(privateKey.getEncoded()));
//        System.out.println(privateKey.getAlgorithm());
//        System.out.println(getCertificate(ks,"client").equals(getCertificateFromFile("d:\\client.cer")));
    }

    /**
     * 获取keystore
     * @param keystorePath keystore路径
     * @param password keystore密码
     * @return
     * @throws KeyStoreException
     * @throws IOException
     * @throws CertificateException
     * @throws NoSuchAlgorithmException
     */
    public static KeyStore getKeystore(String keystorePath,String password) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream in = new FileInputStream(keystorePath);
        ks.load(in,password.toCharArray());
        in.close();
        return ks;
    }

    /**
     * 从keystore获取私钥
     * @param ks keystore
     * @param keyAlias 密钥对别名
     * @param keyPassword 密钥对密码
     * @return
     * @throws UnrecoverableKeyException
     * @throws NoSuchAlgorithmException
     * @throws KeyStoreException
     */
    public static PrivateKey getPrivateKey(KeyStore ks,String keyAlias,String keyPassword) throws UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        return (PrivateKey) ks.getKey(keyAlias,keyPassword.toCharArray());
    }

    /**
     * 从keystore获取证书
     * @param ks keystore
     * @param keyAlias 密钥对别名
     * @return
     * @throws KeyStoreException
     */
    public static X509Certificate getCertificate(KeyStore ks,String keyAlias) throws KeyStoreException {
        return (X509Certificate) ks.getCertificate(keyAlias);
    }

    /**
     * 从文件获取证书
     * @param certificatePath 证书路径
     * @return
     * @throws CertificateException
     * @throws IOException
     */
    public static Certificate getCertificateFromFile(String certificatePath) throws CertificateException, IOException {
        CertificateFactory certificateFactory =CertificateFactory.getInstance("X.509");
        FileInputStream in = new FileInputStream(certificatePath);
        Certificate certificate = certificateFactory.generateCertificate(in);
        in.close();
        return certificate;
    }

    /**
     * 从证书中获取公钥
     * @param certificate 证书
     * @return
     */
    public static PublicKey getPublicKey(Certificate certificate){
        return certificate.getPublicKey();
    }
}
