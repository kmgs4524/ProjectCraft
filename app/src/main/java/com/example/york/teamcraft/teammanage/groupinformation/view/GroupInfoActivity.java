package com.example.york.teamcraft.teammanage.groupinformation.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.targetfragment.TargetFragment;
import com.example.york.teamcraft.taskfragment.TaskFragment;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenter;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenterImpl;

public class GroupInfoActivity extends AppCompatActivity implements GroupInfoView{
    private GroupInfoPresenter groupInfoPresenter;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragTarget;
    private Fragment fragTask;

    private String groupName;
    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_manage_group);

        // get bundle data
        getPassedGroupData();
        // init toolbar
        initToolBar(groupName);
        // set TargetFragment TaskFragment
        setFragment();

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

    public void getPassedGroupData() {
        Bundle bundle = getIntent().getExtras();
        groupName =bundle.getString("name");
        groupId =bundle.getString("id");
    }

    public void setFragment() {
        // init fragment manager, transaction
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragTarget = TargetFragment.newInstance();
        fragTask = TaskFragment.newInstance(groupId);
        fragmentTransaction.add(R.id.team_activity_group_layout, fragTarget);
        fragmentTransaction.add(R.id.team_activity_group_layout, fragTask);
        fragmentTransaction.commit();
    }

}
