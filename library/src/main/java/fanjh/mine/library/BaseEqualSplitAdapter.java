package fanjh.mine.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

/**
* @author fanjh
* @date 2017/8/29 18:40
* @description 再确认tab可以显示的下的前提下，可以自动实现横向等分的适配器
**/
public abstract class BaseEqualSplitAdapter<T> extends BaseRecyclerTabAdapter<T> {
    private RecyclerView associateRecyclerView;

    public BaseEqualSplitAdapter(Context mContext, RecyclerView recyclerView) {
        super(mContext);
        this.associateRecyclerView = recyclerView;
        associateRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                notifyDataSetChanged();
                associateRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = createHolder(parent,viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if(params.width != associateRecyclerView.getWidth() / getItemCount()){
            params.width = associateRecyclerView.getWidth() / getItemCount();
            holder.itemView.requestLayout();
        }
    }

    public abstract RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType);

}
