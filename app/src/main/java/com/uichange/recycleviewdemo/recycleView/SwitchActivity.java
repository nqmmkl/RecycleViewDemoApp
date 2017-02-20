package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_grid;

import java.util.ArrayList;
import java.util.List;

public class SwitchActivity extends TopBaseActivity {
    private RecyclerView recycle_view;
    private List<SampleShow> lists;
    private MyAdapter adapter;
    private static int flag=0;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    if(flag==0){
                        GridLayoutManager layoutManager=new GridLayoutManager(SwitchActivity.this,2);
                        recycle_view.setLayoutManager(layoutManager);
//                        recycle_view.addItemDecoration(new ItemDecor_grid(SwitchActivity.this));

                        recycle_view.setAdapter(adapter);
                        flag=1;
                    }else if(flag==1){
                        GridLayoutManager layoutManager=new GridLayoutManager(SwitchActivity.this,3);
                        recycle_view.setLayoutManager(layoutManager);
//                        recycle_view.addItemDecoration(new ItemDecor_grid(SwitchActivity.this));

                        recycle_view.setAdapter(adapter);
                        flag=2;
                    }else {
                        LinearLayoutManager manager=new LinearLayoutManager(SwitchActivity.this);
                        manager.setOrientation(LinearLayoutManager.VERTICAL);
                        recycle_view.setLayoutManager(manager);
//                        recycle_view.addItemDecoration(new ItemDecor_grid(SwitchActivity.this));
                        recycle_view.setAdapter(adapter);
                        flag=0;
                    }


                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_switch);

        initLists();
        adapter=new MyAdapter();
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycle_view.setLayoutManager(manager);
        recycle_view.addItemDecoration(new ItemDecor_grid(SwitchActivity.this));
        recycle_view.setAdapter(adapter);


        getTop_base_rightImg().setVisibility(View.VISIBLE);
        getTop_base_rightImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(1000);
            }
        });

    }

    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return "布局切换";
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(R.layout.activity_switch,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        recycle_view= (RecyclerView) view.findViewById(R.id.recycle_view);

    }

    private void initLists() {
        lists=new ArrayList<>();

        for(int i=0;i<8;i++){
            SampleShow sample0=new SampleShow(R.drawable.buchiyu,"不吃鱼");
            lists.add(sample0);

            SampleShow sample1=new SampleShow(R.drawable.chunlv,"蠢驴");
            lists.add(sample1);

            SampleShow sample2=new SampleShow(R.drawable.everydaylove,"每天都在谈恋爱");
            lists.add(sample2);

            SampleShow sample3=new SampleShow(R.drawable.jiuyue,"九月");
            lists.add(sample3);

            SampleShow sample4=new SampleShow(R.drawable.jxiansen,"j先生");
            lists.add(sample4);

            SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,"旅行者");
            lists.add(sample5);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_v_layout,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            SampleShow sampleShow=lists.get(position);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.linear_v_ll.setLayoutParams(params);

            holder.linear_v_img.setImageResource(sampleShow.getResId());
            holder.linear_v_text.setText(sampleShow.getTextShow());

        }

        @Override
        public int getItemCount() {
            return lists.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            LinearLayout linear_v_ll;
            ImageView linear_v_img;
            TextView linear_v_text;

            public MyViewHolder(View itemView) {
                super(itemView);
                linear_v_ll= (LinearLayout) itemView.findViewById(R.id.linear_v_ll);
                linear_v_img= (ImageView) itemView.findViewById(R.id.linear_v_img);
                linear_v_text= (TextView) itemView.findViewById(R.id.linear_v_text);
            }
        }
    }
}
