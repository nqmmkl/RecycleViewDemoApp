package com.uichange.recycleviewdemo.recycleView;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.uichange.recycleviewdemo.Constants;
import com.uichange.recycleviewdemo.R;
import com.uichange.recycleviewdemo.base.TopBaseActivity;
import com.uichange.recycleviewdemo.javabean.SampleShow;
import com.uichange.recycleviewdemo.utils.CommonUtil;
import com.uichange.recycleviewdemo.volley.MemoryBitmapCache;

import java.util.ArrayList;
import java.util.List;

public class Staggered_Activity extends TopBaseActivity {
    private RecyclerView staggered_recyclrview;
    private List<String> urlList;
    private List<SampleShow> staggered_list;
    private int showType=1;  //0-网络请求   //1-本地图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_staggered_);

        if(getIntent().hasExtra("Staggered_flag")){
            showType=getIntent().getIntExtra("Staggered_flag",1);
        }

        initLists();
        initVolley();
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        staggered_recyclrview.setLayoutManager(staggeredGridLayoutManager);

        StaggeredAdapter adapter=new StaggeredAdapter();
        staggered_recyclrview.setAdapter(adapter);


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
        urlList=new ArrayList<>();
        staggered_list=new ArrayList<>();


        if(showType==0){
            for(int i=0;i< Constants.imageUrls.length;i++){
                urlList.add(Constants.imageUrls[i]);
            }
        }else if(showType==1){
            for(int i=0;i<15;i++){
                SampleShow sample0=new SampleShow(R.drawable.buchiyu, CommonUtil.getRandomLengthName("不吃鱼"));
                staggered_list.add(sample0);

                SampleShow sample1=new SampleShow(R.drawable.chunlv,CommonUtil.getRandomLengthName("蠢驴"));
                staggered_list.add(sample1);

                SampleShow sample2=new SampleShow(R.drawable.everydaylove,CommonUtil.getRandomLengthName("每天都在谈恋爱"));
                staggered_list.add(sample2);

                SampleShow sample3=new SampleShow(R.drawable.jiuyue,CommonUtil.getRandomLengthName("九月"));
                staggered_list.add(sample3);

                SampleShow sample4=new SampleShow(R.drawable.jxiansen,CommonUtil.getRandomLengthName("j先生"));
                staggered_list.add(sample4);

                SampleShow sample5=new SampleShow(R.drawable.lvxingzhe,CommonUtil.getRandomLengthName("旅行者"));
                staggered_list.add(sample5);
            }
        }

    }

    private ImageLoader imageLoader;
    private void initVolley() {
        RequestQueue mQueue= Volley.newRequestQueue(this);  //创建一个RequestQueue对象。

//        imageLoader=new ImageLoader(mQueue, new ImageLoader.ImageCache() {  //创建一个ImageLoader对象。
//            @Override
//            public Bitmap getBitmap(String url) {
//                return null;
//            }
//
//            @Override
//            public void putBitmap(String url, Bitmap bitmap) {
//
//            }
//        });
        imageLoader=new ImageLoader(mQueue,new MemoryBitmapCache());

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
            if(showType==0){
                holder.staggered_ll.setVisibility(View.GONE);
                holder.staggered_img.setDefaultImageResId(R.drawable.staggered_default);
                holder.staggered_img.setErrorImageResId(R.drawable.staggered_default);
                holder.staggered_img.setImageUrl(urlList.get(position),imageLoader);
            }else if(showType==1){
                holder.staggered_img.setVisibility(View.GONE);
                holder.staggered_ll_img.setImageResource(staggered_list.get(position).getResId());
                holder.staggered_ll_text.setText(staggered_list.get(position).getTextShow());
            }


        }

        @Override
        public int getItemCount() {
            if(showType==0){
                return urlList.size();
            }else if(showType==1){
                return staggered_list.size();
            }else {
                return 0;
            }

        }

        public class MyViewHolder extends RecyclerView.ViewHolder{
            NetworkImageView staggered_img;
            LinearLayout staggered_ll;
            ImageView staggered_ll_img;
            TextView staggered_ll_text;

            public MyViewHolder(View itemView) {
                super(itemView);
                staggered_img= (NetworkImageView) itemView.findViewById(R.id.staggered_img);
                staggered_ll= (LinearLayout) itemView.findViewById(R.id.staggered_ll);
                staggered_ll_img= (ImageView) itemView.findViewById(R.id.staggered_ll_img);
                staggered_ll_text= (TextView) itemView.findViewById(R.id.staggered_ll_text);
            }
        }
    }
}
