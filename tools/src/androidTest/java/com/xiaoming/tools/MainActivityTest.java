package com.xiaoming.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import org.junit.Before;
import org.junit.Test;

import androidx.test.InstrumentationRegistry;

import static junit.framework.TestCase.assertEquals;

public class MainActivityTest {

    private Context mTargetContext;

    @Before
    public void setUp() throws Exception {
        mTargetContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void onCreate() {
        try {
            ApplicationInfo applicationInfo = mTargetContext.getPackageManager().getApplicationInfo(mTargetContext.getPackageName(), PackageManager.GET_META_DATA);
            Bundle metaData = applicationInfo.metaData;
            String data = metaData.getString("com.xiaoying.TEST");

            assertEquals("1234567890", data);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

}
