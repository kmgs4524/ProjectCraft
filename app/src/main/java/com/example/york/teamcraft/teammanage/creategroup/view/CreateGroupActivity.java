package com.example.york.teamcraft.teammanage.creategroup.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenter;
import com.example.york.teamcraft.teammanage.creategroup.presenter.CreateGroupPresenterImpl;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateGroupActivity extends AppCompatActivity implements CreateGroupView{
    //view
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.edt_group_name) EditText edtGroupName;
    @BindView(R.id.txt_group_member) TextView txtGroupMember;
    @BindView(R.id.grid_view_selected_member) GridView gridSelectedMember;
    @BindView(R.id.spin_team_member) Spinner spnTeamMember;
    @BindView(R.id.btn_add_group_member) Button btnAddMember;
    @BindView(R.id.btn_create) Button btnCreate;
    // presenter
    private CreateGroupPresenter createGroupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_create_group);
        ButterKnife.bind(this);
        initToolBar();
        createGroupPresenter = new CreateGroupPresenterImpl(this);
        createGroupPresenter.setSpinMenu();
        initCreateBtn();
    }

    // 設置ToolBar
    private void initToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("創建群組");
        Log.d("PersonalTasksActivity", "init ToolBar");
    }

    @Override
    public void initGridView() {

    }

    // 設定Spinner
    public void setSpinMenu(final ArrayList<GroupMember> memList) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<GroupMember> spinMenuAdapter = new SpinMenuAdapter(this, R.layout.spinner_init, memList);
        // Specify the layout to use when the list of choices appears
        spinMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnTeamMember.setAdapter(spinMenuAdapter);
        final CreateGroupActivity activity = this;
        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 將spinner被選擇的項目加入暫存的list
                createGroupPresenter.saveSpinnerData(memList.get(spnTeamMember.getSelectedItemPosition()));
                Log.d("setSpinMenu", "size: " + createGroupPresenter.getSaveGroupMember().getMemList().size());
                gridSelectedMember.setAdapter(new SelectedViewAdapter(activity, createGroupPresenter.getSaveGroupMember().getMemList()));
                // 設定點擊GridView項目的listener
                gridSelectedMember.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("setSpinMenu:", "position: " + position);
                        createGroupPresenter.getSaveGroupMember().getMemList().remove(position);    // 項目被點擊後就從儲存被選擇成員的list中刪除該成員
                        gridSelectedMember.setAdapter(new SelectedViewAdapter(activity, createGroupPresenter.getSaveGroupMember().getMemList()));   // 刪除後再次更新GridView
                    }
                });
            }
        });
    }

    public void initCreateBtn() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createGroupPresenter.createGroup(edtGroupName.getText().toString(), createGroupPresenter.getSaveGroupMember().getMemList());
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
