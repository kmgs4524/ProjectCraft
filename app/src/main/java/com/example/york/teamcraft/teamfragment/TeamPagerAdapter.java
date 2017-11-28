package com.example.york.teamcraft.teamfragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageFragment;

/**
 * Created by York on 2017/11/28.
 */

public class TeamPagerAdapter extends FragmentPagerAdapter{

    public TeamPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment selectedFrag = new GroupManageFragment();
        switch (position) {
            case 0:
                selectedFrag = new GroupManageFragment();
                break;
            case 1:
                selectedFrag = new GroupManageFragment();
        }
        return selectedFrag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";

        switch (position) {
            case 0:
                title = "成員";
                break;
            case 1:
                title = "群組";
                break;
        }

        return title;
    }
}
