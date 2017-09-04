package com.example.york.teamcraft.teammanage;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/7/4.
 */

public class SectionsPageAdapter extends FragmentPagerAdapter { //FragmentPagerAdapter: 若fragment並不在可見的狀態時，依然會儲存在記憶體中，因此可能會耗費大量記憶體

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public SectionsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }

    // 繼承FragmentPagerAdapter必定實作的方法
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    // 繼承FragmentPagerAdapter必定實作的方法
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
