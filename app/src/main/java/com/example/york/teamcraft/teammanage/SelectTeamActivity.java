package com.example.york.teamcraft.teammanage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.createteam.CreateTeamActivity;
import com.example.york.teamcraft.teammanage.jointeam.view.JoinTeamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectTeamActivity extends AppCompatActivity {
    @BindView(R.id.btn_create_team) Button btnCreateTeam;
    @BindView(R.id.btn_join_team) Button btnJoinTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_select_team);
        ButterKnife.bind(this);
    }

    @OnClick({
            R.id.btn_create_team,
            R.id.btn_join_team
    })
    public void chosenBtnClicked(Button button) {
        Intent intent = new Intent();
        switch (button.getId()) {
            case R.id.btn_create_team:
                intent.setClass(SelectTeamActivity.this, CreateTeamActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_join_team:
                intent.setClass(SelectTeamActivity.this, JoinTeamActivity.class);
                startActivity(intent);
                break;
        }
    }
}
