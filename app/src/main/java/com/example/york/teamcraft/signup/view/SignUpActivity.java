package com.example.york.teamcraft.signup.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.signup.viewmodel.CreateUser;
import com.example.york.teamcraft.teammanage.SelectTeamActivity;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpActivity extends AppCompatActivity implements SignUpView{
    private static String TAG = "SignUpActivity";
    // view
    private Button confirmBtn;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPwd;
    // view model
    private CreateUser createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initToolBar();  //ToolBar
        // find view
        edtName = (EditText) findViewById(R.id.edt_user_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPwd = (EditText) findViewById(R.id.edt_pwd);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);
        // set listener
        confirmBtn.setOnClickListener(confirmBtnOnClick);

    }

    //設置ToolBar為此activity的app bar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("註冊帳號");
        setSupportActionBar(toolbar);
    }

    private View.OnClickListener confirmBtnOnClick = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            String email = edtEmail.getText().toString();
            String password = edtPwd.getText().toString();
            String name = edtName.getText().toString();
            createUser = new CreateUser(SignUpActivity.this);
            createUser.createUser(email, password, name);
        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        //確認使用者是否登入中
//        FirebaseUser currentUser = auth.getCurrentUser();
    }

    public void startSelectActivity() {
        Intent intent = new Intent();
        intent.setClass(this, SelectTeamActivity.class);
        startActivity(intent);
    }

}
