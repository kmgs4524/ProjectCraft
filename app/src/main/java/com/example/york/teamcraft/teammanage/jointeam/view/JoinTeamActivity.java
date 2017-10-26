package com.example.york.teamcraft.teammanage.jointeam.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.MainActivity;
import com.example.york.teamcraft.teammanage.jointeam.viewmodel.JoinExistTeam;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JoinTeamActivity extends AppCompatActivity implements JoinTeamView{
    // view
    @BindView(R.id.edt_team_id) EditText edtTeamId;
    @BindView(R.id.btn_confirm_join_team) Button btnConfirm;
    @BindView(R.id.txt_team_not_exist_message) TextView txtMessg;    // 此Team Id不存在的text
    // view model
    private JoinExistTeam joinExistTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_team);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_confirm_join_team)
    public void confirm() {
        joinExistTeam = new JoinExistTeam(this);
        joinExistTeam.checkTeam(edtTeamId.getText().toString());
    }

    @Override
    public void showTextMessage() {
        txtMessg.setVisibility(View.VISIBLE);
    }

    @Override
    public void startTeamMainActivity() {
        Intent intent = new Intent();
        intent.setClass(this, MainActivity.class);
        startActivity(intent);
    }

}
