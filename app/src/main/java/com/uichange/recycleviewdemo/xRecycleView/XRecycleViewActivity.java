package com.uichange.recycleviewdemo.xRecycleView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.utils.ActivityUtils;

public class XRecycleViewActivity extends TopBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xrecycle_view);

        getTop_base_left_ll().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.finishActivity_scale(XRecycleViewActivity.this);
            }
        });
    }

    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return null;
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(R.layout.activity_xrecycle_view,null);
        return view;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.finishActivity_scale(XRecycleViewActivity.this);
    }
}
