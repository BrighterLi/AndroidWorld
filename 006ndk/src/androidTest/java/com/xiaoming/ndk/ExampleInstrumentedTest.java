package com.xiaoming.ndk;

import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented combination_to_left_righht, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under combination_to_left_righht.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.xiaoming.ndk", appContext.getPackageName());
    }
}
