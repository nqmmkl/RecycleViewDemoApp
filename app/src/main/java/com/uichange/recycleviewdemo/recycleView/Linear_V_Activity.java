package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor;
import com.uichange.recycleviewdemo.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

public class Linear_V_Activity extends TopBaseActivity {
    private RecyclerView linear_v_recyclrView;
    private List<SampleShow> linear_v_list;
    private PhoneUtils phoneUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_linear__v_);

        phoneUtils=new PhoneUtils(Linear_V_Activity.this);

        initLists();


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linear_v_recyclrView.setLayoutManager(layoutManager);

        linear_v_recyclrView.addItemDecoration(new ItemDecor());      //自定义画布分割线
//        linear_v_recyclrView.addItemDecoration(new ItemDecor_R(this,LinearLayoutManager.VERTICAL));  //调用系统分割线

        Linear_V_Adapter adapter=new Linear_V_Adapter();

        View headView=LayoutInflater.from(this).inflate(R.layout.recycleview_headerview,null);
        View footerView=LayoutInflater.from(this).inflate(R.layout.recycleview_footerview,null);
        adapter.setmHeaderView(headView);
        linear_v_list.add(0,null);

        adapter.setmFooterView(footerView);
        linear_v_list.add(linear_v_list.size()-1,null);

        linear_v_recyclrView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(Linear_V_Activity.this,"onClick>>>>>"+linear_v_list.get(position).getTextShow(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(Linear_V_Activity.this,"onLongClick>>>>>"+linear_v_list.get(position).getTextShow(),Toast.LENGTH_SHORT).show();
            }
        });


    }



    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return "纵向布局";
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(R.layout.activity_linear__v_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        linear_v_recyclrView= (RecyclerView) view.findViewById(R.id.linear_v_recyclrView);
    }

    private void initLists() {
        linear_v_list=new ArrayList<>();

        for(int i=0;i<5;i++){
            SampleShow sample0=new SampleShow(R.drawable.buchiyu,"不吃鱼");
            linear_v_list.add(sample0);

            SampleShow sample1=new SampleShow(R.drawable.chunlv,"蠢驴");
            linear_v_list.add(sample1);

            SampleShow sample2=new SampleShow(R.drawable.everydaylove,"每天都在谈恋爱");
            linear_v_list.add(sample2);

            SampleShow sample3=new SampleShow(R.drawable.jiuyue,"九月");
            linear_v_list.add(sample3);

            SampleShow sample4=new SampleShow(R.drawable.jxiansen,"j先生");
            linear_v_list.add(sample4);

            SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,"旅行者");
            linear_v_list.add(sample5);
        }
    }


    public class Linear_V_Adapter extends RecyclerView.Adapter<Linear_V_Adapter.MyViewHolder>{
        public static final int TYPE_HEADER = 0;  //说明是带有Header的
        public static final int TYPE_FOOTER = 1;  //说明是带有Footer的
        public static final int TYPE_NORMAL = 2;  //说明是不带有header和footer的
        private View mHeaderView;
        private View mFooterView;


        public View getmHeaderView() {
            return mHeaderView;
        }

        public void setmHeaderView(View mHeaderView) {
            this.mHeaderView = mHeaderView;
        }

        public View getmFooterView() {
            return mFooterView;
        }

        public void setmFooterView(View mFooterView) {
            this.mFooterView = mFooterView;
        }


        @Override
        public int getItemViewType(int position) {
//            return super.getItemViewType(position);
            if (mHeaderView == null && mFooterView == null){
                return TYPE_NORMAL;
            }
            if(mHeaderView!=null) {
                if (position == 0) {
                    //第一个item应该加载Header
                    return TYPE_HEADER;
                }
            }
            if(mFooterView!=null){
                if (position == getItemCount()-1){
                    //最后一个,应该加载Footer
                    return TYPE_FOOTER;
                }
            }

            return TYPE_NORMAL;
        }


        //-----------------------------------------------------------

        public Linear_V_Adapter() {
        }



        public class MyViewHolder extends RecyclerView.ViewHolder{
            LinearLayout linear_v_ll;
            ImageView linear_v_img;
            TextView linear_v_text;

            public MyViewHolder(View itemView) {
                super(itemView);
                if (itemView == mHeaderView){
                    return;
                }
                if (itemView == mFooterView){
                    return;
                }
                linear_v_ll= (LinearLayout) itemView.findViewById(R.id.linear_v_ll);
                linear_v_img= (ImageView) itemView.findViewById(R.id.linear_v_img);
                linear_v_text= (TextView) itemView.findViewById(R.id.linear_v_text);
            }
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_v_layout,null);
            MyViewHolder holder=new MyViewHolder(view);

            if(mHeaderView != null && viewType == TYPE_HEADER) {
                return new MyViewHolder(mHeaderView);
            }
            if(mFooterView != null && viewType == TYPE_FOOTER){
                return new MyViewHolder(mFooterView);
            }


            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            if (getItemViewType(position) == TYPE_HEADER){
                return;
            }
            if (getItemViewType(position) == TYPE_FOOTER){
                return;
            }

            Log.e("wjk",">>>>>>>>"+getItemCount());
            if(getItemViewType(position)==TYPE_NORMAL){


                if(linear_v_list.get(position)!=null){
                    SampleShow sampleShow=linear_v_list.get(position);
                    ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    holder.linear_v_ll.setLayoutParams(params);

                    holder.linear_v_img.setImageResource(sampleShow.getResId());
                    holder.linear_v_text.setText(sampleShow.getTextShow());

                    if(onItemClickListener!=null){
                        holder.linear_v_img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onItemClickListener.onClick(position);
                            }
                        });

                        holder.linear_v_img.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View view) {
                                onItemClickListener.onLongClick(position);
                                return true;
                            }
                        });
                    }
                }



            }

        }

        @Override
        public int getItemCount() {

            return linear_v_list.size();

//            if(mHeaderView != null && mFooterView == null){
//                return linear_v_list.size();
//            }else if(mHeaderView == null && mFooterView != null){
//                return linear_v_list.size();
//            }else if(mHeaderView != null && mFooterView != null){
//                return linear_v_list.size();
//            }else{
//                return linear_v_list.size();
//            }



        }


        public OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
        }


    }


    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }






}
