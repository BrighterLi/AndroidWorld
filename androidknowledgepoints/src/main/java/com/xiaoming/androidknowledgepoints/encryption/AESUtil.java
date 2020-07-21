package com.xiaoming.androidknowledgepoints.encryption;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//方式jni实现
//AES流程与机制

//安卓客户端与服务端对称加密传输demohttps://www.cnblogs.com/kuangdw/p/12933449.html
public class AESUtil {
    /*
     * 加密
     * 1.构造密钥生成器
     * 6.返回字符串
     */
    private static final String IV_STRING = "16-Bytes--String"; //这里限制了加密规则只能使16个字符长的字符串

    public  static String aESEncode(String encodeRules,String content) throws InvalidAlgorithmParameterException {
        try {

            byte[] keyFormat = encodeRules.getBytes("UTF-8");
            byte[] contentFormat = content.getBytes("UTF-8");
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyFormat,"AES");
            byte[] initParam = IV_STRING.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            //初始化密码器
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec,ivParameterSpec);

            byte [] byte_AES=cipher.doFinal(contentFormat);

            return parseByte2HexStr(byte_AES);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //如果有错就大胆抛空
        return null;
    }
    /*
     * 解密
     * 过程和加密基本类似
     */
    public static String aESDncode(String encodeRules,String content) throws InvalidAlgorithmParameterException {
        try {
            byte[] keyFormat = encodeRules.getBytes("UTF-8");
            byte[] contentFormat = parseHexStr2Byte(content);
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyFormat,"AES");
            byte[] initParam = IV_STRING.getBytes("UTF-8");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            Cipher cipher=Cipher.getInstance("AES/CBC/PKCS5Padding");
            //初始化密码器
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,ivParameterSpec);
            byte [] byte_AES=cipher.doFinal(contentFormat);
            return new String(byte_AES);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        //如果有错就大胆抛空
        return null;
    }


    /**
     * 将二进制转换成16进制
     * @method parseByte2HexStr
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte buf[]){
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < buf.length; i++){
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将16进制转换为二进制
     * @method parseHexStr2Byte
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr){
        if(hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

}
