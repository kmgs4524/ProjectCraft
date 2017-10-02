package com.example.york.teamcraft.teammanage.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.Work;

public class ActContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teammanage_activity_act_content);

        Bundle bundle = getIntent().getExtras();
        Work work = bundle.getParcelable("Work");
        Log.d("work", work.getTopic());
    }
}
