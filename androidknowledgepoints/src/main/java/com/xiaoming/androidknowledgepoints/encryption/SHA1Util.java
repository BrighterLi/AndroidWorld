package com.xiaoming.androidknowledgepoints.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {

    public static void sha1Encrypt(String[] args) throws NoSuchAlgorithmException {

        String plainText = "this is plain Text";

        // 获取指定摘要算法的messageDigest对象
        MessageDigest messageDigest = MessageDigest.getInstance("SHA"); // 此处的sha代表sha1

        // 调用digest方法，进行加密操作
        byte[] cipherBytes = messageDigest.digest(plainText.getBytes());

        //String cipherStr = Hex.encodeHexString(cipherBytes);

        //System.out.println(cipherStr);

    }
}
