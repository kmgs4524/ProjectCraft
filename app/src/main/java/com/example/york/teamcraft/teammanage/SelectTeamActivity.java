package com.example.york.teamcraft.teammanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.createteam.CreateTeamActivity;

public class SelectTeamActivity extends AppCompatActivity {
    private Button btnCreateTeam;
    private Button btnJoinTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_select_team);

        // find view
        btnCreateTeam = (Button) findViewById(R.id.btn_create_team);
        btnJoinTeam = (Button) findViewById(R.id.btn_join_team);

        // set listener
        btnCreateTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(SelectTeamActivity.this, CreateTeamActivity.class);
                startActivity(intent);
            }
        });

        btnJoinTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // join team...
            }
        });
    }
}
