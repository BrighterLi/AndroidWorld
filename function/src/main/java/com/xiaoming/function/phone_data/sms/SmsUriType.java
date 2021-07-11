package com.xiaoming.function.phone_data.sms;

import android.net.Uri;

public class SmsUriType {

     //所有的短信

    public static final Uri SMS_URI_ALL = Uri.parse("content://sms/");

    //收件箱短信
    public static final Uri SMS_URI_INBOX = Uri.parse("content://sms/inbox");

   //已发送短信
    public static final Uri SMS_URI_SEND = Uri.parse("content://sms/sent");

   //草稿箱短信
    public static final Uri SMS_URI_DRAFT = Uri.parse("content://sms/draft");

}
