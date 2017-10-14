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
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenter;
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenterImpl;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupView{
    //view
    private EditText edtGroupName;
    private EditText edtGroupMember;
    private Button btnCreate;
    // presenter
    private CreateGroupPresenter createGroupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_create_group);
        initToolBar();
        initEdtText();
        initCreateBtn();
        createGroupPresenter = new CreateGroupPresenterImpl(this);
    }

    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("創建群組");
        Log.d("PersonalTasksActivity", "init ToolBar");
    }

    //設置EditText
    public void initEdtText() {
        edtGroupName = (EditText) findViewById(R.id.edt_group_name);
        edtGroupMember = (EditText) findViewById(R.id.edt_group_member);
    }

    public void initCreateBtn() {
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroupPresenter.createGroup(edtGroupName.getText().toString());
            }
        });
    }

    public void finishCreate() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createGroupPresenter = null;
    }
}
