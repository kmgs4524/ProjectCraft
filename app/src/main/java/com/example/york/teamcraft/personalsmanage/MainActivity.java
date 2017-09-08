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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.example.york.teamcraft.R;

import java.util.ArrayList;


//個人管理主頁面
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    /*----- Drawer and ToolBar -------*/
    private String[] planetTitles;  //用來初始化navigation list的string array
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    /*----------------------------*/

    /*----- RecyclerView -------*/
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    /*--------------------------------*/

    private FloatingActionButton btnNote;

    private ArrayList<Note> dataSet =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personals_activity_main);
        Log.d(TAG,"onCreate: ");

        addNote(dataSet);   // 新增資料到ArrayList

        //設置UI
        initDrawer();   //Drawer
        initToolBar();
        initRecycleView();
//        initDB(dataSet);
        btnNote = (FloatingActionButton) findViewById(R.id.fab);
        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });
    }

    // 設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("個人記事");
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

// 下列註解區塊為舊的listView用法
//        //為drawerList設置adapter
//        drawerList.setAdapter(new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, planetTitles));
//        //設置drawerList的listener
//        drawerList.setOnItemClickListener(new MainActivity.DrawerItemClickListener());
        Log.d(TAG, "init Drawer");
    }

    public void initRecycleView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Cursor cursor = initDB();

        adapter = new ItemViewAdapter(dataSet, cursor, this);

        recyclerView.setAdapter(adapter);
        // 加入item之間的分隔線
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void addNote(ArrayList<Note> list) {
        Note note = new Note("Android設計手冊", "如何從零開始設計Android, 你想的到的都在這邊", "9/7", 1);
        Note note2 = new Note("React設計手冊", "如何從零開始設計ReactNative, 你想的到的都在這邊", "9/8", 2);
        Note note3 = new Note("iOS設計手冊", "如何從零開始設計iOS, 你想的到的都在這邊", "9/9", 3);

        list.add(note);
        list.add(note2);
        list.add(note3);
    }

    public Cursor initDB(){
        NotesDbHelper helper = NotesDbHelper.getInstance(this);
        Cursor cursor = helper.getReadableDatabase().query(
                NotesContract.Notes.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        return cursor;
//        cursor.moveToFirst();
//        String itemTitle = cursor.getString(
//                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_TITLE)
//        );
//        String itemContent = cursor.getString(
//                cursor.getColumnIndexOrThrow(NotesContract.Notes.COLUMN_NAME_CONTENT)
//        );
//        Log.d("Title", itemTitle);
//        Log.d("Content", itemContent);
    }


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
////                Intent intent = new Intent();
////                intent.setClass(MainActivity.this, MainActivity.class);
////                startActivity(intent);
//            } else if(position == 1){
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
//                startActivity(intent);
//            }
//        }
//    }

}


