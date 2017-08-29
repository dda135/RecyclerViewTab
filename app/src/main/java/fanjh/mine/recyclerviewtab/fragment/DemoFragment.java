package fanjh.mine.recyclerviewtab.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import fanjh.mine.recyclerviewtab.tab.TabActivity;

/**
 * Created by faker on 2017/8/29.
 */

public class DemoFragment extends Fragment {
    public static final String EXTRA_TEXT = "extra_text";
    private TextView mView;
    private String mShowText;

    public static DemoFragment newInstance(String text){
        if(null == text){
            throw new NullPointerException("初始化显示文字不能为null！");
        }
        DemoFragment fragment = new DemoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TEXT,text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mShowText = getArguments().getString(EXTRA_TEXT);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(null == mView) {
            mView = new TextView(getContext());
            mView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mView.setTextSize(24);
            mView.setText(mShowText);
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), TabActivity.class);
                    startActivity(intent);
                }
            });
        }
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewParent parent = mView.getParent();
        if(parent instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) parent;
            viewGroup.removeView(mView);
        }
    }
}
