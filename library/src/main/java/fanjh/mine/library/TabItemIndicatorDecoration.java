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
* @description Tab的底部横线型装饰
**/
public class TabItemIndicatorDecoration extends BaseTabDecoration {
    private Context mContext;
    private boolean isFixedWidth;
    private int mLineHeight;
    private int mLineColor;
    private int mLineBackgroundColor;
    private int mLineWidth;
    private Paint mPaint;
    private OnScrollSelectedCallback mDefaultScrollCallback;
    private Rect mLineBound;

    public TabItemIndicatorDecoration(Context mContext) {
        this.mContext = mContext;
        mLineColor = mContext.getResources().getColor(android.R.color.holo_green_dark);
        mLineBackgroundColor = mContext.getResources().getColor(android.R.color.darker_gray);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mLineBound = new Rect();
        mDefaultScrollCallback = new OnScrollSelectedCallback() {
            @Override
            public void onScrollSelected(Canvas c, View child, int nextDistance, float offset) {
                int bottom = child.getBottom();
                int left;
                if(isFixedWidth){
                    mLineWidth = child.getWidth();
                    left = (int) (child.getLeft() + offset * mLineWidth);
                }else{
                    if(mLineWidth > child.getWidth()){
                        mLineWidth = child.getWidth();
                    }
                    left = (int) (child.getLeft() + ((child.getWidth() - mLineWidth) >> 1) +
                            offset * ((child.getWidth() + nextDistance) >> 1));
                }
                mLineBound.set(left,bottom,left + mLineWidth,bottom + mLineHeight);
                mPaint.setColor(mLineColor);
                c.drawRect(mLineBound.left, mLineBound.top, mLineBound.right, mLineBound.bottom,mPaint);
            }
        };
        setScrollSelectedCallback(mDefaultScrollCallback);
    }

    public void setLineColor(int lineColor){
        mLineColor = lineColor;
    }

    public void setLineBackgroundColor(int lineBackgroundColor){
        mLineBackgroundColor = lineBackgroundColor;
    }

    public void setLineHeight(int lineHeight){
        mLineHeight = lineHeight;
    }

    public void setFixedWidth(boolean isFixedWidth){
        this.isFixedWidth = isFixedWidth;
    }

    public void setLineWidth(int mLineWidth) {
        this.mLineWidth = mLineWidth;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        mLineBound.set(parent.getLeft(),parent.getBottom() - mLineHeight,parent.getRight(),parent.getBottom());
        mPaint.setColor(mLineBackgroundColor);
        c.drawRect(mLineBound.left, mLineBound.top, mLineBound.right, mLineBound.bottom,mPaint);
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,0,0,mLineHeight);
    }
}
