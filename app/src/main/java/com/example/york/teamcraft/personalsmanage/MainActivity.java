package com.example.york.teamcraft.personalsmanage;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;

import java.util.ArrayList;


//個人管理主頁面
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*----- Drawer and ToolBar -------*/
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;
    /*----------------------------*/

    /*----- RecyclerView -------*/
    private RecyclerView recyclerView;
    private ItemViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    /*--------------------------------*/

    private FloatingActionButton btnNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personals_activity_main);
        Log.d(TAG,"onCreate: ");


        //設置UI
        initDrawer();   //Drawer
        initToolBar();
        initRecycleView();
//        initDB(dataSet);

    }

    // 設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("個人記事");
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); // 給定的Activity會連接給定的DrawerLayout, 使用此constructor會設置listener點擊icon便切換drawer
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar左上角的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout

        Log.d("MainActivity", "init ToolBar");
    }

    //設置側邊欄
    private void initDrawer(){
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
//        drawerList = (ListView) findViewById(R.id.left_drawer);
        Log.d(TAG, navigationView.toString());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 先檢查點擊的item是否為checked
                if(item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                drawerLayout.closeDrawers();    // 點擊Drawer的選項後，關閉drawer
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.drawer_personals:
                        return  true;
                    case R.id.drawer_team:
                        intent = new Intent();
                        intent.setClass(MainActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
                        startActivity(intent);
                        return  true;
                    case R.id.drawer_account:
                        intent = new Intent();
                        intent.setClass(MainActivity.this, SignInActivity.class);
                        startActivity(intent);
                        return  true;
                    default:
                        return  true;
                }

            }
        });
        Log.d(TAG, "init Drawer");
    }

    public void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);  // 建立RecyclerView的LayoutManager
        recyclerView.setLayoutManager(layoutManager);

//        adapter = new ItemViewAdapter(dataSet, cursor, this);   // 將Cursor放入ItemViewAdapter
//        adapter.setClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos = recyclerView.indexOfChild(v);
//                Log.d(TAG, Integer.toString(pos));
//            }
//        });
//
//        recyclerView.setAdapter(adapter);

        // 加入item之間的分隔線
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }


    // 當Activity離開螢幕進入Stop狀態後，再次顯現在螢幕時會呼叫此方法
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "Call onRestart");
    }
}


