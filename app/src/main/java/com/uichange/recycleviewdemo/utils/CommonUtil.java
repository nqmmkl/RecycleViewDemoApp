package com.uichange.recycleviewdemo.utils;

import java.util.Random;

/**
 * Created by jiankai.wang on 2017/2/15.
 */
public class CommonUtil {
    public static String getRandomLengthName(String name){
        Random random=new Random();
        int length=random.nextInt(20)+1;
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append(name);
        }

        return builder.toString();
    }
}
