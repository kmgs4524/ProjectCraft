package com.example.york.teamcraft.teammanage.groupinformation.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addgroupmember.view.AddGroupMemberActivity;
import com.example.york.teamcraft.targetfragment.view.TargetFragment;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.taskfragment.view.PassDataListener;
import com.example.york.teamcraft.taskfragment.view.TaskFragment;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenter;
import com.example.york.teamcraft.teammanage.groupinformation.presenter.GroupInfoPresenterImpl;

// 群組任務的畫面，包含TargetFragment、TaskFragment
public class GroupInfoActivity extends AppCompatActivity implements GroupInfoView, PassDataListener {
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
    public void initToolBar(String groupName) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(groupName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_group_member_action_button, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_new_group_member:
                Intent intent = new Intent();
                Bundle groupIdBundle = new Bundle();
                // 傳入group id
                groupIdBundle.putString("GROUP_ID", groupId);
                intent.putExtras(groupIdBundle);
                intent.setClass(this, AddGroupMemberActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return true;
    }

    public void getPassedGroupData() {
        Bundle bundle = getIntent().getExtras();
        groupName = bundle.getString("name");
        groupId = bundle.getString("id");
        Log.d("GroupInfoActivity", "getPassedGroupData: " + groupId);
    }

    public void setFragment() {
        // init fragment manager, transaction
        fragmentManager = this.getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        Log.d("GroupInfoActivity", "setFragment: " + groupId);
        fragTarget = TargetFragment.newInstance(groupId, null, null);
        fragTask = TaskFragment.newInstance(groupId);
        fragmentTransaction.add(R.id.team_activity_group_layout, fragTarget, "TARGET_FRAG");
        fragmentTransaction.add(R.id.team_activity_group_layout, fragTask, "TASK_FRAG") ;
        fragmentTransaction.commit();
    }

    @Override
    public void passData(String groupTaskName, ContentTask contentTask) {
        TargetFragment fragTarget = TargetFragment.newInstance(groupId, groupTaskName, contentTask);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_grouptask_detail, fragTarget, "TARGET_FRAG")
                    .commit();
    }

}
