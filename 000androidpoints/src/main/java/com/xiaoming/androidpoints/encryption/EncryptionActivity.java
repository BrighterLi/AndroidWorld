package com.xiaoming.androidpoints.encryption;

import android.app.Activity;
import android.os.Bundle;

import com.xiaoming.androidpoints.R;

import java.security.InvalidAlgorithmParameterException;

//对称加密：AES
//非对称加密
//可逆加密

public class EncryptionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        try {
            aESEncodeAnddecode();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    //AES加解密
    private void aESEncodeAnddecode() throws InvalidAlgorithmParameterException {
        String encodeRules="thisisencoderule"; //加密规则
        System.out.println("bright#加密规则为：" + encodeRules);
        String content = "一头小菜鸡的账号和密码";
        System.out.println("bright#加密的内容为:"+content);
        //加密
        String keyContent = AESUtil.aESEncode(encodeRules,content);
        System.out.println("bright#加密后的内容为：" + keyContent);
        //解密
        System.out.println("bright#解密后的明文为："+ AESUtil.aESDncode(encodeRules,keyContent));
    }
}
