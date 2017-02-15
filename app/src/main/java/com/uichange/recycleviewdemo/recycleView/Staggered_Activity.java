package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;

public class Staggered_Activity extends TopBaseActivity {
    private RecyclerView staggered_recyclrview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_staggered_);

        initLists();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        staggered_recyclrview.setLayoutManager(staggeredGridLayoutManager);


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
        View view=layoutInflater.inflate(R.layout.activity_staggered_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        staggered_recyclrview= (RecyclerView) view.findViewById(R.id.staggered_recyclrview);
    }

    private void initLists(){

    }

    public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.staggered_layout,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView staggered_img;

            public MyViewHolder(View itemView) {
                super(itemView);
                staggered_img= (ImageView) itemView.findViewById(R.id.staggered_img);
            }
        }
    }
}
