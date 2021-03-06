package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.uichange.recycleviewdemo.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

public class Linear_H_Activity extends TopBaseActivity {
    private RecyclerView linear_h_recyclrView;
    private List<SampleShow> linear_h_list;
    private PhoneUtils phoneUtils;
    private Linear_H_Adapter adapter;
    private ImageView show_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_linear__h_);

        phoneUtils=new PhoneUtils(Linear_H_Activity.this);

        initLists();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        linear_h_recyclrView.setLayoutManager(linearLayoutManager);
        linear_h_recyclrView.addItemDecoration(new ItemDecor_R(this,LinearLayoutManager.HORIZONTAL));

        linear_h_recyclrView.setItemAnimator(new DefaultItemAnimator());

        adapter=new Linear_H_Adapter();
        linear_h_recyclrView.setAdapter(adapter);

    }

    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return "横向布局";
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view =layoutInflater.inflate(R.layout.activity_linear__h_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        linear_h_recyclrView= (RecyclerView) view.findViewById(R.id.linear_h_recyclrView);
        show_img= (ImageView) view.findViewById(R.id.show_img);
    }


    private void initLists() {
        linear_h_list=new ArrayList<>();

        for(int i=0;i<5;i++){
            SampleShow sample0=new SampleShow(R.drawable.buchiyu,"不吃鱼");
            linear_h_list.add(sample0);

            SampleShow sample1=new SampleShow(R.drawable.chunlv,"蠢驴");
            linear_h_list.add(sample1);

            SampleShow sample2=new SampleShow(R.drawable.everydaylove,"每天都在谈恋爱");
            linear_h_list.add(sample2);

            SampleShow sample3=new SampleShow(R.drawable.jiuyue,"九月");
            linear_h_list.add(sample3);

            SampleShow sample4=new SampleShow(R.drawable.jxiansen,"j先生");
            linear_h_list.add(sample4);

            SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,"旅行者");
            linear_h_list.add(sample5);
        }
    }

    public class Linear_H_Adapter extends RecyclerView.Adapter<Linear_H_Adapter.MyViewHolder>{

        public Linear_H_Adapter() {
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_h_layout,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            final SampleShow sampleShow=linear_h_list.get(position);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(phoneUtils.get720WScale(200), ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin=phoneUtils.get720WScale(30);
//            params.gravity= Gravity.CENTER;  //此处相当于布局文件中的Android:layout_gravity属性
            holder.linear_h_text.setLayoutParams(params);

            holder.linear_h_text.setGravity(Gravity.CENTER); //此处相当于布局文件中的Android：gravity属性
            holder.linear_h_text.setTextSize(phoneUtils.px2sp(phoneUtils.get720WScale(30)));

            holder.linear_h_img.setImageResource(sampleShow.getResId());
            holder.linear_h_text.setText(sampleShow.getTextShow());

            holder.linear_h_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    addData(position);
                    show_img.setImageResource(sampleShow.getResId());
                }
            });

            holder.linear_h_img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    removeData(position);
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return linear_h_list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            LinearLayout linear_h_ll;
            ImageView linear_h_img;
            TextView linear_h_text;

            public MyViewHolder(View itemView) {
                super(itemView);
                linear_h_ll= (LinearLayout) itemView.findViewById(R.id.linear_h_ll);
                linear_h_img= (ImageView) itemView.findViewById(R.id.linear_h_img);
                linear_h_text= (TextView) itemView.findViewById(R.id.linear_h_text);
            }
        }
    }

    public void addData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        linear_h_list.add(position,sample);
        adapter.notifyItemInserted(position);
        adapter.notifyItemRangeChanged(position,linear_h_list.size());
    }

    public void removeData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        linear_h_list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,linear_h_list.size());

    }
}
