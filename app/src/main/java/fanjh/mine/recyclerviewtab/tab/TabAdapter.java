package fanjh.mine.recyclerviewtab.tab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import fanjh.mine.library.BaseEqualSplitAdapter;
import fanjh.mine.library.BaseRecyclerTabAdapter;
import fanjh.mine.recyclerviewtab.R;

/**
 * Created by faker on 2017/8/29.
 */

public class TabAdapter extends BaseEqualSplitAdapter<TabEntity> {

    public TabAdapter(Context mContext, int mSumWidth) {
        super(mContext, mSumWidth);
    }

    @Override
    public void bindDataToView(boolean isSelected, float nextOffset, RecyclerView.ViewHolder holder, int position) {
        TabHolder tabHolder = (TabHolder) holder;
        TabEntity tabEntity = getItem(position);
        tabHolder.mTabTextView.setTextColor(isSelected?getContext().getResources().getColor(android.R.color.holo_green_dark):
        getContext().getResources().getColor(android.R.color.black));
        tabHolder.mTabTextView.setText(tabEntity.getText());
        tabHolder.mTabIconView.setImageResource(isSelected?tabEntity.getSelectResID():tabEntity.getNormalResID());
    }

    @Override
    public RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return new TabHolder(inflater(R.layout.item_tab,parent));
    }

    class TabHolder extends RecyclerView.ViewHolder{
        private TextView mTabTextView;
        private ImageView mTabIconView;
        public TabHolder(View itemView) {
            super(itemView);
            mTabIconView = (ImageView) itemView.findViewById(R.id.iv_tab_icon);
            mTabTextView = (TextView) itemView.findViewById(R.id.tv_tab_text);
        }
    }

}
