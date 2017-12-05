package vision.apollo;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vision.apollo.encrypt.RSABuilder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class TokenUtils {

    private static Logger log = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 生成token
     *
     * @param userName
     * @return
     */
    public static String createTokenStr(String userName) throws IOException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        Map<String, Key> keyMap = RSABuilder.initKeyPair();
        SimpleToken simpleToken = new SimpleToken(userName, System.currentTimeMillis());
        byte[] token = JSON.toJSONString(simpleToken).getBytes("utf-8");
        BufferedInputStream inputReader = new BufferedInputStream(new ByteArrayInputStream(token));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(out);
        outputStream.write(RSABuilder.getPublicKey(keyMap));
        byte[] tmp = new byte[245];
        int templ;
        byte[] privateKey = RSABuilder.getPrivateKey(keyMap);
        while ((templ = inputReader.read(tmp)) != -1) {
            byte[] newArr = null;
            byte[] encText = null;
            if (tmp.length == templ) {
                newArr = tmp;
            } else {
                newArr = new byte[templ];
                for (int i = 0; i < templ; i++) {
                    newArr[i] = tmp[i];
                }
            }
            encText = RSABuilder.encryptByPrivateKey(newArr, privateKey);
            outputStream.write(encText);
        }
        outputStream.flush();
        return Base64.encodeBase64String(out.toByteArray());
    }

    /**
     * 验证token是否有效
     *
     * @param token
     * @param expire
     * @return
     * @throws IOException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    public static boolean validateTokenStr(String token, int expire) throws IOException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        SimpleToken simpleToken = null;
        try {
            simpleToken = parseToken(token);
        } catch (Exception e) {
            log.error("token校验失败", e);
            return false;
        }
        long now = System.currentTimeMillis();
        if (simpleToken.getTimeStamp() + expire * 60 * 1000 < now) {
            log.error("token过期");
            return false;
        }
        return true;
    }

    /**
     * 解析token
     *
     * @param token
     * @return
     * @throws IOException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws IllegalBlockSizeException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidKeySpecException
     */
    private static SimpleToken parseToken(String token) throws IOException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        byte[] tmp = Base64.decodeBase64(token);
        BufferedInputStream inputReader = new BufferedInputStream(new ByteArrayInputStream(tmp));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        BufferedOutputStream outputStream = new BufferedOutputStream(out);
        byte[] publicKey = new byte[294];
        byte[] buf = new byte[256];
        inputReader.read(publicKey);
        int bufl;
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
            encText = RSABuilder.decryptByPublicKey(newArr, publicKey);
            outputStream.write(encText);
        }
        outputStream.flush();
        return JSON.parseObject(out.toString("utf-8"), SimpleToken.class);
    }

    public static void main(String[] args) throws BadPaddingException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
//        System.out.println(validateTokenStr(createTokenStr("chenzhe"), 10));
//        System.out.println(validateTokenStr("sadsad",14));
//        long s1 = System.currentTimeMillis();
//        String token = createTokenStr("chenzhe");
//        long s2 = System.currentTimeMillis();
//        validateTokenStr(token, 10);
//        long s3 = System.currentTimeMillis();
//        System.out.println("生成token:" + (s2 - s1));
//        System.out.println("解密token:" + (s3 - s2));
        System.out.println(createTokenStr("chenzhe"));
    }
}
