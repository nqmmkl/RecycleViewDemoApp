package com.uichange.recycleviewdemo.bossCustom;

import android.os.Bundle;
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
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_R;

import java.util.ArrayList;
import java.util.List;

public class BossRecycleActivity extends TopBaseActivity {
    private PagingRecycleView pagingRecycleView;
    private List<SampleShow> mlist;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_boss_recycle);
        initLists();

        adapter=new MyAdapter();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        pagingRecycleView.setLayoutManager(layoutManager);
        pagingRecycleView.addItemDecoration(new ItemDecor_R(this,LinearLayoutManager.VERTICAL));  //调用系统分割线
        pagingRecycleView.setAdapter(adapter);




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
        View view =layoutInflater.inflate(R.layout.activity_boss_recycle,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        pagingRecycleView= (PagingRecycleView) view.findViewById(R.id.pagingRecycleView);
    }

    private void initLists() {
        mlist=new ArrayList<>();

        for(int i=0;i<5;i++){
            SampleShow sample0=new SampleShow(R.drawable.buchiyu,"不吃鱼");
            mlist.add(sample0);

            SampleShow sample1=new SampleShow(R.drawable.chunlv,"蠢驴");
            mlist.add(sample1);

            SampleShow sample2=new SampleShow(R.drawable.everydaylove,"每天都在谈恋爱");
            mlist.add(sample2);

            SampleShow sample3=new SampleShow(R.drawable.jiuyue,"九月");
            mlist.add(sample3);

            SampleShow sample4=new SampleShow(R.drawable.jxiansen,"j先生");
            mlist.add(sample4);

            SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,"旅行者");
            mlist.add(sample5);
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
            SampleShow sampleShow=mlist.get(position);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.linear_v_ll.setLayoutParams(params);

            holder.linear_v_img.setImageResource(sampleShow.getResId());
            holder.linear_v_text.setText(sampleShow.getTextShow());
        }

        @Override
        public int getItemCount() {
            return mlist.size();
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
