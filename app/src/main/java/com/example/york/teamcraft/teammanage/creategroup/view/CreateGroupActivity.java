package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenter;
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenterImpl;
import com.example.york.teamcraft.teammanage.groupinformation.view.GroupInfoActivity;

import java.util.ArrayList;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupView{
    //view
    private EditText edtGroupName;
    private Spinner spnTeamMember;
    private Button btnCreate;
    // presenter
    private CreateGroupPresenter createGroupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_create_group);
        initToolBar();
        initInputView();
        initCreateBtn();
        setSpinMenuData();
        createGroupPresenter = new CreateGroupPresenterImpl(this);
    }

    // 設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("創建群組");
        Log.d("PersonalTasksActivity", "init ToolBar");
    }

    // 設置EditText
    public void initInputView() {
        edtGroupName = (EditText) findViewById(R.id.edt_group_name);
        spnTeamMember = (Spinner) findViewById(R.id.spin_team_member);
    }

    public void setSpinMenuData() {
        ArrayList<Member> list = new ArrayList<>();
        list.add(new Member("李侑乘", "組長"));
        list.add(new Member("李白", "組員"));
        list.add(new Member("小球球", "組員"));
        Spinner spinner = (Spinner) findViewById(R.id.spin_team_member);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<Member> spinMenuAdapter = new SpinMenuAdapter(this, R.layout.spinner_init, list);
        // Specify the layout to use when the list of choices appears
        spinMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(spinMenuAdapter);
    }

    public void initCreateBtn() {
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroupPresenter.createGroup(edtGroupName.getText().toString());
            }
        });
    }

    public void finishCreate() {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        createGroupPresenter = null;
    }
}
