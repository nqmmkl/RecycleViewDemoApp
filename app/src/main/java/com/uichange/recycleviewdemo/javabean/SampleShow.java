package com.uichange.recycleviewdemo.javabean;

import java.io.Serializable;

/**
 * Created by jiankai.wang on 2017/2/15.
 */
public class SampleShow implements Serializable{


    private int resId;
    private String textShow;

    public SampleShow() {
    }

    public SampleShow(int resId, String textShow) {
        this.resId = resId;
        this.textShow = textShow;
    }

    public String getTextShow() {
        return textShow;
    }

    public void setTextShow(String textShow) {
        this.textShow = textShow;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

}
