package com.example.york.teamcraft.teammanage.groupinformation;

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
    private EditText edtGroupLeader;
    private EditText edtTargeDate;
    private EditText edtTargetTime;
    private EditText edtTargetContent;
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
        edtGroupLeader = (EditText) findViewById(R.id.edt_group_leader);
        edtTargeDate = (EditText) findViewById(R.id.edt_target_date);
        edtTargetTime = (EditText) findViewById(R.id.edt_target_time);
        edtTargetContent = (EditText) findViewById(R.id.edt_target_content);
    }

    private void initBtn() {
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(CreateGroupActivity.this, GroupInfoActivity.class);
                String groupName = new String(edtGroupName.getText().toString());
                Bundle bundle = new Bundle();   //用來打包傳入GroupActivity資料的Bundle
                bundle.putString("DATA_GROUP_NAME", groupName);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }


}
