package com.example.york.teamcraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class GroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_manage_group);
        Intent intent = getIntent();    //取得CreateGroupActivity傳來的intent
        Bundle bundle = intent.getExtras(); //從intent取出CreateGroupActivity傳過來的bundle
        String groupName = bundle.getString("DATA_GROUP_NAME");
        initToolBar(groupName);
    }

    //設置ToolBar
    private void initToolBar(String groupName){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(groupName);
        Log.d("MainActivity", "init ToolBar");
    }
}
