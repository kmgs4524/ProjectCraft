package com.example.york.teamcraft.teammanage.tasktable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.schedulefragment.ScheduleFragment;

public class TaskTableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_table);

        Fragment scheduleFragment = ScheduleFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.linea_layout_main_content, scheduleFragment);
        transaction.commit();
    }
}
