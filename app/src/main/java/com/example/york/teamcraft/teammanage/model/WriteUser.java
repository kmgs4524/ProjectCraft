package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.teammanage.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/22.
 */

public class WriteUser {
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private Map<String, Object> userMap;
    private FirebaseUser user;

    public WriteUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        userMap = new HashMap<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void updateUser() {
//        userMap.put("2", new User(null, null, "235", null));
        usersRef.updateChildren(userMap, new DatabaseReference.CompletionListener() {
            // 檢查寫入資料庫是否成功
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if(databaseError != null) {
                    Log.d("Data could not be saved", databaseError.getMessage());   // 若失敗即記錄錯誤訊息
                } else {
                    Log.d("updateUser", "Data be saved successfully");
                }
            }
        });
    }

    // 在users插入新的user object
    public void pushData() {
        String key = usersRef.push().getKey();
        userMap.put(key, new User(user.getDisplayName(), user.getEmail(), "0"));
        usersRef.updateChildren(userMap);
    }

    public void updateUserTeam(String userId, String teamId) {
        DatabaseReference childRef = usersRef.child(userId);
        childRef.child("teamId").setValue(teamId);
    }
}
