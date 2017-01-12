package com.common.widget.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

public class FragmentManager {

	private int containerId;
	private android.support.v4.app.FragmentManager fm;
	private ArrayList<Fragment> fragments;
	
	private static FragmentManager controller;

	public static FragmentManager getInstance(FragmentActivity activity, int containerId, ArrayList<Fragment> fragments) {
		if (controller == null) {
			controller = new FragmentManager(activity, containerId, fragments);
		}
		return controller;
	}
	
	public static void onDestroy() {
		if (null != controller) {
			controller = null;
		}
	}

	public FragmentManager(FragmentActivity activity, int containerId, ArrayList<Fragment> fragments) {
		this.containerId = containerId;
		fm = activity.getSupportFragmentManager();
		initFragment(fragments);
	}

	private void initFragment(ArrayList<Fragment> fragments) {
		this.fragments = fragments;
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			ft.add(containerId, fragment);
		}
		ft.commitAllowingStateLoss();
		showFragment(0);
	}

	public void showFragment(int position) {
		hideFragments();
		Fragment fragment = fragments.get(position);
		FragmentTransaction ft = fm.beginTransaction();
		ft.show(fragment);
		ft.commitAllowingStateLoss();
	}
	
	public void hideFragments() {
		FragmentTransaction ft = fm.beginTransaction();
		for(Fragment fragment : fragments) {
			if(fragment != null) {
				ft.hide(fragment);
			}
		}
		ft.commitAllowingStateLoss();
	}
	
	public Fragment getFragment(int position) {
		return fragments.get(position);
	}
}