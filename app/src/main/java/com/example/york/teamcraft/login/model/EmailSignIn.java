package com.example.york.teamcraft.login.model;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.york.teamcraft.login.view.SignInActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by York on 2017/10/3.
 */

public class EmailSignIn implements UserSignIn {
    private String TAG = "EmailSignIn";
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public Task<FirebaseUser> signIn(String email, String pwd) {
        Log.d("sign in", "enter EmailSignIn model");
        final TaskCompletionSource<FirebaseUser> source = new TaskCompletionSource<FirebaseUser>();

        // 當user登入App時，傳送帳號及密碼到 signInWithEmailAndPassword()
        auth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 登入成功
                            Log.d("sign in", "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            source.setResult(user);
                        } else {
                            // 登入失敗
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            updateUI(null);
                        }
                    }
                });
        return source.getTask();
    }

}
