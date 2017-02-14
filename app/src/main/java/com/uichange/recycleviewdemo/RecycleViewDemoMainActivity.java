package com.uichange.recycleviewdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.recycleView.RecycleViewActivity;
import com.uichange.recycleviewdemo.utils.ActivityUtils;
import com.uichange.recycleviewdemo.xRecycleView.XRecycleViewActivity;

public class RecycleViewDemoMainActivity extends TopBaseActivity {
    private Button btn_RecycleView,btn_xRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycle_view_demo_main);
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
        View view=layoutInflater.inflate(R.layout.activity_recycle_view_demo_main,null);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view){
        btn_RecycleView= (Button) view.findViewById(R.id.btn_RecycleView);
        btn_xRecycleView= (Button) view.findViewById(R.id.btn_xRecycleView);

    }

    private void initData(){
        btn_RecycleView.setOnClickListener(new BtnClickLis());
        btn_xRecycleView.setOnClickListener(new BtnClickLis());
    }

    public class BtnClickLis implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_RecycleView:
                    ActivityUtils.startActivity(RecycleViewDemoMainActivity.this, RecycleViewActivity.class);
                    break;
                case R.id.btn_xRecycleView:
                    ActivityUtils.startActivity_scale(RecycleViewDemoMainActivity.this, XRecycleViewActivity.class);
                    break;

            }

        }
    }
}
