package com.example.york.teamcraft.teammanage.view;

import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;

import com.example.york.teamcraft.R;

public class AddItemActivity extends AppCompatActivity {
    private EditText edtTitle;
    private EditText edtContent;
    private EditText edtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teammanage_activity_add_item);
        initToolBar();
        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtContent = (EditText) findViewById(R.id.edt_target_content);
        edtDate = (EditText) findViewById(R.id.edt_date);

    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("新增活動");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊

        Log.d("MainActivity", "init ToolBar");
    }
}
