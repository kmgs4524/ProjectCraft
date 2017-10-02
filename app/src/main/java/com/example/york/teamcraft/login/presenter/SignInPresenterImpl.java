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
                user = firebaseUser;
                Log.d("sign in", user.getEmail());
                showStatus();
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
