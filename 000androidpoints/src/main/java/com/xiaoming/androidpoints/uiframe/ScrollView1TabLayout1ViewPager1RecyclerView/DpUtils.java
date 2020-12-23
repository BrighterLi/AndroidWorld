package com.xiaoming.androidpoints.uiframe.ScrollView1TabLayout1ViewPager1RecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created By HuangQing on 2018/8/2 16:28
 **/
public class DpUtils {
    public static int dp2px(@NonNull final Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
