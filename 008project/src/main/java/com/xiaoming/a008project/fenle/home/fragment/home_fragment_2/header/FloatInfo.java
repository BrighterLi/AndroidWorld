package com.xiaoming.a008project.fenle.home.fragment.home_fragment_2.header;

import org.json.JSONObject;

/**
 * 点击弹框信息
 */
public class FloatInfo {

    public static final String JDD = "jdd";
    public static final String QUOTA_REPAY = "quotaRepay";
    public static final String OVERDUE = "overdue";
    public static final String MONEY_CARD = "moneyCard";

    /**
     * 浮层类型 （jdd：借得到 quotaRepay：额度和账单 overdue：严重逾期）
     */
    public String type;
    public String stat;


    public static FloatInfo fromJson(JSONObject o) {
        if (o == null) {
            return null;
        }
        FloatInfo info = new FloatInfo();
        info.type = o.optString("type");
        info.stat = o.optString("stat");

        return info;
    }


    public boolean isQuota() {
        return QUOTA_REPAY.equals(type);
    }

}

