package vision.apollo.encrypt;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * rsa工具类
 *
 * @author chenzhe8 2017/11/16 19:18
 */
public class RSABuilder {

//    public PublicKey publicKey;成长
//    public PrivateKey privateKey;

    public static final int KEY_SIZE = 2048;
    public static final String PUBLIC_KEY = "public_key";
    public static final String PRIVATE_KEY = "private_key";
    public static final String KEY_ALGORITHM = "RSA";

//    public RSABuilder getInstance(){
//        return new RSABuilder();
//    }

    /**
     * 初始化密钥对
     *
     * @return
     */
    public static Map<String, Key> initKeyPair() {
        KeyPairGenerator keyGen = null;
        try {
            //实例化一个RSA算法的公钥/私钥对生成器
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //设置公钥/私钥对的长度
        keyGen.initialize(KEY_SIZE);
        //生成一个RSA算法的公钥/私钥
        KeyPair keyPair = keyGen.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        Map<String, Key> result = new HashMap<String, Key>();
        result.put(PUBLIC_KEY, publicKey);
        result.put(PRIVATE_KEY, privateKey);
        return result;
    }

    /**
     * 私钥加密
     *
     * @param message
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     */
    public static byte[] encryptByPrivateKey(byte[] message, byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {

        return encrypt(message, translatePrivateKey(privateKey));
    }

    /**
     * 公钥加密
     *
     * @param message
     * @param publicKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     */
    public static byte[] encryptByPublicKey(byte[] message, byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        return encrypt(message, translatePublicKey(publicKey));
    }

    /**
     * 私钥解密
     *
     * @param message
     * @param privateKey
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     */
    public static byte[] decryptByPrivateKey(byte[] message, byte[] privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        return decrypt(message, translatePrivateKey(privateKey));
    }

    /**
     * 公钥解密
     *
     * @param message
     * @param publicKey
     * @return
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchPaddingException
     */
    public static byte[] decryptByPublicKey(byte[] message, byte[] publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        return decrypt(message, translatePublicKey(publicKey));
    }

    /**
     * 转化私钥
     *
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public static PrivateKey translatePrivateKey(byte[] privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey1 = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
        return privateKey1;
    }

    /**
     * 转换公钥
     *
     * @param publicKey
     * @return
     */
    public static PublicKey translatePublicKey(byte[] publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey1 = keyFactory.generatePublic(x509EncodedKeySpec);
        return publicKey1;
    }


    /**
     * 使用公钥/私钥进行加密
     *
     * @param message 待加密信息
     * @param key     公钥/私钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] encrypt(byte[] message, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(message);
    }

    /**
     * 使用公钥/私钥进行解密
     *
     * @param message 待解密信息
     * @param key     公钥/私钥
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static byte[] decrypt(byte[] message, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(key.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(message);
    }

    /**
     * 获取公钥
     *
     * @return
     */
    public static byte[] getPublicKey(Map<String, Key> keyMap) {
        Key publicKey = (Key) keyMap.get(PUBLIC_KEY);
        if (publicKey != null) {
            return publicKey.getEncoded();
        }
        return null;
    }

    /**
     * 获取私钥
     *
     * @return
     */
    public static byte[] getPrivateKey(Map<String, Key> keyMap) {
        Key privateKey = (Key) keyMap.get(PRIVATE_KEY);
        if (privateKey != null) {
            return privateKey.getEncoded();
        }
        return null;
    }
}
