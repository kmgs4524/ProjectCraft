package com.example.york.teamcraft.personalsmanage.view;

import android.content.Intent;
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

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.personalsmanage.presenter.PersonalTasksPresenterImpl;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.view.SetDrawer;

import java.util.ArrayList;


//個人管理主頁面
public class PersonalTasksActivity extends AppCompatActivity implements PersonalTasksView{
    private static final String TAG = "PersonalTasksActivity";
    /*----- Drawer and ToolBar -------*/
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    /*----- RecyclerView -------*/
    private RecyclerView recyclerView;
    private ItemViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private FloatingActionButton btnNote;

    private PersonalTasksPresenterImpl personalTasksPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personals_activity_main);
        Log.d(TAG,"onCreate: ");

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //設置UI
        initToolBar();
        initDrawer();   //Drawer

        personalTasksPresenter = new PersonalTasksPresenterImpl(this);
        personalTasksPresenter.readData();

    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("個人事項");
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); // 給定的Activity會連接給定的DrawerLayout, 使用此constructor會設置listener點擊icon便切換drawer
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout

        Log.d("PersonalTasksActivity", "init ToolBar");
    }

    //設置側邊欄
    private void initDrawer(){
        SetDrawer setDrawer = new SetDrawer();
        setDrawer.setLeftDrawer(this, drawerLayout);
    }

    // 初始化RecyclerView的資料
    public void initRecycleView(ArrayList<ContentTask> list) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);  // 建立RecyclerView的LayoutManager
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ItemViewAdapter(list, this);   // 將Cursor放入ItemViewAdapter
//        adapter.setClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int pos = recyclerView.indexOfChild(v);
//                Log.d(TAG, Integer.toString(pos));
//            }
//        });

        recyclerView.setAdapter(adapter);

        // 加入item之間的分隔線
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

}


