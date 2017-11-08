package com.example.york.teamcraft.personalsfragment.viewmodel;

import android.widget.Toast;

import com.example.york.teamcraft.personalsfragment.view.PersonalsView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/11/8.
 */

public class SignIn {
    private PersonalsView personalsView;

    public SignIn(PersonalsView view) {
        this.personalsView = view;
    }

    public void checkStatus() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user == null) {
            personalsView.startSignInActivity();
        } else {
            FirebaseAuth.getInstance().signOut();
            personalsView.showSignOutMesg();
        }
    }
}
