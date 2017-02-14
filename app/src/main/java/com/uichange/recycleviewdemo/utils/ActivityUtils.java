package com.uichange.recycleviewdemo.utils;

import android.app.Activity;
import android.content.Intent;

import com.uichange.recycleviewdemo.R;

/**
 * Created by jiankai.wang on 2017/2/14.
 */
public class ActivityUtils {

    public static void startActivity(Activity activity,Class<?> c){
        activity.startActivity(new Intent(activity,c));
        activity.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    public static void finishActivity(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }

    public static void startActivity_scale(Activity activity,Class<?> c){
        activity.startActivity(new Intent(activity,c));
        activity.overridePendingTransition(R.anim.in_from_center, R.anim.out_to_bg);
    }

    public static void finishActivity_scale(Activity activity){
        activity.finish();
        activity.overridePendingTransition(R.anim.in_from_bg, R.anim.out_to_center);
    }


}
