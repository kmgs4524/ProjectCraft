package com.example.york.teamcraft.signup.viewmodel;

import android.graphics.Bitmap;
import android.net.Uri;
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
import com.google.firebase.storage.UploadTask;

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

    public void createUser(String email, String password, final String name, final Bitmap bitmap) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {  // firebase authencation註冊成功之後
                            // Sign in success
                            setProfileRequest(name);
                            user = auth.getCurrentUser();   // 先取得登入中的user
                            user.updateProfile(profChange).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    UploadImage uploadImage = new UploadImage();    // 將圖片上傳到firebase storage
                                    Log.d(TAG, "onSuccess: " + bitmap.toString());
                                    uploadImage.uploadBitmap(bitmap).addOnSuccessListener((SignUpActivity) signUpView, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            String downloadUrl= taskSnapshot.getDownloadUrl().toString();   // 上傳成功後取得圖片的download url
                                            writeUser.pushData(user.getDisplayName(), user.getEmail(), downloadUrl);    // 將已註冊的user name, email及圖片的download url寫入user node
                                            bitmap.recycle();   // 回收bitmap所佔的記憶體
                                            signUpView.startSelectActivity();
                                        }
                                    });
                                }
                            });

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
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
