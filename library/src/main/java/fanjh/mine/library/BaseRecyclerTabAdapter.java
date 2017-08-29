package fanjh.mine.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
* @author fanjh
* @date 2017/8/29 9:21
* @description 适配器基类
**/
public abstract class BaseRecyclerTabAdapter<T> extends RecyclerView.Adapter{
    private List<T> mCollections;
    private Context mContext;
    private LayoutInflater mInflater;
    private List<OnSelectedCallback> mCallbacks;
    private List<View.OnClickListener> mTabClickListeners;
    private int mSelectedIndex;
    private float mNextOffset;

    public Context getContext() {
        return mContext;
    }

    public int getSelectedIndex() {
        return mSelectedIndex;
    }

    public interface OnSelectedCallback{
        void onSelected(int oldSelectedPosition,int SelectedPosition);
    }

    public BaseRecyclerTabAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    void setSelectedIndex(int mSelectedIndex,float nextOffset){
        this.mSelectedIndex = mSelectedIndex;
        mNextOffset = nextOffset;
    }

    public void addCallback(OnSelectedCallback callback){
        if(null == mCallbacks){
            mCallbacks = new ArrayList<>();
        }
        mCallbacks.add(callback);
    }

    public void removeCallback(OnSelectedCallback callback){
        if(null == mCallbacks){
            return;
        }
        mCallbacks.remove(callback);
    }

    public void addTabClickListener(View.OnClickListener listener){
        if(null == mTabClickListeners){
            mTabClickListeners = new ArrayList<>();
        }
        mTabClickListeners.add(listener);
    }

    public void removeTabClickListener(View.OnClickListener listener){
        if(null == mTabClickListeners){
            return;
        }
        mTabClickListeners.remove(listener);
    }

    protected View inflater(int resID, ViewGroup viewGroup){
        return mInflater.inflate(resID,viewGroup,false);
    }

    public void addCollections(List<T> newCollections){
        if(null == newCollections){
            return;
        }
        if(null == mCollections){
            mCollections = new ArrayList<>(newCollections);
        }else{
            mCollections.addAll(newCollections);
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int oldSelectedIndex = mSelectedIndex;
                mSelectedIndex = position;
                if(null != mCallbacks){
                    for(OnSelectedCallback callback:mCallbacks){
                        callback.onSelected(oldSelectedIndex,mSelectedIndex);
                    }
                }
                if(null != mTabClickListeners) {
                    for (View.OnClickListener listener : mTabClickListeners) {
                        listener.onClick(v);
                    }
                }
            }
        });
        bindDataToView(mSelectedIndex == position,mNextOffset,holder,position);
    }

    public abstract void bindDataToView(boolean isSelected,float nextOffset,RecyclerView.ViewHolder holder, final int position);

    public void updateCollections(List<T> newCollections){
        if(null == newCollections){
            return;
        }
        if(null == mCollections){
            mCollections = new ArrayList<>(newCollections);
        }else{
            mCollections.clear();
            mCollections.addAll(newCollections);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(null != mCollections){
            return mCollections.size();
        }
        return 0;
    }

    public T getItem(int position){
        return mCollections.get(position);
    }

}
