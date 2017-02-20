package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.recycleView.ItemDecoration.ItemDecor_grid;

import java.util.ArrayList;
import java.util.List;

public class Grid_Activity extends TopBaseActivity {
    private RecyclerView grid_recycleview;
    private List<SampleShow> grid_list;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_grid_);

        initLists();


        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
        grid_recycleview.setLayoutManager(gridLayoutManager);

        grid_recycleview.addItemDecoration(new ItemDecor_grid(this));

        adapter=new GridAdapter();
        grid_recycleview.setAdapter(adapter);
    }

    @Override
    public String setLeftText() {
        return null;
    }

    @Override
    public String setCenterText() {
        return "网格布局";
    }

    @Override
    public View setChildView(LayoutInflater layoutInflater) {
        View view=layoutInflater.inflate(R.layout.activity_grid_,null);
        initView(view);
        return view;
    }

    private void initView(View view){
        grid_recycleview= (RecyclerView) view.findViewById(R.id.grid_recycleview);
    }

    private void initLists() {
        grid_list=new ArrayList<>();

        for(int i=0;i<15;i++){
            SampleShow sample0=new SampleShow(R.drawable.buchiyu,"不吃鱼");
            grid_list.add(sample0);

            SampleShow sample1=new SampleShow(R.drawable.chunlv,"蠢驴");
            grid_list.add(sample1);

            SampleShow sample2=new SampleShow(R.drawable.everydaylove,"每天都在谈恋爱");
            grid_list.add(sample2);

            SampleShow sample3=new SampleShow(R.drawable.jiuyue,"九月");
            grid_list.add(sample3);

            SampleShow sample4=new SampleShow(R.drawable.jxiansen,"j先生");
            grid_list.add(sample4);

            SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,"旅行者");
            grid_list.add(sample5);
        }
    }
    public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_layout,null);
            MyViewHolder holder=new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            SampleShow sampleShow=grid_list.get(position);
            holder.grid_img.setBackgroundResource(sampleShow.getResId());
            holder.grid_text.setText(sampleShow.getTextShow());

            holder.grid_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addData(position);
                }
            });

            holder.grid_img.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    removeData(position);
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return grid_list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView grid_img;
            TextView grid_text;

            public MyViewHolder(View itemView) {
                super(itemView);
                grid_img= (ImageView) itemView.findViewById(R.id.grid_img);
                grid_text= (TextView) itemView.findViewById(R.id.grid_text);
            }
        }
    }

    public void addData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        grid_list.add(position,sample);
        adapter.notifyItemInserted(position);
        adapter.notifyItemRangeChanged(position,grid_list.size());
    }

    public void removeData(int position){
        SampleShow sample=new SampleShow(R.drawable.buchiyu,"王建凯");
        grid_list.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position,grid_list.size());

    }
}
