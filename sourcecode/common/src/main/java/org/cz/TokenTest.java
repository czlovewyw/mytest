package org.cz;

import com.hikvision.hik.crypt.Authentication;
import com.hikvision.hik.crypt.Cloader;
import com.hikvision.hik.crypt.CryptErrorException;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class TokenTest {
    public static void main(String[] args) throws InvalidKeySpecException, NoSuchMethodException, NoSuchAlgorithmException, CryptErrorException, IOException {
        Cloader.loaderInitlize("Rsh@18969188291!", "hikvision@(rsh)",   50000, 16, "PBKDF2WithHmacSHA256");
        // 申请身份(不带有数据签名)
        byte[] identityData = Authentication.identifyApplyEx(null);
// identityData为身份认证信息

        // 认证身份（带有时间戳）
        String resultStr = Authentication.identifyCheckEx(identityData, null, 5);
        System.out.println(resultStr);
    }
}
