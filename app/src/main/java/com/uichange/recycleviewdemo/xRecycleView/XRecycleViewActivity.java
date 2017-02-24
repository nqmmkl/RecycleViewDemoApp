package com.uichange.recycleviewdemo.xRecycleView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.utils.ActivityUtils;

public class XRecycleViewActivity extends TopBaseActivity {
    private Button xbtn_v_listview,xbtn_gridview;

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
        initView(view);
        return view;
    }



    private void initView(View view){
        xbtn_v_listview= (Button) view.findViewById(R.id.xbtn_v_listview);
        xbtn_gridview= (Button) view.findViewById(R.id.xbtn_gridview);

        xbtn_v_listview.setOnClickListener(new MyonClick());
        xbtn_gridview.setOnClickListener(new MyonClick());

    }





    private class MyonClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.xbtn_v_listview:
//                    ActivityUtils.startActivity_scale(XRecycleViewActivity.this,XLinear_V_Activity.class);
                    Intent intent=new Intent(XRecycleViewActivity.this,XLinear_V_Activity.class);
                    intent.putExtra("xRecycleView","list");
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_center, R.anim.out_to_bg);
                    break;
                case R.id.xbtn_gridview:
                    Intent intent1=new Intent(XRecycleViewActivity.this,XLinear_V_Activity.class);
                    intent1.putExtra("xRecycleView","grid");
                    startActivity(intent1);
                    overridePendingTransition(R.anim.in_from_center, R.anim.out_to_bg);

                    break;
            }

        }
    }





    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.finishActivity_scale(XRecycleViewActivity.this);
    }
}
