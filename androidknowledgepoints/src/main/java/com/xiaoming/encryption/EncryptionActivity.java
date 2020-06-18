package com.xiaoming.encryption;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xiaoming.androidknowledgepoints.R;

import java.security.InvalidAlgorithmParameterException;

//对称加密：AES
//非对称加密
//可逆加密

public class EncryptionActivity extends AppCompatActivity {

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

    private void aESEncodeAnddecode() throws InvalidAlgorithmParameterException {
        String encodeRules="thisisencoderule";
        System.out.println("bright#加密规则为：" + encodeRules);
        String content = "一头小菜鸡的账号和密码";
        System.out.println("bright#加密的内容为:"+content);
        String keyContent = AESUtil.aESEncode(encodeRules,content);
        System.out.println("bright#加密后的内容为：" + keyContent);

        System.out.println("bright#解密后的明文为："+ AESUtil.aESDncode(encodeRules,keyContent));
    }
}
