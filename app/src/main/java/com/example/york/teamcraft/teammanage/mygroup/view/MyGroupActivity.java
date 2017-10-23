package com.example.york.teamcraft.teammanage.mygroup.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.targetfragment.view.TargetFragment;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.view.PassDataListener;
import com.example.york.teamcraft.taskfragment.view.TaskFragment;

public class MyGroupActivity extends AppCompatActivity implements MyGroupView, PassDataListener {
    private FragmentManager fragmentManager;
    private Fragment fragTarget;
    private Fragment fragTask;

    private String groupName;
    private String groupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_my_group);

        // get bundle data
        getPassedGroupData();
        // init toolbar
        initToolBar(groupName);
        // set TargetFragment TaskFragment
        setFragment();
    }

    public void initToolBar(String groupName) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(groupName);
    }

    public void getPassedGroupData() {
        try{
            Bundle bundle = getIntent().getExtras();
            groupId = bundle.getString("id");
        } catch (Exception e) {
            Log.d("passed", e.getMessage());
        }
    }

    public void setFragment() {
        // init fragment manager, transaction
        fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragTarget = TargetFragment.newInstance(null, null);
        fragTask = TaskFragment.newInstance(groupId);

        fragmentTransaction.add(R.id.linearLayout_my_group, fragTarget, "TARGET_FRAG");
        fragmentTransaction.add(R.id.linearLayout_my_group, fragTask, "TASK_FRAG") ;
        fragmentTransaction.commit();
    }

    @Override
    public void passData(String groupTaskName, ContentTask contentTask) {
        TargetFragment fragTarget = TargetFragment.newInstance(groupTaskName, contentTask);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_grouptask_detail, fragTarget, "TARGET_FRAG")
                .commit();
    }
}
