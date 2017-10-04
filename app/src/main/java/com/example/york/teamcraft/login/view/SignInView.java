package com.example.york.teamcraft.login.view;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/10/3.
 */

public interface SignInView {
    public abstract void showStatus(FirebaseUser user);
}
