package com.xiaoming.widgetaddressselector;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

//以Dialog形式显示
public class PopupUtil {

    /**
     *
     * @param context
     * @param mType
     * @param province
     * @param city
     * @param area
     * @return
     */
    public static Dialog showRegionView(Context context, int mType, final String province, final String city, final String area, final OnRegionListener onRegionListener) {
        final Dialog dialog = new Dialog(context, R.style.DialogCommonStyle);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setContentView(R.layout.layout_address);
        AddressPopupWindow addressPopupWindow = window.findViewById(R.id.regionPpw);
        // 设置历史记录？
        addressPopupWindow.setHistory(mType, province, city, area);
        // 设置右上角叉号监听
        addressPopupWindow.setOnForkClickListener(new AddressPopupWindow.OnForkClickListener() {
            @Override
            public void onForkClick() {
                dialog.dismiss();
            }
        });
        // 设置item监听，回调传回结果
        addressPopupWindow.setOnRpwItemClickListener(new AddressPopupWindow.OnRpwItemClickListener() {
            @Override
            public void onRpwItemClick(String selectedProvince, String selectedCity, String selectedArea) {
                onRegionListener.onRegionListener(selectedProvince, selectedCity, selectedArea);
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = PhoneUtil.getScreenPix(context).widthPixels;// 宽为手机屏幕宽
        //attributes.height = PhoneUtil.getScreenPix(context).heightPixels * 4/5;// 高为手机屏幕高的4/5
        attributes.height = PhoneUtil.getScreenPix(context).heightPixels / 2;// 高为手机屏幕高的1/2
        window.setBackgroundDrawableResource(R.color.white);
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.AnimBottom);
        dialog.show();
        return dialog;
    }

    public interface OnRegionListener {
        void onRegionListener(String province, String city, String area);
    }
}
