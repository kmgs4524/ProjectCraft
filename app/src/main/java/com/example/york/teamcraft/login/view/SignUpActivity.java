package com.example.york.teamcraft.login.view;

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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static String TAG = "SignUpActivity";

    private FirebaseAuth auth;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private Button confirmBtn;
    private EditText emailEdt;
    private EditText passwordEdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();

        initToolBar();  //ToolBar

        emailEdt = (EditText) findViewById(R.id.email_edt);
        passwordEdt = (EditText) findViewById(R.id.password_edt);
        confirmBtn = (Button) findViewById(R.id.confirm_btn);

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
            String email = emailEdt.getText().toString();
            String password = passwordEdt.getText().toString();

            createUser(email, password);
        }
    };

    @Override
    protected void onStart(){
        super.onStart();
        //確認使用者是否登入中
        FirebaseUser currentUser = auth.getCurrentUser();
        //updateUI(currentUser);
    }

    public void createUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }



}
