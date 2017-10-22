package com.example.york.teamcraft.login.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.york.teamcraft.signup.view.SignUpActivity;
import com.example.york.teamcraft.login.model.EmailSignIn;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.teammanage.MainActivity;
import com.example.york.teamcraft.teammanage.SelectTeamActivity;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by York on 2017/10/3.
 */

public class SignInPresenterImpl implements SignInPresenter {
    // const
    private static final String SEVER_CLIENT_ID = "351544429326-6g982pc3jarftp4017oi8gu526c5m70f.apps.googleusercontent.com";
    private static final int RC_SIGN_IN = 123;

    private GoogleApiClient mGoogleApiClient;
    private FirebaseAuth auth;
    private FirebaseUser user;

    // SignInActivity
    private SignInActivity signInActivity;

    // SignIn Model
    private EmailSignIn emailSignIn;

    public FirebaseUser getCurrentUser() {
        return user;
    }

    public SignInPresenterImpl(SignInActivity a) {
        // view and model
        signInActivity = a;
        emailSignIn = new EmailSignIn();

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // 建立GoogleSignInOptions
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(SEVER_CLIENT_ID)
                .requestEmail()
                .build();
        // 建立GoogleApiClient
        mGoogleApiClient = new GoogleApiClient.Builder(signInActivity)
                .enableAutoManage(signInActivity /* FragmentActivity */, signInActivity /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    // 呼叫SignInActivity.onStop時，呼叫此Callback
    public void destoryGoogleClient() {
        mGoogleApiClient.stopAutoManage(signInActivity);
        mGoogleApiClient.disconnect();
    }

    // 一般信箱登入(由EmailSignIn Model
    @Override
    public void signIn(String e, String p) {
        Task<FirebaseUser> task = emailSignIn.signIn(e, p);
        task.addOnSuccessListener(new OnSuccessListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                user = firebaseUser;
                Log.d("sign in", user.getEmail());
                showStatus();
                confirmUserExist();
            }
        });
    }

    // 以Google帳號登入(尚未使用額外的Business Model來處理Google登入事項)
    public void googleSignIn(int reqCode) {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        signInActivity.startActivityForResult(signInIntent, reqCode);
    }

    // 利用GoogleSignInAccount的ID Token，轉換成Firebase的授權
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);  // 取得Google憑證
        auth.signInWithCredential(credential)   // 以Google憑證登入FireBase Project
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("google sign in", "signInWithCredential:success");
                            user = auth.getCurrentUser();
                            confirmUserExist(); // 確認資料庫是否已有使用者的資料
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("google sign in", "signInWithCredential:failure", task.getException());
                        }

                    }
                });
    }

    // 登出帳號
    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        user = FirebaseAuth.getInstance().getCurrentUser();
        showStatus();
    }

    // 確認資料庫中是否存在使用者的資料
    public void confirmUserExist() {
        final ReadUser readUser = new ReadUser();

        final Task<Boolean> taskCheck = readUser.checkUserExist();    // 確認users node是否已有目前登入使用者的資料
        taskCheck.addOnSuccessListener(new OnSuccessListener<Boolean>() {
            @Override
            public void onSuccess(Boolean aBoolean) {
                if (aBoolean == false) {    // 若尚未有資料
                    WriteUser writeUser = new WriteUser();
                    writeUser.pushData(user.getDisplayName(), user.getEmail());
                    startCreateTeam();
                    Log.d("goto", "create team");
                } else {    // 若已有資料
                    Task<Boolean> taskCheckTeam = readUser.checkUserTeam();
                    taskCheckTeam.addOnSuccessListener(new OnSuccessListener<Boolean>() {
                        @Override
                        public void onSuccess(Boolean aBoolean) {
                            if (!aBoolean) {    // 若尚未擁有團隊
                                startCreateTeam();
                            } else {
                                startTeamMain();
                            }
                        }
                    });
//                    Log.d("goto", "true");
//                    startCreateTeam();
                }
            }
        });

    }

    // 進入創建團隊的畫面
    public void startCreateTeam() {
        Intent intent = new Intent();
        intent.setClass(signInActivity, SelectTeamActivity.class);
        signInActivity.startActivity(intent);
    }

    // 進入Team Manage的MainActivity
    public void startTeamMain() {
        Intent intent = new Intent();
        intent.setClass(signInActivity, MainActivity.class);
        signInActivity.startActivity(intent);
    }

    // 進入註冊新帳號畫面
    public void startSignUp() {
        Intent intent = new Intent();
        intent.setClass(signInActivity, SignUpActivity.class);
        signInActivity.startActivity(intent);
    }

    // 顯示登入狀態
    public void showStatus() {
        if (user == null) {
            Log.d("sign in", "user is null");
        } else {
            Log.d("sign in", user.getEmail());
        }
        signInActivity.showStatus(user);
    }

}
