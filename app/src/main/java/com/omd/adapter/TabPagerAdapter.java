package com.omd.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.omd.fragment.TabFragment;

public class TabPagerAdapter extends FragmentPagerAdapter {

    private int size;

    public TabPagerAdapter(FragmentManager fm, int size) {
        super(fm);
        this.size = size;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance(position + "");
    }

    @Override
    public int getCount() {
        return size;
    }
}
