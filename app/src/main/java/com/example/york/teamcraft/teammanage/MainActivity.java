package com.example.york.teamcraft.teammanage;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.teammanage.taskprogress.TaskProgressFragment;
import com.example.york.teamcraft.teammanage.view.BoardFragment;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageFragment;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*----- Pager相關元件 -------*/
    private SectionsPageAdapter mSectionsPageAdapter;   //繼承FragmentPageAdapter的adapter
    private ViewPager mViewPager;   //可讓使用者左右翻動頁面的class，此外必須提供PageAdapter來產生pages
    /*----------------------------*/

    /*----- Drawer and ToolBar -------*/
    private String[] planetTitles;  //用來初始化navigation list的string array
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
//    private ListView drawerList;
    /*----------------------------*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_main);
        Log.d(TAG,"onCreate: ");

        initDrawer();   //初始化Drawer
        initToolBar();  //初始化ToolBar

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
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); // 給定的Activity會連接給定的DrawerLayout, 使用此constructor會設置listener點擊icon便切換drawer
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout

        Log.d("MainActivity", "init ToolBar");
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
                        return  true;
                    case R.id.drawer_account:
                        intent = new Intent();
                        intent.setClass(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                        return  true;
                    default:
                        Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_SHORT);
                        return  true;
                }
            }
        });

    }

    //設置ViewPager
    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        // 將 Fragement 加入 mFragmentList, 標題
        adapter.addFragment(new BoardFragment(),"佈告欄"); //加入Fragment
        adapter.addFragment(new GroupManageFragment(),"群組管理");  //加入Fragment
        adapter.addFragment(new TaskProgressFragment(),"任務進度"); //加入Fragment
        viewPager.setAdapter(adapter);  //設置ViewPager的adapter
    }

}
