package fanjh.mine.recyclerviewtab.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import fanjh.mine.library.TabRecyclerView;
import fanjh.mine.recyclerviewtab.R;
import fanjh.mine.recyclerviewtab.fragment.DemoFragment;

/**
 * Created by faker on 2017/8/29.
 */

public class TabActivity extends FragmentActivity {
    private ViewPager mContentPager;
    private TabRecyclerView mTabView;
    private TabAdapter mTabAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        mTabAdapter = new TabAdapter(this,getResources().getDisplayMetrics().widthPixels);
        final List<TabEntity> tabEntityList = new ArrayList<>();
        tabEntityList.add(new TabEntity("首页",R.mipmap.ic_launcher,R.mipmap.ic_launcher_round));
        tabEntityList.add(new TabEntity("发现",R.mipmap.ic_launcher,R.mipmap.ic_launcher_round));
        tabEntityList.add(new TabEntity("消息",R.mipmap.ic_launcher,R.mipmap.ic_launcher_round));
        tabEntityList.add(new TabEntity("我的",R.mipmap.ic_launcher,R.mipmap.ic_launcher_round));
        mTabAdapter.addCollections(tabEntityList);
        mContentPager = (ViewPager) findViewById(R.id.vp_content);
        mTabView = (TabRecyclerView) findViewById(R.id.trv_tab);
        mTabView.setViewPager(mContentPager);
        mTabView.clickShouldSmooth(false);

        mTabView.setAdapter(mTabAdapter);
        mContentPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return DemoFragment.newInstance(tabEntityList.get(position).getText());
            }

            @Override
            public int getCount() {
                return tabEntityList.size();
            }
        });
    }
}
