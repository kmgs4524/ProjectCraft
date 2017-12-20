package com.example.york.teamcraft.addteammember.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addteammember.presenter.AddTeamMemberPresenter;
import com.example.york.teamcraft.addteammember.presenter.AddTeamMemberPresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddTeamMemberActivity extends AppCompatActivity implements AddTeamMemberView{
    // view
    @BindView(R.id.edt_team_member_email)
    EditText edtTeamMemberEmail;
    @BindView(R.id.txt_add_fail_message)
    TextView txtFailMessage;
    // presenter
    private AddTeamMemberPresenter addTeamMemberPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team_member);
        addTeamMemberPresenter = new AddTeamMemberPresenterImpl(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_add_team_member)
    public void addTeamMember() {
        addTeamMemberPresenter.addTeamMember(edtTeamMemberEmail.getText().toString());
    }


    @Override
    public void showFailMessage() {
        txtFailMessage.setText("找不到使用者");
    }

    @Override
    public void showSuccessfulMessage() {
        SuccessDialogFragment successDialogFragment = new SuccessDialogFragment();
        successDialogFragment.show(getSupportFragmentManager(), "SuccessDialogFragment");
    }


}
