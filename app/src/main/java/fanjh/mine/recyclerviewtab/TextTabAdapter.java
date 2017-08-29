package fanjh.mine.recyclerviewtab;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import fanjh.mine.library.BaseRecyclerTabAdapter;

/**
 * Created by faker on 2017/8/29.
 */

public class TextTabAdapter extends BaseRecyclerTabAdapter<String> {
    private int mLineHeight;

    public TextTabAdapter(Context mContext, int mLineHeight) {
        super(mContext);
        this.mLineHeight = mLineHeight;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater(R.layout.item_tab_text,parent);
        view.setPadding(view.getPaddingLeft(),view.getPaddingTop(),view.getPaddingRight(),view.getPaddingBottom() + mLineHeight);
        return new TextViewHolder(view);
    }

    @Override
    public void bindDataToView(boolean isSelected,float nextOffset,RecyclerView.ViewHolder holder, final int position) {
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
