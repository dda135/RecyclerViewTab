package fanjh.mine.recyclerviewtab.split;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fanjh.mine.library.TabItemIndicatorDecoration;
import fanjh.mine.library.TabItemSplitDecoration;
import fanjh.mine.library.TabRecyclerView;
import fanjh.mine.recyclerviewtab.R;
import fanjh.mine.recyclerviewtab.fragment.DemoFragment;
import fanjh.mine.recyclerviewtab.indicator.TextTabAdapter;

public class SplitActivity extends AppCompatActivity {

    private ViewPager mContentPager;

    private TabRecyclerView mRecyclerView;
    private TextTabAdapter mTabAdapter;
    private TabItemSplitDecoration mTabSplitDecoration;
    private TabItemIndicatorDecoration mTabIndicatorDecoration;

    private Button mChangeTabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContentPager = (ViewPager) findViewById(R.id.vp_content);

        mChangeTabButton = (Button) findViewById(R.id.btn_change_tab);
        mChangeTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int randomCount = (int) (Math.random() * 40 + 1);
                List<String> mCollections = new ArrayList<String>(randomCount);
                for(int i = 0;i < randomCount;++i){
                    if(i > 10){
                        mCollections.add("aaaaa"+i);
                    }else {
                        mCollections.add("Tab" + i);
                    }
                }
                mTabAdapter.updateCollections(mCollections);
                mContentPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int position) {
                        return DemoFragment.newInstance("Fragment"+position);
                    }

                    @Override
                    public int getCount() {
                        return randomCount;
                    }
                });
                mRecyclerView.setSelectIndex(0,0);
            }
        });
        mRecyclerView = (TabRecyclerView) findViewById(R.id.rv_tab);

        initIndicatorDecoration();
        initDecoration();
        initAdapter();
        mRecyclerView.setViewPager(mContentPager);

        mRecyclerView.addItemDecoration(mTabIndicatorDecoration);
        mRecyclerView.addItemDecoration(mTabSplitDecoration);
        mRecyclerView.setAdapter(mTabAdapter);
        //mRecyclerView.shouldAutoMiddle(false);

    }

    private void initDecoration(){
        mTabSplitDecoration = new TabItemSplitDecoration(this);
        int mLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,8,getResources().getDisplayMetrics());
        int mLineWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2,getResources().getDisplayMetrics());
        //mTabDecoration.setLineHeight(mLineHeight);
        mTabSplitDecoration.setLineColor(getResources().getColor(android.R.color.darker_gray));
        mTabSplitDecoration.setRatio(0.4f);
        mTabSplitDecoration.setLineWidth(mLineWidth);
    }

    private void initIndicatorDecoration(){
        mTabIndicatorDecoration = new TabItemIndicatorDecoration(this);
        mTabIndicatorDecoration.setFixedWidth(true);
        int mLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,2,getResources().getDisplayMetrics());
        mTabIndicatorDecoration.setLineBackgroundColor(getResources().getColor(android.R.color.transparent));
        mTabIndicatorDecoration.setLineHeight(mLineHeight);
        mTabIndicatorDecoration.setLineWidth(mLineHeight);
    }

    private void initAdapter(){
        mTabAdapter = new TextTabAdapter(this);
    }

}
