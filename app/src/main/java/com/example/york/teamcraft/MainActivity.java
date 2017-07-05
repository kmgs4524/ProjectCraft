package com.example.york.teamcraft;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: ");

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up  the ViewPager with the section adapter.
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new Schedule_fragment(),"行事曆");
        adapter.addFragment(new Notebook_fragment(),"記事本");
        adapter.addFragment(new AddTeam_fragment(),"建立團隊");
        viewPager.setAdapter(adapter);
    }
}


