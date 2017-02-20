package com.uichange.recycleviewdemo.recycleView.ItemDecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jiankai.wang on 2017/2/16.
 */
public class ItemDecor extends RecyclerView.ItemDecoration { //paint绘制

    //先执行ItemDecoration的onDraw()、再执行ItemView的onDraw()、再执行ItemDecoration的onDrawOver()。
    // 由于和RecyclerView使用的是同一个Canvas，所以你想在Canvas上画什么都可以，就像我们平时自定义View时写onDraw()方法一样


    private Paint mpaint;

    public ItemDecor() {
        mpaint=new Paint();
        mpaint.setColor(0x99FF0000);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        c.drawCircle(500,80,30,mpaint);  //绘制的小红点

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + 10;
            c.drawRect(left, top, right, bottom, mpaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

//        int itemPosition = parent.getChildAdapterPosition(view);
//        int dataSize = parent.getAdapter().getItemCount();
//        if(itemPosition == 0){
//            outRect.set(0,10,0,10);
//        }else{
//            outRect.set(0,0,0,10);
//        }

        outRect.set(0,0,0,10);

    }
}
