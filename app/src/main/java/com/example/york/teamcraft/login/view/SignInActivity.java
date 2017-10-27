package com.example.york.teamcraft.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.presenter.SignInPresenterImpl;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, SignInView {
    private static final String TAG = "SignInActivity";

    /*----------  後端登入 ----------*/
    private SignInPresenterImpl signInPresenter;
    //    private static String SEVER_CLIENT_ID = "351544429326-6g982pc3jarftp4017oi8gu526c5m70f.apps.googleusercontent.com";
    private static int RC_SIGN_IN = 123;    // Request Code
    /*-------------------------------*/

    //UI元件
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.edt_acct)  EditText edtAcct;   //帳號欄位
    @BindView(R.id.edt_pwd)  EditText edtPwd;    //密碼欄位
    @BindView(R.id.status_txtView)  TextView txtStatus; //登入狀態
    @BindView(R.id.name_txtView)  TextView txtName;   //姓名
    @BindView(R.id.txt_sign_up)  TextView txtSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        //設置UI
        initToolBar();  //ToolBar

        findViewById(R.id.signIn_btn).setOnClickListener(this); //登入Button
        findViewById(R.id.google_signIn_btn).setOnClickListener(this);  //Google帳號登入Button
        findViewById(R.id.signOut_btn).setOnClickListener(this);    //登出Button
        findViewById(R.id.txt_sign_up).setOnClickListener(this);    //登出Button
    }

    @Override
    public void onStart() {
        super.onStart();
        // Presenter
        signInPresenter = new SignInPresenterImpl(this);

        // 顯示登入的使用者狀態
        showStatus(signInPresenter.getCurrentUser());
    }

    @Override
    protected void onStop() {
        super.onStop();
        signInPresenter.destoryGoogleClient();
    }

    // 按下登入、登出的事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn_btn:
                // Email Sign In程序由SignInPresenter負責
                if (signInPresenter.getCurrentUser() == null) {
                    signInPresenter.signIn(edtAcct.getText().toString(), edtPwd.getText().toString());
                    break;
                } else {
                    break;
                }
            case R.id.google_signIn_btn:
                if (signInPresenter.getCurrentUser() == null) { // 尚未登入
                    // Google Sign In程序由SignInPresenter負責
                    signInPresenter.googleSignIn(RC_SIGN_IN); // 建立GoogleSignInApi的intent，並呼叫startActivityForResult(intent)
                    break;
                } else {    // 已登入
                    boolean[] exist = new boolean[1];
                    signInPresenter.confirmUserExist(); // 判斷是否註冊使用者資料
                    break;
                }
            case R.id.signOut_btn:
                // Sign Out程序由SignInPresenter負責
                signInPresenter.signOut();
                break;
            case R.id.txt_sign_up:
                signInPresenter.startSignUp();
        }
    }

    // 負責Google Sign In 的程序
    // 執行signInActivity.startActivityForResult(signInIntent, RC_SIGN_IN)後，自動呼叫此callback(onActivityResult());
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                signInPresenter.firebaseAuthWithGoogle(account);    // 將成功後取得的account交給Presenter轉換成Firebase User
            } else {
                // Google Sign In failed, update UI appropriately
            }
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    public void showStatus(FirebaseUser user) {
        if (user != null) {   //已登入
            txtStatus.setText("帳號登入中");
            txtName.setText(txtName.getText() + user.getEmail());
        } else { //未登入
            txtStatus.setText("尚未登入");
            txtName.setText("姓名：");
        }
    }

    public void showPwdEmptyMesg() {
        txtStatus.setVisibility(View.VISIBLE);
        txtStatus.setText("帳號或密碼不能是空白");
    }

    public void showPwdWrongMesg() {
        txtStatus.setVisibility(View.VISIBLE);
        txtStatus.setText("帳號或密碼錯誤");
    }

    //設置ToolBar為此activity的app bar
    private void initToolBar() {
        toolbar.setTitle("登入");
        setSupportActionBar(toolbar);
    }

}
