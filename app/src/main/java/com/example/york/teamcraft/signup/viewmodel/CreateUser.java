package com.example.york.teamcraft.signup.viewmodel;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.york.teamcraft.signup.view.SignUpActivity;
import com.example.york.teamcraft.signup.view.SignUpView;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * Created by York on 2017/10/22.
 */

public class CreateUser {
    public static String TAG = "CreateUser";

    private FirebaseAuth auth;
    private FirebaseUser user;
    private UserProfileChangeRequest profChange;
    // view
    private SignUpView signUpView;
    // database model
    private WriteUser writeUser;

    public CreateUser(SignUpView view) {
        this.auth = FirebaseAuth.getInstance();
        this.signUpView = view;
        this.writeUser = new WriteUser();
    }

    public void createUser(String email, String password, final String name) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success
                            setProfileRequest(name);
                            user = auth.getCurrentUser();
                            user.updateProfile(profChange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    writeUser.pushData(user.getDisplayName(), user.getEmail());
                                    signUpView.startSelectActivity();
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public void setProfileRequest(String name) {
        profChange = new UserProfileChangeRequest.Builder()
                .setDisplayName(name)
                .build();
    }

}
