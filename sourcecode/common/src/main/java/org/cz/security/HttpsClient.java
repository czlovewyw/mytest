package org.cz.security;

import com.sun.net.httpserver.HttpsConfigurator;

import javax.net.ssl.*;
import java.io.DataInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;

/**
 * 采用https和服务器端通信
 *
 * Created by chenzhe8 on 2017/6/21.
 */
public class HttpsClient {
    public static final String DEFAULT_STOREPASS = "88075998";
    public static final String DEFAULT_PROTOCOL = "TLS";
    public static void main(String[] args) throws Exception {

        String httpsUrl = "https://cz.com:8443/index.jsp";

        //初始化密钥库管理工厂
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        KeyStore keyStore = ManageKeystore.getKeystore("d:\\tomcat.keystore",DEFAULT_STOREPASS);
        keyManagerFactory.init(keyStore,DEFAULT_STOREPASS.toCharArray());

        //初始化信任库管理工厂
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyStore trustStore = ManageKeystore.getKeystore("d:\\tomcat.keystore",DEFAULT_STOREPASS);
        trustManagerFactory.init(trustStore);

        //初始化ssl套接字工厂
        SSLContext sslContext = SSLContext.getInstance(DEFAULT_PROTOCOL);
        sslContext.init(keyManagerFactory.getKeyManagers(),trustManagerFactory.getTrustManagers(),new SecureRandom());
        SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

        URL url = new URL(httpsUrl);
        //建立https连接
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection)url.openConnection();
        httpsURLConnection.setDoInput(true);
        httpsURLConnection.setSSLSocketFactory(sslSocketFactory);

        int length = httpsURLConnection.getContentLength();
        System.out.println("length="+length);
        System.out.println("status="+httpsURLConnection.getResponseCode());
        DataInputStream in = new DataInputStream(httpsURLConnection.getInputStream());
        byte[] data = new byte[1024];
        StringBuffer sb = new StringBuffer();
        while (in.read(data)!=-1){
            sb.append(new String(data,"utf-8"));
        }
        in.close();
        httpsURLConnection.disconnect();
        System.out.println(sb.toString());

    }
}
