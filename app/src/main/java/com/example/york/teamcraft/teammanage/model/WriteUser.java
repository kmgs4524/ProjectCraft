package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.teammanage.model.User;
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
    private User user;

    public WriteUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        userMap = new HashMap<>();

        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                user = dataSnapshot.child("1").getValue(User.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("The read failed", databaseError.getMessage());
            }
        };

        usersRef.addValueEventListener(listener);

    }

    public void updateUser() {
        userMap.put("2", new User(null, null, "235", null));
//        userMap.put("3/password", "1045");

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

    public void pushUser() {
//        DatabaseReference newUserRef = usersRef.push();
        usersRef.child("1").push().setValue(new User("李宗碩", "chu123@gmail.com", "2424", null));
    }

    public void getUser() {
//        Log.d("getKey", firstRef.getKey());

    }


}
