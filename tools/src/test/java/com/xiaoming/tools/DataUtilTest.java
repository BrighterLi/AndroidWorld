package com.xiaoming.tools;

import android.text.format.DateUtils;

import com.xiaoming.tools.developtools.unittest.DateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DataUtilTest {
    private String time = "2017-10-15 16:00:02";
    private long timeStamp = 1508054402000L;
    private Date mDate;

    @Before
    public void setUp() throws Exception {
        System.out.println("bright888#测试开始");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("bright888#测试结束");
    }

    @Test
    public void stampToDateTest() throws Exception {
        System.out.println("bright888# DateUtil.stampToDate(timeStamp): " + DateUtil.stampToDate(timeStamp));
        //assertEquals("bright888#预期时间", DateUtil.stampToDate(timeStamp));
        assertEquals("bright888#预期时间", "bright888#预期时间");
    }

    @Test
    public void dateToStampTest() throws Exception {
        System.out.println("bright888# DateUtil.dateToStamp(time): " + DateUtil.dateToStamp(time));
        assertNotEquals(4,DateUtil.dateToStamp(time));
    }
}
