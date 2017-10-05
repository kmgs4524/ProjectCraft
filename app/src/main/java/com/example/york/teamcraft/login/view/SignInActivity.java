package com.example.york.teamcraft.login.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.presenter.SignInPresenterImpl;
import com.example.york.teamcraft.personalsmanage.MainActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
        View.OnClickListener, SignInView {
    private static final String TAG = "SignInActivity";

    /*----------  後端登入 ----------*/
    private SignInPresenterImpl signInPresenter;
    //    private static String SEVER_CLIENT_ID = "351544429326-6g982pc3jarftp4017oi8gu526c5m70f.apps.googleusercontent.com";
    private static int RC_SIGN_IN = 123;    // Request Code
    /*-------------------------------*/

    //UI元件
    private EditText edtAcct;   //帳號欄位
    private EditText edtPwd;    //密碼欄位
    private TextView txtStatus; //登入狀態
    private TextView txtName;   //姓名

    /*----- Drawer相關元件 -------*/
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    /*----------------------------*/

    @Override
    public void onStart() {
        super.onStart();
        // Presenter
        signInPresenter = new SignInPresenterImpl(this);

        // 顯示登入的使用者狀態
        showStatus(signInPresenter.getCurrentUser());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //設置UI
        initToolBar();  //ToolBar
        initDrawer();   //Drawer

        txtStatus = (TextView) findViewById(R.id.status_txtView);   // 登入狀態
        txtName = (TextView) findViewById(R.id.name_txtView);   //

        edtAcct = (EditText) findViewById(R.id.edt_acct);
        edtPwd = (EditText) findViewById(R.id.edt_pwd);
        findViewById(R.id.signIn_btn).setOnClickListener(this); //登入Button
        findViewById(R.id.google_signIn_btn).setOnClickListener(this);  //Google帳號登入Button
        findViewById(R.id.signOut_btn).setOnClickListener(this);    //登出Button
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

    //設置ToolBar為此activity的app bar
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    //設置側邊欄
    private void initDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        Log.d(TAG, navigationView.toString());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 先檢查點擊的item是否為checked
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }

                drawerLayout.closeDrawers();    // 點擊Drawer的選項後，關閉drawer
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.drawer_personals:
                        intent = new Intent();
                        intent.setClass(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.drawer_team:
                        intent = new Intent();
                        intent.setClass(SignInActivity.this, com.example.york.teamcraft.teammanage.MainActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.drawer_account:
                        return true;
                    default:
                        return true;
                }

            }
        });
        Log.d(TAG, "init Drawer");
    }


}
