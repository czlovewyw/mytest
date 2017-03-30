package org.leochen.samples.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * User: leochen
 * Date: 11-12-5
 * Time: 下午1:36
 */
public class IPTokenBasedRememberMeService extends TokenBasedRememberMeServices {
    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();


    public HttpServletRequest getContext(){
        return requestHolder.get();
    }

    public void setContext(HttpServletRequest request){
        requestHolder.set(request);
    }

    protected String getUserIPAddress(HttpServletRequest request){
        return request.getRemoteAddr();
    }

    @Override
    public void onLoginSuccess(HttpServletRequest request, HttpServletResponse response, Authentication successfulAuthentication) {
        try{
            setContext(request);
            super.onLoginSuccess(request, response, successfulAuthentication);
        }finally {
            setContext(null);
        }
    }

    @Override
    protected String makeTokenSignature(long tokenExpiryTime, String username, String password) {
        StringBuilder sb=new StringBuilder();
        sb.append(username).append(":").append(tokenExpiryTime).append(":")
                .append(password).append(":").append(getKey()).append(":")
                .append(getUserIPAddress(getContext()));

        byte[] b = sb.toString().getBytes();

        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(b)));

    }


    @Override
    protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        // append IP address to the cookie
        String[] tokensWithIpAddress= Arrays.copyOf(tokens,tokens.length+1);
        tokensWithIpAddress[tokensWithIpAddress.length-1]=getUserIPAddress(request);
        super.setCookie(tokensWithIpAddress, maxAge, request, response);
    }


    @Override
    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {
        try{
            setContext(request);
            String ipAddressToken=cookieTokens[cookieTokens.length-1];
            // check IP address first, and then check cookieTokens
            if(!getUserIPAddress(request).equals(ipAddressToken)){
                throw new InvalidCookieException("Cookie IP address did not contain a matching IP('"+ipAddressToken+"')");
            }
            return super.processAutoLoginCookie(Arrays.copyOf(cookieTokens,cookieTokens.length-1), request, response);
        }finally {
            setContext(null);
        }

    }
}
