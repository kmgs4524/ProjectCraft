package com.example.york.teamcraft.login.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/10/3.
 */

public interface UserSignIn {
    public abstract Task<FirebaseUser> signIn(String e, String p);
}
