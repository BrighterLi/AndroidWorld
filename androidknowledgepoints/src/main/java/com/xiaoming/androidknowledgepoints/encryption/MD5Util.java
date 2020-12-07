package com.xiaoming.androidknowledgepoints.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

    public static void md5Encryp(String[] args) throws NoSuchAlgorithmException {
        String plainText = "this is plain text";

        // 通过调用MessageDigest（数据摘要类）的getInstance()静态方法，传入加密算法的名称，获取数据摘要对象。
        //MessageDigest MessageDigest.getInstance(algorithm);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");

        // 获取摘要（加密），结果是字节数组
        // byte[] java.security.MessageDigest.digest(byte[] input)
        byte[] ciphertext = messageDigest.digest(plainText.getBytes());

        // 利用apache的commons-codec，将字节数组转换为十六进制。
        //System.out.println(Hex.encodeHexString(ciphertext));
    }
}
