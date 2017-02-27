package com.uichange.recycleviewdemo.xRecycleView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_R;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_grid;
import com.uichange.recycleviewdemo.utils.PhoneUtils;

import java.util.ArrayList;
import java.util.List;

public class XLinear_V_Activity extends TopBaseActivity {
    private XRecyclerView xlinear_v_recyclrView;
    private List<SampleShow> linear_v_list;
    private PhoneUtils phoneUtils;
    private XLinear_V_Adapter adapter;
    private String enterFlag;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_xlinear__v_);

        if(getIntent().hasExtra("xRecycleView")){
            enterFlag=getIntent().getStringExtra("xRecycleView");
        }

        phoneUtils=new PhoneUtils(XLinear_V_Activity.this);

        initLists();

        if(!TextUtils.isEmpty(enterFlag)&&enterFlag.equals("list")){
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            xlinear_v_recyclrView.setLayoutManager(layoutManager);

            xlinear_v_recyclrView.addItemDecoration(new ItemDecor_R(this,LinearLayoutManager.VERTICAL));  //调用系统分割线


        }else{
            GridLayoutManager layoutManager=new GridLayoutManager(this,3);
            xlinear_v_recyclrView.setLayoutManager(layoutManager);

            xlinear_v_recyclrView.addItemDecoration(new ItemDecor_grid(this));
        }


        xlinear_v_recyclrView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xlinear_v_recyclrView.setArrowImageView(R.drawable.arrow);



        View headView=LayoutInflater.from(this).inflate(R.layout.recycleview_headerview,null);
        xlinear_v_recyclrView.addHeaderView(headView);

        adapter=new XLinear_V_Adapter();
        xlinear_v_recyclrView.setAdapter(adapter);

        xlinear_v_recyclrView.setPullRefreshEnabled(true);
        xlinear_v_recyclrView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        for(int i=0;i<2;i++){
//                            SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
//                            linear_v_list.add(0,sample);
//                        }
                        adapter.notifyDataSetChanged();
                        xlinear_v_recyclrView.refreshComplete();
//                        Toast.makeText(XLinear_V_Activity.this,"上啦刷新成功~  新增2条数据",Toast.LENGTH_LONG).show();
                    }
                },1500);


            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=0;i<2;i++){
                            SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
                            linear_v_list.add(sample);

                        }

                        adapter.notifyDataSetChanged();
                        xlinear_v_recyclrView.loadMoreComplete();
                        Toast.makeText(XLinear_V_Activity.this,"下拉加载成功",Toast.LENGTH_LONG).show();
                    }
                },1500);


            }
        });









        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                addData(position);
//                Toast.makeText(XLinear_V_Activity.this,"onClick"+linear_v_list.get(position).getTextShow(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onLongClick(int position) {
                removeData(position);
//                Toast.makeText(XLinear_V_Activity.this,"onLongClick"+linear_v_list.get(position).getTextShow(),Toast.LENGTH_LONG).show();
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
        View view =layoutInflater.inflate(R.layout.activity_xlinear__v_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        xlinear_v_recyclrView= (XRecyclerView) view.findViewById(R.id.xlinear_v_recyclrView);

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

    public class XLinear_V_Adapter extends RecyclerView.Adapter<XLinear_V_Adapter.MyViewHolder>{

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_v_layout,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            SampleShow sampleShow=linear_v_list.get(position);
            ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            holder.linear_v_ll.setLayoutParams(params);

            holder.linear_v_img.setImageResource(sampleShow.getResId());
            holder.linear_v_text.setText(sampleShow.getTextShow());

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

        @Override
        public int getItemCount() {
            return linear_v_list.size();
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

        public OnItemClickListener onItemClickListener;

        public void setOnItemClickListener(OnItemClickListener onItemClickListener){
            this.onItemClickListener=onItemClickListener;
        }



    }

    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }

    public void addData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"我是新增加的~");
        linear_v_list.add(position,sample);
        adapter.notifyItemInserted(position);
        adapter.notifyItemRangeChanged(position,linear_v_list.size());
    }

    public void removeData(int position){
        linear_v_list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,linear_v_list.size());

    }
}
