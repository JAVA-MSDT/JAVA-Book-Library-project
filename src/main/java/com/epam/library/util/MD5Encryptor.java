package com.epam.library.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor {
    private final static String ALGORITHM = "MD5";
    private static MessageDigest messageDigest;

    public MD5Encryptor(){
      init();
    }

    private void init(){
        try {
            messageDigest = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String convert(String st){
        byte[] bytes = st.getBytes();
        messageDigest.reset();
        byte[] digested = messageDigest.digest(bytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : digested) {
            sb.append(Integer.toHexString(0xff & b));
        }
        return sb.toString();
    }
}
