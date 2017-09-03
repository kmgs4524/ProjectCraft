package com.example.york.teamcraft.teammanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.SignInActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*----- Pager相關元件 -------*/
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    /*----------------------------*/

    /*----- Drawer相關元件 -------*/
    private String[] planetTitles;  //用來初始化navigation list的string array
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    /*----------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_main);
        Log.d(TAG,"onCreate: ");

        initToolBar();
        initDrawer();   //Drawer

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up  the ViewPager with the section adapter.
        mViewPager=(ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("團隊管理");
        Log.d("MainActivity", "init ToolBar");
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new BoardFragment(),"佈告欄");
        adapter.addFragment(new GroupManageFragment(),"群組管理");
        adapter.addFragment(new TaskProgressFragment(),"任務進度");
        viewPager.setAdapter(adapter);
    }

    //設置側邊欄
    private void initDrawer(){
        planetTitles = getResources().getStringArray(R.array.planets_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        //為drawerList設置adapter
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, planetTitles));
        //設置drawerList的listener
        drawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());
    }

    //AdapterView.OnItemClickListener
    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

        /** Swaps fragments in the main content view */
        private void selectItem(int position) {
            if(position == 0){
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, com.example.york.teamcraft.personalsmanage.MainActivity.class);
                startActivity(intent);
            } else if(position == 1){
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
//                startActivity(intent);
            }

        }
    }
}
