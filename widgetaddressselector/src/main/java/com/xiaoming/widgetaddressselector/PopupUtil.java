package com.xiaoming.widgetaddressselector;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

//以Dialog形式显示
public class PopupUtil {
    private static final String TAG = "PopupUtil";

    /**
     *
     * @param context
     * @param mType
     * @param province
     * @param city
     * @param area
     * @return
     */
    //自定义dialog 默认的显示位置是window 的位置
    //我们可以通过dialog或者窗口对象 window 然后通过window 去设置dialog的上下中的位置
    public static Dialog showRegionView(Context context, int mType, final String province, final String city, final String area, final OnRegionListener onRegionListener) {
        final Dialog dialog = new Dialog(context, R.style.DialogCommonStyle);
        Window window = dialog.getWindow(); //获得dialog当前窗口window
        window.setGravity(Gravity.BOTTOM); //设置窗口window位置
        window.setContentView(R.layout.layout_address); //设置窗口window内容
        AddressPopupWindow addressPopupWindow = window.findViewById(R.id.regionPpw); //加载AddressPopupWindow
        // 设置历史记录？
        addressPopupWindow.setHistory(mType, province, city, area);
        // 设置右上角叉号监听
        addressPopupWindow.setOnForkClickListener(new AddressPopupWindow.OnForkClickListener() {
            @Override
            public void onForkClick() {
                dialog.dismiss();
            }
        });
        // 设置item监听，回调传回结果，省、市、区点击完才回调
        addressPopupWindow.setOnRpwItemClickListener(new AddressPopupWindow.OnRpwItemClickListener() {
            @Override
            public void onRpwItemClick(String selectedProvince, String selectedCity, String selectedArea) {
                Log.d(TAG, "showRegionView#onRpwItemClick");
                onRegionListener.onRegionListener(selectedProvince, selectedCity, selectedArea);
                dialog.dismiss();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        WindowManager.LayoutParams attributes = window.getAttributes(); // 获取对话框当前的参数值
        attributes.width = PhoneUtil.getScreenPix(context).widthPixels;// 设置对话框宽为手机屏幕宽
        //attributes.height = PhoneUtil.getScreenPix(context).heightPixels * 4/5;// 高为手机屏幕高的4/5
        attributes.height = PhoneUtil.getScreenPix(context).heightPixels / 2;// 设置对话框高为手机屏幕高的1/2
        window.setBackgroundDrawableResource(R.color.white); // 设置对话框dialog的背景
        window.setAttributes(attributes);
        window.setWindowAnimations(R.style.AnimBottom); //设置对话框dialog进出动画
        dialog.show(); //显示对话框dialog
        return dialog;
    }

    public interface OnRegionListener {
        void onRegionListener(String province, String city, String area);
    }
}
