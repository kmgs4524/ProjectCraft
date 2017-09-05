package com.example.york.teamcraft.teammanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.SignInActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*----- Pager相關元件 -------*/
    private SectionsPageAdapter mSectionsPageAdapter;   //繼承FragmentPageAdapter的adapter
    private ViewPager mViewPager;   //可讓使用者左右翻動頁面的class，此外必須提供PageAdapter來產生pages
    /*----------------------------*/

    /*----- Drawer相關元件 -------*/
    private String[] planetTitles;  //用來初始化navigation list的string array
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
//    private ListView drawerList;
    /*----------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_main);
        Log.d(TAG,"onCreate: ");

        initToolBar();  //初始化ToolBar
        initDrawer();   //初始化Drawer

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        //Set up  the ViewPager with the section adapter.
        mViewPager = (ViewPager)findViewById(R.id.container);
        setupViewPager(mViewPager);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);   //The one-stop shop for setting up this TabLayout with a ViewPager.
    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("團隊管理");
        Log.d("MainActivity", "init ToolBar");
    }

    //設置ViewPager
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        // 將Fragement加入 mFragmentList, 標題
        adapter.addFragment(new BoardFragment(),"佈告欄"); //加入Fragment
        adapter.addFragment(new GroupManageFragment(),"群組管理");  //加入Fragment
        adapter.addFragment(new TaskProgressFragment(),"任務進度"); //加入Fragment
        viewPager.setAdapter(adapter);  //設置ViewPager的adapter
    }

    //設置側邊欄
    private void initDrawer(){
        planetTitles = getResources().getStringArray(R.array.planets_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        drawerList = (ListView) findViewById(R.id.left_drawer);
        Log.d(TAG, navigationView.toString());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Toast.makeText(getApplicationContext(), "轉至個人管理", Toast.LENGTH_SHORT);
                // 先檢查點擊的item是否為checked
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                drawerLayout.closeDrawers();    //關閉drawer
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.drawer_personals:
                        intent = new Intent();
                        intent.setClass(MainActivity.this, com.example.york.teamcraft.personalsmanage.MainActivity.class);
                        startActivity(intent);
                        return  true;
                    case R.id.drawer_team:
                        intent = new Intent();
                        intent.setClass(MainActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
                        startActivity(intent);
                        return  true;
                    case R.id.drawer_account:
                        Toast.makeText(getApplicationContext(), "轉至帳戶資料", Toast.LENGTH_SHORT);
                        return  true;
                    default:
                        Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_SHORT);
                        return  true;
                }
            }
        });

    }

// 下列註解區塊為舊的listView用法
    //AdapterView.OnItemClickListener
//    private class DrawerItemClickListener implements android.widget.AdapterView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            selectItem(position);
//        }
//
//        /** Swaps fragments in the main content view */
//        private void selectItem(int position) {
//            if(position == 0){
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, com.example.york.teamcraft.personalsmanage.MainActivity.class);
//                startActivity(intent);
//            } else if(position == 1){
////                Intent intent = new Intent();
////                intent.setClass(MainActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
////                startActivity(intent);
//            }
//
//        }
//    }
}
