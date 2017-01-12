package com.hulabusiness.ui;

import android.support.v4.app.Fragment;

import com.common.widget.fragment.FragmentManager;
import com.common.widget.tablayout.BottomTabLayout;
import com.common.widget.tablayout.CustomTabEntity;
import com.common.widget.tablayout.OnTabSelectListener;
import com.common.widget.tablayout.TabEntity;
import com.hulabusiness.R;
import com.hulabusiness.base.mvp.BaseMvpActivity;
import com.hulabusiness.base.mvp.BasePresenter;
import com.hulabusiness.ui.check.CheckFragment;
import com.hulabusiness.ui.message.MessageFragment;
import com.hulabusiness.ui.mine.MineFragment;

import java.util.ArrayList;

public class MainActivity extends BaseMvpActivity
{
    private BottomTabLayout tabLayout;

    private CheckFragment firstFragment;
    private MessageFragment nextFragment;
    private MineFragment thirdFragment;

    //模块名
    private String[] mTitles = {"场馆", "课程", "我的"};
    //模块选中图片
    private int[] mIconSelectIds = {R.mipmap.icon_venue_press, R.mipmap.icon_class_press, R.mipmap.icon_mine_perss};
    //模块未选中图片
    private int[] mIconUnSelectIds = {R.mipmap.icon_venue_normal, R.mipmap.icon_class_normal, R.mipmap.icon_mine_normal};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected BasePresenter createPresenterInstance() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated() {
        tabLayout = (BottomTabLayout) findViewById(R.id.tab_layout);
    }

    @Override
    protected void doLogicFunc() {
        initTabLayout();
    }

    private void initTabLayout()
    {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new CheckFragment());
        fragments.add(new MessageFragment());
        fragments.add(new MineFragment());

        tabLayout.setTabData(mTabEntities, this, R.id.fragment, fragments);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        FragmentManager.onDestroy();
        super.onDestroy();
    }
}
