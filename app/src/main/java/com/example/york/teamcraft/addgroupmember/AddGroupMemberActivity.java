package com.example.york.teamcraft.addgroupmember;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.york.teamcraft.R;

public class AddGroupMemberActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group_member);

        getGroupIdBundle();
    }

    public void getGroupIdBundle() {
        try {
            Bundle groupIdBundle = getIntent().getExtras();
            String groupId = groupIdBundle.getString("GROUP_ID");
            Log.d("getGroupIdBundle", "getGroupIdBundle: " + groupId);
        } catch (NullPointerException e) {
            Log.d("getGroupIdBundle", "getGroupIdBundle: " + e.toString());
        }
    }
}
