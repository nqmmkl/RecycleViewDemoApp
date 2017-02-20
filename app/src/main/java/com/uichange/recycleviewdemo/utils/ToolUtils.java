package com.uichange.recycleviewdemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jiankai.wang on 2017/2/17.
 */
public class ToolUtils {
    private Context context;
    private static ToolUtils instance;
    public static ToolUtils getInstance(Context context){
        if(instance==null){
            instance=new ToolUtils(context);
        }

        return instance;
    }

    public ToolUtils(Context context) {
        this.context = context;
    }

    private Toast toast;

    public void makeToast(String name,int time){
//        View layout = View.inflate(context, R.layout.toast_tiem, null);
//        textView = (TextView) layout.findViewById(R.id.textcontent);
//        textView.setText(name);
        toast = new Toast(context);
//        toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL , 0, SecretMessageApplication.get720WScale(240));
        toast.setDuration(time);
//        toast.setView(layout);
        toast.show();

    }

    public void cancletoast(){
        if(toast!=null){
            toast.cancel();
        }
    }
}
