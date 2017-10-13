package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;

public class CreateGroupActivity extends AppCompatActivity {
    private EditText edtGroupName;
    private EditText edtGroupMember;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_create_group);
        initToolBar();
        initEdtText();
        initBtn();
    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("創建群組");
        Log.d("MainActivity", "init ToolBar");
    }

    //設置EditText
    private void initEdtText() {
        edtGroupName = (EditText) findViewById(R.id.edt_group_name);
        edtGroupMember = (EditText) findViewById(R.id.edt_group_member);
    }

    private void initBtn() {
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
