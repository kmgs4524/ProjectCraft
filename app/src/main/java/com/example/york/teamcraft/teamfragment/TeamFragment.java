package com.example.york.teamcraft.teamfragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager pager;
    private TeamPagerAdapter adapter;

    public static TeamFragment newInstance() {
        Bundle args = new Bundle();

        TeamFragment fragment = new TeamFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        pager = (ViewPager) view.findViewById(R.id.pager);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        adapter = new TeamPagerAdapter(getChildFragmentManager());

        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        return view;
    }

}
