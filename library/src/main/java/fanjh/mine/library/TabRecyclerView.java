package fanjh.mine.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * @author fanjh
 * @date 2017/8/29 10:19
 * @description 横向选项卡RecyclerView
 * @note 内部默认使用横向的LinearLayoutManager，所以不允许自定义LayoutManager
 * 其次Adapter会进行装饰，并且默认添加ItemDecoration
 * 内部统一管理选中item
 **/
public class TabRecyclerView extends RecyclerView {
    public static final int DEFAULT_ANIMATE_TIME = 150;
    public static final int DEFAULT_AUTO_SCROLL_TIME = 50;
    private LinearLayoutManager mDefaultLayoutManager;
    private BaseTabDecoration mTabDecoration;
    private BaseRecyclerTabAdapter<?> mTabAdapter;
    private ViewPager mAssociatePager;
    private int mSelectedIndex;
    private LinearSmoothScroller mLinearSmoothScroller;
    private DefaultItemAnimator mItemAnimator;
    private boolean clickShouldSmooth;

    public TabRecyclerView(Context context) {
        this(context, null);
    }

    public TabRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setDefaultLayoutManager();
        initSmoothScroller();
        initAnimator();
    }

    private void initSmoothScroller() {
        mLinearSmoothScroller = new LinearSmoothScroller(getContext()) {

            @Override
            public int calculateDtToFit(int viewStart, int viewEnd, int boxStart, int boxEnd, int snapPreference) {
                return (boxStart + ((boxEnd - boxStart) >> 1)) - (viewStart + ((viewEnd - viewStart) >> 1));
            }

            @Override
            protected int calculateTimeForScrolling(int dx) {
                return DEFAULT_AUTO_SCROLL_TIME;
            }
        };
    }

    private void initAnimator() {
        mItemAnimator = new DefaultItemAnimator(){
            @Override
            public boolean animateMove(ViewHolder holder, int fromX, int fromY, int toX, int toY) {
                return true;
            }

            @Override
            public boolean animateAdd(ViewHolder holder) {
                return true;
            }

            @Override
            public boolean animateRemove(ViewHolder holder) {
                return true;
            }

            @Override
            public boolean animateAppearance(@NonNull ViewHolder viewHolder, @Nullable ItemHolderInfo preLayoutInfo, @NonNull ItemHolderInfo postLayoutInfo) {
                return true;
            }

            @Override
            public boolean animateDisappearance(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo preLayoutInfo, @Nullable ItemHolderInfo postLayoutInfo) {
                return true;
            }

            @Override
            public boolean animatePersistence(@NonNull ViewHolder viewHolder, @NonNull ItemHolderInfo preInfo, @NonNull ItemHolderInfo postInfo) {
                return true;
            }
        };
        mItemAnimator.setAddDuration(DEFAULT_ANIMATE_TIME);
        mItemAnimator.setChangeDuration(DEFAULT_ANIMATE_TIME);
        mItemAnimator.setMoveDuration(DEFAULT_ANIMATE_TIME);
        mItemAnimator.setRemoveDuration(DEFAULT_ANIMATE_TIME);
    }

    private void setDefaultLayoutManager() {
        mDefaultLayoutManager = new LinearLayoutManager(getContext());
        mDefaultLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        super.setLayoutManager(mDefaultLayoutManager);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        throw new IllegalArgumentException("当前不能使用自定义的LayoutManager！");
    }

    @Override
    public void addItemDecoration(ItemDecoration decor) {
        if (decor instanceof BaseTabDecoration) {
            mTabDecoration = (BaseTabDecoration) decor;
            super.addItemDecoration(mTabDecoration);
            return;
        }
        throw new IllegalArgumentException("当前只能使用继承于BaseTabDecoration的装饰");
    }

    public void clickShouldSmooth(boolean clickShouldSmooth) {
        this.clickShouldSmooth = clickShouldSmooth;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter instanceof BaseRecyclerTabAdapter) {
            mTabAdapter = (BaseRecyclerTabAdapter<?>) adapter;
            mTabAdapter.addCallback(new BaseRecyclerTabAdapter.OnSelectedCallback() {
                @Override
                public void onSelected(int oldSelectedIndex, int position) {
                    setSelectIndex(position, 0);
                    if (null != mAssociatePager) {
                        mAssociatePager.setCurrentItem(position, clickShouldSmooth);
                    }
                    autoScroll();
                }
            });
            super.setAdapter(mTabAdapter);
            return;
        }
        throw new IllegalArgumentException("当前只能填充继承于BaseRecyclerTabAdapter的适配器");
    }

    public void setViewPager(ViewPager mAssociatePager) {
        if (null == mAssociatePager) {
            throw new NullPointerException("关联的ViewPager可以为空？");
        }
        this.mAssociatePager = mAssociatePager;
        this.mAssociatePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setSelectIndex(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(state == RecyclerView.SCROLL_STATE_IDLE) {
                    autoScroll();
                }
            }
        });
    }

    private void autoScroll(){
        if(null == mDefaultLayoutManager.findViewByPosition(mSelectedIndex)){
            scrollToPosition(mSelectedIndex);
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    mLinearSmoothScroller.setTargetPosition(mSelectedIndex);
                    mDefaultLayoutManager.startSmoothScroll(mLinearSmoothScroller);
                }
            },100);
        }else {
            mLinearSmoothScroller.setTargetPosition(mSelectedIndex);
            mDefaultLayoutManager.startSmoothScroll(mLinearSmoothScroller);
        }
    }

    public void setSelectIndex(int newIndex, float nextOffset) {
        if (null != mTabDecoration) {
            mTabDecoration.setSelectIndex(newIndex, nextOffset);
        }
        if (null != mTabAdapter) {
            mTabAdapter.setSelectedIndex(newIndex, nextOffset);
            if (newIndex != mSelectedIndex) {
                mTabAdapter.notifyItemChanged(mSelectedIndex);
                mTabAdapter.notifyItemChanged(newIndex);
            } else {
                invalidate();
            }
        }
        mSelectedIndex = newIndex;
    }

}

