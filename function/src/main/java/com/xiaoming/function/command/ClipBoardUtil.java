package com.xiaoming.function.command;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import androidx.annotation.Nullable;
import android.util.Log;

import static android.content.Context.CLIPBOARD_SERVICE;

public class ClipBoardUtil {
    @Nullable
    public static String getCurrentClipboardText(Context context) {
        try {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
            //无数据时直接返回
            ClipData clipData = cm.getPrimaryClip();
            if (clipData == null) {
                return null;
            }
            ClipDescription description = clipData.getDescription();
            //如果是文本信息
            if (description != null && description.hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                if (clipData.getItemCount() > 0) {
                    ClipData.Item item = clipData.getItemAt(0);
                    //此处是TEXT文本信息
                    if (item != null && item.getText() != null) {
                        return item.getText().toString();
                    }
                }
            }
        } catch (Throwable e) {
            Log.e("", "", e);
        }
        return null;
    }


    public static boolean clearCurrentClipboard(Context context) {
        try {
            ClipboardManager cm = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
            ClipData myClip = ClipData.newPlainText("combination_to_left_righht", "");
            cm.setPrimaryClip(myClip);
            return true;
        } catch (Throwable e) {
            Log.e("", "", e);
        }
        return false;
    }

    private void setClipboard(Context context) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", "￥AzZK0hPyou5￥");
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }
}
