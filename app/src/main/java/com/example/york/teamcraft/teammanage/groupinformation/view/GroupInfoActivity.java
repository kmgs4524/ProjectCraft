package com.example.york.teamcraft.teammanage.groupinformation.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenter;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenterImpl;

public class GroupInfoActivity extends AppCompatActivity implements GroupInfoView{
    private GroupInfoPresenter groupInfoPresenter;
    private Fragment fragTarget;
    private Fragment fragTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_manage_group);

        // get bundle
        Intent intent = getIntent();    //取得CreateGroupActivity傳來的intent
        Bundle bundle = intent.getExtras(); //從intent取出CreateGroupActivity傳過來的bundle

        initToolBar(bundle.getString("name"));

        fragTask = this.getSupportFragmentManager().findFragmentById(R.id.fragment_group_task);
        setFragArg(bundle.getString("id"));

        // init presenter
        groupInfoPresenter = new GroupInfoPresenterImpl(this);

    }

    //設置ToolBar
    public void initToolBar(String groupName){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(groupName);
        Log.d("MainActivity", "init ToolBar");
    }

    public void setFragArg(String id) {
        fragTask.getArguments().putString("id", id);
//        Bundle bundle = new Bundle();
//        bundle.putString("id", id);
//        fragTask.setArguments(bundle);
    }

}
