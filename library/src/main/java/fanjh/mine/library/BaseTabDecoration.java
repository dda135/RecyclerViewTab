package fanjh.mine.library;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
* @author fanjh
* @date 2017/8/29 10:29
* @description tab选项卡的装饰基类
* @note
**/
public abstract class BaseTabDecoration extends RecyclerView.ItemDecoration{
    private int mSelectedIndex;
    private float mNextOffset;
    private OnScrollSelectedCallback mScrollSelectedCallback;

    public void setScrollSelectedCallback(OnScrollSelectedCallback mScrollSelectedCallback) {
        this.mScrollSelectedCallback = mScrollSelectedCallback;
    }

    public interface OnScrollSelectedCallback{
        void onScrollSelected(Canvas c,View child,int nextDistance,float offset);
    }

    void setSelectIndex(int selectIndex,float nextOffset){
        mSelectedIndex = selectIndex;
        mNextOffset = nextOffset;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for(int i = 0;i < parent.getChildCount();++i){
            View child = parent.getChildAt(i);
            int viewPosition = parent.getChildAdapterPosition(child);
            boolean isAnchorSelectedItem = viewPosition == mSelectedIndex;
            if(null != mScrollSelectedCallback){
                if(isAnchorSelectedItem){
                    RecyclerView.ViewHolder holder = parent.findViewHolderForAdapterPosition(mSelectedIndex + 1);
                    int nextDistance = 0;
                    if(null != holder) {
                        View nextChild = holder.itemView;
                        nextDistance = nextChild.getWidth();
                    }
                    mScrollSelectedCallback.onScrollSelected(c, child, nextDistance, mNextOffset);
                }
            }
        }
    }

}
