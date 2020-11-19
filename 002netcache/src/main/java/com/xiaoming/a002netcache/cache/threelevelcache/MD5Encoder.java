package com.xiaoming.a002netcache.cache.threelevelcache;

import java.security.MessageDigest;


//MD5加密
class MD5Encoder {


    public static String encode(String string) throws Exception {
        String MD5 = "MD5";
        String UTF_8 = "UTF-8";
        byte[] hash = MessageDigest.getInstance(MD5).digest(string.getBytes(UTF_8));
        StringBuffer hex = new StringBuffer(hash.length * 2);
        for(byte b:hash){
            if((b & 0xFF)<0x10){
                hex.append("0");
            }
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }
}
