package com.example.york.teamcraft;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,
                                                                 View.OnClickListener {
    private static String SEVER_CLIENT_ID = "351544429326-6g982pc3jarftp4017oi8gu526c5m70f.apps.googleusercontent.com";
    private static int RC_SIGN_IN = 123;
    private static final String TAG = "SignInActivity";

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();    //Firebase Authentication Instance
    private GoogleApiClient mGoogleApiClient;

    //UI元件
    private AutoCompleteTextView edtAcct;   //帳號欄位
    private EditText edtPwd;    //密碼欄位
    private TextView txtStatus; //登入狀態
    private TextView txtName;   //姓名

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //建立GoogleSignInOptions
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(SEVER_CLIENT_ID)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        txtStatus = (TextView) findViewById(R.id.status_txtView);
        txtName = (TextView) findViewById(R.id.name_txtView);

        edtAcct = (AutoCompleteTextView) findViewById(R.id.account_edt);
        edtPwd = (EditText) findViewById(R.id.password_edt);
        findViewById(R.id.signIn_btn).setOnClickListener(this); //登入Button
        findViewById(R.id.google_signIn_btn).setOnClickListener(this);  //Google帳號登入Button
        findViewById(R.id.signOut_btn).setOnClickListener(this);    //登出Button
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signIn_btn:
                signIn();
                break;
            case R.id.google_signIn_btn:
                googleSignIn();
                break;
            case R.id.signOut_btn:
                signOut();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // 檢查User是否登入中
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void signIn() {
        String acct = edtAcct.getText().toString(); //帳號(Email)
        String pwd = edtPwd.getText().toString();   //密碼

        mAuth.signInWithEmailAndPassword(acct, pwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser currentUser = mAuth.getCurrentUser();
                            updateUI(currentUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = result.getSignInAccount();
//            FirebaseUser user = mAuth.getCurrentUser();
//            updateUI(user);
            firebaseAuthWithGoogle(account);
        } else {
            // Google Sign In failed, update UI appropriately
            // ...
        }
    }

    //登出
    private  void signOut(){
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
        updateUI(null); //User已登出，更新UI
    }

    //更新UI
    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){   //已登入
            txtStatus.setText("帳號登入中");
            txtName.setText(txtName.getText() + currentUser.getDisplayName());
        } else{ //未登入
            txtStatus.setText("尚未登入");
            txtName.setText("姓名：");
        }

    }


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        txtStatus.setText("帳號登入中");
        txtName.setText(txtName.getText() + account.getDisplayName());
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
}
