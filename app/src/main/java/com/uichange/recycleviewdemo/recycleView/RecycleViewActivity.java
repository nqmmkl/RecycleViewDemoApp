package com.uichange.recycleviewdemo.recycleView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.utils.ActivityUtils;

public class RecycleViewActivity extends TopBaseActivity {
    private Button btn_v_listview,btn_h_listview,btn_gridview,btn_staggered,btn_staggered_volley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycle_view_test);


        getTop_base_left_ll().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityUtils.finishActivity(RecycleViewActivity.this);
            }
        });
    }

    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return "RecycleView_Test";
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(R.layout.activity_recycle_view_test,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        btn_v_listview= (Button) view.findViewById(R.id.btn_v_listview);
        btn_h_listview= (Button) view.findViewById(R.id.btn_h_listview);
        btn_gridview= (Button) view.findViewById(R.id.btn_gridview);
        btn_staggered= (Button) view.findViewById(R.id.btn_staggered);
        btn_staggered_volley= (Button) view.findViewById(R.id.btn_staggered_volley);

        btn_v_listview.setOnClickListener(new Btn_clickLis());
        btn_h_listview.setOnClickListener(new Btn_clickLis());
        btn_gridview.setOnClickListener(new Btn_clickLis());
        btn_staggered.setOnClickListener(new Btn_clickLis());
        btn_staggered_volley.setOnClickListener(new Btn_clickLis());
    }

    private class Btn_clickLis implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_v_listview:
                    ActivityUtils.startActivity(RecycleViewActivity.this,Linear_V_Activity.class);
                    break;
                case R.id.btn_h_listview:
                    ActivityUtils.startActivity(RecycleViewActivity.this,Linear_H_Activity.class);
                    break;
                case R.id.btn_gridview:
                    ActivityUtils.startActivity(RecycleViewActivity.this,Grid_Activity.class);
                    break;
                case R.id.btn_staggered:
//                    ActivityUtils.startActivity(RecycleViewActivity.this,Staggered_Activity.class);
                    Intent intent=new Intent(RecycleViewActivity.this,Staggered_Activity.class);
                    intent.putExtra("Staggered_flag",1);    //1-本地图片
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    break;
                case R.id.btn_staggered_volley:
//                    ActivityUtils.startActivity(RecycleViewActivity.this,Staggered_Activity.class);
                    Intent intent0=new Intent(RecycleViewActivity.this,Staggered_Activity.class);
                    intent0.putExtra("Staggered_flag",0);    //0-网络请求
                    startActivity(intent0);
                    overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
                    break;
            }
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityUtils.finishActivity(RecycleViewActivity.this);
    }
}
