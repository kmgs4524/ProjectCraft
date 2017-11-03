package com.example.york.teamcraft.teammanage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsfragment.view.PersonalsFragment;
import com.example.york.teamcraft.teammanage.board.view.BoardFragment;
import com.example.york.teamcraft.teammanage.groupfragment.view.GroupManageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    /*----- Pager相關元件 -------*/
    private SectionsPageAdapter mSectionsPageAdapter;   //繼承FragmentPageAdapter的adapter
    private ViewPager mViewPager;   //可讓使用者左右翻動頁面的class，此外必須提供PageAdapter來產生pages
    /*----------------------------*/

    /*----- Drawer and ToolBar -------*/
    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_main);

        ButterKnife.bind(this);
//        initToolBar();  //初始化ToolBar

        bottomNavView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFrag = null;
                switch (item.getItemId()) {
                    case R.id.action_item_personals:
                        selectedFrag = PersonalsFragment.newInstance();
//                        selectedFrag = ScheduleFragment.newInstance();
                        break;
                    case R.id.action_item_team:
                        selectedFrag = GroupManageFragment.newInstance();
                        break;
                    case R.id.action_item_board:
                        selectedFrag = BoardFragment.newInstance();
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.main_content, selectedFrag);
                transaction.commit();
                return true;
            }
        });

        // 進入MainActivity後，預設顯示PersonalsFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_content, PersonalsFragment.newInstance());
//        transaction.replace(R.id.main_content, ScheduleFragment.newInstance());
        transaction.commit();

        //Set up  the ViewPager with the section adapter.
//        mViewPager = (ViewPager)findViewById(R.id.container);
//        setupViewPager(mViewPager);

//        TabLayout tabLayout=(TabLayout)findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);   //The one-stop shop for setting up this TabLayout with a ViewPager.

    }

    //設置ToolBar
//    private void initToolBar(){
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("團隊管理");
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); // 給定的Activity會連接給定的DrawerLayout, 使用此constructor會設置listener點擊icon便切換drawer
//        drawerLayout.addDrawerListener(drawerToggle);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
//        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
//        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout
//    }

}
