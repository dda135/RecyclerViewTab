package fanjh.mine.recyclerviewtab.indicator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fanjh.mine.library.BaseRecyclerTabAdapter;
import fanjh.mine.recyclerviewtab.R;

/**
 * Created by faker on 2017/8/29.
 */

public class TextTabAdapter extends BaseRecyclerTabAdapter<String> {

    public TextTabAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TextViewHolder(inflater(R.layout.item_tab_text,parent));
    }

    @Override
    public void bindDataToView(boolean isSelected,int pagerIndex,float nextOffset,RecyclerView.ViewHolder holder, final int position) {
        TextViewHolder textViewHolder = (TextViewHolder) holder;
        textViewHolder.mTabText.setTextColor(isSelected?getContext().getResources().getColor(android.R.color.holo_green_dark):
        getContext().getResources().getColor(android.R.color.black));
        textViewHolder.mTabText.setText(getItem(position));
    }

    class TextViewHolder extends RecyclerView.ViewHolder{
        TextView mTabText;

        public TextViewHolder(View itemView) {
            super(itemView);
            mTabText = (TextView) itemView;
        }

    }


}
