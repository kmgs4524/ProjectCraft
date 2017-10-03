package com.example.york.teamcraft.login.presenter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.york.teamcraft.login.model.EmailSignIn;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.login.view.SignInView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
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

public class SignInPresenterImpl implements SignInPresenter{
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

    @Override
    public void signIn(String e, String p) {
        Log.d("sign in", "enter presenter");
        Task<FirebaseUser> task = emailSignIn.signIn(e, p);
        task.addOnSuccessListener(new OnSuccessListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                user = firebaseUser;
                Log.d("sign in", user.getEmail());
                showStatus();
            }
        });
    }

    public void googleSignIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        signInActivity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // 利用GoogleSignInAccount的ID Token，轉換成Firebase的授權
    public void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("google sign in", "signInWithCredential:success");
                            user = auth.getCurrentUser();
                            showStatus();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("google sign in", "signInWithCredential:failure", task.getException());
                            showStatus();
                        }

                    }
                });
    }

    @Override
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        user = FirebaseAuth.getInstance().getCurrentUser();
        showStatus();
    }


    public void showStatus() {
        if(user == null) {
            Log.d("sign in", "user is null");
        } else {
            Log.d("sign in", user.getEmail());
        }
        signInActivity.showStatus(user);
    }
}
