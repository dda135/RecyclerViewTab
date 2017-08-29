package fanjh.mine.recyclerviewtab.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import fanjh.mine.library.TabItemDecoration;
import fanjh.mine.library.TabRecyclerView;
import fanjh.mine.recyclerviewtab.fragment.DemoFragment;
import fanjh.mine.recyclerviewtab.R;

public class MainActivity extends AppCompatActivity {

    private ViewPager mContentPager;

    private TabRecyclerView mRecyclerView;
    private TextTabAdapter mTabAdapter;
    private TabItemDecoration mTabDecoration;

    private Button mChangeTabButton;

    private int mLineHeight;

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

        initDecoration();
        initAdapter();
        mRecyclerView.setViewPager(mContentPager);

        mRecyclerView.addItemDecoration(mTabDecoration);
        mRecyclerView.setAdapter(mTabAdapter);

    }

    private void initDecoration(){
        mTabDecoration = new TabItemDecoration(this);
        mTabDecoration.setFixedWidth(true);
        mLineHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,4,getResources().getDisplayMetrics());
        mTabDecoration.setLineHeight(mLineHeight);
        mTabDecoration.setLineWidth(mLineHeight);
    }

    private void initAdapter(){
        mTabAdapter = new TextTabAdapter(this,mLineHeight);
    }

}
