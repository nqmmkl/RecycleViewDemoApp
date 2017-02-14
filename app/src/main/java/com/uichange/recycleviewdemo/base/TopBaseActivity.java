package com.uichange.recycleviewdemo.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.uichange.recycleviewdemo.R;

public abstract class TopBaseActivity extends FragmentActivity {

    private LinearLayout top_base_layout_ll;

    private RelativeLayout top_base_layout_rl;
    private LinearLayout top_base_left_ll;
    private ImageView top_base_leftImg;
    private TextView top_base_leftText;
    private TextView top_base_centerText;
    private ImageView top_base_rightImg;

    private String leftText,centerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_base);

        initView();
        initData();
    }

    private void initView() {
        top_base_layout_ll= (LinearLayout) findViewById(R.id.top_base_layout_ll);

        top_base_layout_rl= (RelativeLayout) findViewById(R.id.top_base_layout_rl);
        top_base_left_ll= (LinearLayout) findViewById(R.id.top_base_left_ll);
        top_base_leftImg= (ImageView) findViewById(R.id.top_base_leftImg);
        top_base_leftText= (TextView) findViewById(R.id.top_base_leftText);
        top_base_centerText= (TextView) findViewById(R.id.top_base_centerText);
        top_base_rightImg= (ImageView) findViewById(R.id.top_base_rightImg);
    }



    private View childView;
    private void initData() {


        leftText=setLeftText();
        top_base_leftText.setText(leftText);

        centerText=setCenterText();
        top_base_centerText.setText(centerText);

        childView=setChildView(getLayoutInflater());
        if(childView!=null){
            top_base_layout_ll.addView(childView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }

        top_base_left_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public abstract String setLeftText();
    public abstract String setCenterText();
    public abstract View setChildView(LayoutInflater layoutInflater);

    public LinearLayout getTop_base_left_ll(){
        return top_base_left_ll;
    }
    public ImageView getTop_base_leftImg(){
        return top_base_leftImg;
    }
    public ImageView getTop_base_rightImg(){
        return top_base_rightImg;
    }



}
