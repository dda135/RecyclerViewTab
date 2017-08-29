package fanjh.mine.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
* @author fanjh
* @date 2017/8/29 18:40
* @description 再确认tab可以显示的下的前提下，可以自动实现横向等分的适配器
**/
public abstract class BaseEqualSplitAdapter<T> extends BaseRecyclerTabAdapter<T> {
    private int mSumWidth;

    public BaseEqualSplitAdapter(Context mContext, int mSumWidth) {
        super(mContext);
        this.mSumWidth = mSumWidth;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = createHolder(parent,viewType);
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(mSumWidth / getItemCount(), ViewGroup.LayoutParams.WRAP_CONTENT));
        return holder;
    }

    public abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

}
