package fanjh.mine.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
* @author fanjh
* @date 2017/8/29 9:26
* @description Tab直接的分割线装饰
**/
public class TabItemSplitDecoration extends BaseTabDecoration {
    private Context mContext;
    private int mLineHeight;
    private float mRatio;
    private int mLineColor;
    private int mLineWidth;
    private Paint mPaint;

    public TabItemSplitDecoration(Context mContext) {
        this.mContext = mContext;
        mLineColor = mContext.getResources().getColor(android.R.color.holo_green_dark);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
    }

    public void setLineColor(int lineColor){
        mLineColor = lineColor;
    }

    public void setLineHeight(int lineHeight){
        mLineHeight = lineHeight;
    }

    public void setLineWidth(int mLineWidth) {
        this.mLineWidth = mLineWidth;
    }

    public void setRatio(float ratio){
        mRatio = ratio;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,mLineWidth,0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for(int i = 0;i < parent.getChildCount();++i){
            View child = parent.getChildAt(i);
            if(mLineHeight > child.getHeight()){
                mLineHeight = child.getHeight();
            }else if(mRatio > 0){
                mLineHeight = (int) (child.getHeight() * mRatio);
            }
            int top = ((child.getHeight() - mLineHeight) >> 1);
            mPaint.setColor(mLineColor);
            c.drawRect(child.getRight(),top,child.getRight() + mLineWidth,top + mLineHeight,mPaint);
        }
    }
}
