package com.uichange.recycleviewdemo.MaterialDesign.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uichange.recycleviewdemo.MaterialDesign.EndLessOnScrollListener;
import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_R;

import java.util.ArrayList;
import java.util.List;

public class MLinear_V_Activity extends TopBaseActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecycleView;
    private List<SampleShow> mlist;
    private MyAdapter adapter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1000:
                    SampleShow sample=new SampleShow(R.drawable.buchiyu,"上拉刷新");
                    mlist.add(0,sample);
                    adapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);

                    break;
                case 2000:
                    for(int i=0;i<5;i++){
                        SampleShow sample1=new SampleShow(R.drawable.buchiyu,"下拉加载>>>>"+i);
                        mlist.add(sample1);
                    }

                    adapter.notifyDataSetChanged();

                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_m_linear__v_);

        initLists();


        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(layoutManager);
        mRecycleView.addItemDecoration(new ItemDecor_R(this,LinearLayoutManager.VERTICAL));  //调用系统分割线

        adapter=new MyAdapter();
        mRecycleView.setAdapter(adapter);

        mRecycleView.addOnScrollListener(new EndLessOnScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadMoreData();   //下来加载
            }
        });

        mSwipeRefreshLayout.setColorSchemeResources(R.color.orange);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                referData();
            }
        });


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(int position) {
                addData(position);
            }

            @Override
            public void onLongClick(int position) {
                removeData(position);
            }
        });

    }


    private void referData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1000);
            }
        },1000);
    }


    private void loadMoreData(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(2000);
            }
        },1000);
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
        View view=layoutInflater.inflate(R.layout.activity_m_linear__v_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        mSwipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.mSwipeRefreshLayout);
        mRecycleView= (RecyclerView) view.findViewById(R.id.mRecycleView);
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
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            SampleShow sampleShow=mlist.get(position);
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
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        mlist.add(position,sample);
        adapter.notifyItemInserted(position);
        adapter.notifyItemRangeChanged(position,mlist.size());
    }

    public void removeData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        mlist.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,mlist.size());

    }
}
