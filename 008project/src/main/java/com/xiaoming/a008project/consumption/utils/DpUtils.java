package com.xiaoming.a008project.consumption.utils;

import android.content.Context;
import android.support.annotation.NonNull;

public class DpUtils {
    public static int dp2px(@NonNull final Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
