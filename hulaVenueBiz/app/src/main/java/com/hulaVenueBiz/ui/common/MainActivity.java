package com.hulaVenueBiz.ui.common;

import android.support.v4.app.Fragment;

import com.common.base.Constants;
import com.common.permission.AfterPermissionGranted;
import com.common.permission.EasyPermissions;
import com.common.widget.fragment.FragmentManager;
import com.common.widget.tablayout.BottomTabLayout;
import com.common.widget.tablayout.CustomTabEntity;
import com.common.widget.tablayout.OnTabSelectListener;
import com.common.widget.tablayout.TabEntity;
import com.hulaVenueBiz.R;
import com.hulaVenueBiz.base.mvp.BaseMvpActivity;
import com.hulaVenueBiz.base.mvp.BasePresenter;
import com.hulaVenueBiz.ui.check.CheckFragment;
import com.hulaVenueBiz.ui.message.MessageFragment;
import com.hulaVenueBiz.ui.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseMvpActivity implements
        EasyPermissions.PermissionCallbacks
{
    private BottomTabLayout tabLayout;

    //模块名
    private String[] mTitles;

    //模块选中图片
    private int[] mIconSelectIds = {R.mipmap.icon_check_press, R.mipmap.icon_new_press, R.mipmap.icon_mine_perss};
    //模块未选中图片
    private int[] mIconUnSelectIds = {R.mipmap.icon_check_normal, R.mipmap.icon_new_normal, R.mipmap.icon_mine_normal};
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
        mTitles = new String[]{getString(R.string.string_fragment_check),
                getString(R.string.string_fragment_message),
                getString(R.string.string_fragment_mine)};
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

    @Override
    protected void onStart() {
        super.onStart();
        requestAppPermission();
    }

    @Override
    public void onPermissionsGranted(List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(List<String> perms) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(Constants.REQUECT_CODE_PERMISSION)
    private void requestAppPermission() {
        if (!EasyPermissions.hasPermissions(context, Constants.REQUECT_NEEDS)) {
            EasyPermissions.requestPermissions(this, getString(R.string.string_permission),
                    Constants.REQUECT_CODE_PERMISSION, Constants.REQUECT_NEEDS);
        }
    }
}
