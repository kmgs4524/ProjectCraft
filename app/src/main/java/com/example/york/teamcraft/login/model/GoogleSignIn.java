package com.example.york.teamcraft.login.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/10/3.
 */

public class GoogleSignIn implements UserSignIn{
    @Override
    public Task<FirebaseUser> signIn(String e, String p){
        return null;
    }
}
