package com.example.york.teamcraft.login.presenter;

import android.util.Log;

import com.example.york.teamcraft.login.model.EmailSignIn;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.login.view.SignInView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/10/3.
 */

public class SignInPresenterImpl implements SignInPresenter{
    private FirebaseUser user;

    // SignInActivity
    private SignInView signInActivity;

    // SignIn Model
    private EmailSignIn emailSignIn;

    public FirebaseUser getCurrentUser() {
        return user;
    }

    public SignInPresenterImpl(SignInActivity a) {
        signInActivity = a;
        emailSignIn = new EmailSignIn();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void signIn(String e, String p) {
        Log.d("sign in", "enter presenter");
        Task<FirebaseUser> task = emailSignIn.signIn(e, p);
        task.addOnSuccessListener(new OnSuccessListener<FirebaseUser>() {
            @Override
            public void onSuccess(FirebaseUser firebaseUser) {
                Log.d("sign in", firebaseUser.getEmail());
                showStatus();
            }
        });
    }

    public void showStatus() {
//        Log.d("sign in", user.toString());
        signInActivity.showStatus(user);
    }
}
