package com.uichange.recycleviewdemo.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by jiankai.wang on 2017/2/16.
 */
public class PhoneUtils {

    //---------------------------------------------------------
    private Context context;

    public PhoneUtils(Context c) {
        this.context = c;
    }

    public static PhoneUtils mphoneUtils;

    public static PhoneUtils getInstance(Context c){
        if(mphoneUtils==null){
            mphoneUtils=new PhoneUtils(c);
        }
        return mphoneUtils;
    }


    //-----------------------------------------------------------
    private DisplayMetrics displayMetrics;


    public DisplayMetrics getPhoneScreen() {
        if (displayMetrics == null) {
            displayMetrics = new DisplayMetrics();
            WindowManager WM = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            WM.getDefaultDisplay().getMetrics(displayMetrics);
        }
        return displayMetrics;
    }


    //---------------------------------------------------------------
    public float getWScale(int w) {
        int widthPhone;
        float mscale_width = 0;
        widthPhone = getPhoneScreen().widthPixels;
        mscale_width = widthPhone * 1.0f / w;
        return mscale_width;
    }

    //----------------------------------------------------------------
    public int get720WScale(int w) {
        return (int) (w * getWScale(720));
    }

    //-----------------------------------------------------------------
    public int px2sp(float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5f);
    }




}
