package com.example.york.teamcraft;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Created by York on 2017/9/22.
 */

public class ReadUser {
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private Map<String, Object> userMap;
    private User user;

    public ReadUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");

    }

    public void readUserData() {
//        String key = usersRef.getKey();
//        Log.d("readUserData", key);
        Query query = usersRef.orderByChild("email");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap = iterator.next();
//                user = snap.child(snap.getKey()).getValue(User.class);
                User user = snap.getValue(User.class);
                Log.d("snap", user.getPassword());
//                Log.d("user", user.getEmail());
//                Log.d("read", dataSnapshot.getValue().toString());
//                while(iterator.hasNext()) {
//
//                }
//                Log.d("read", user.getName());
//                Log.d("read", user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        Log.d("read", key);
//        usersRef.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                user = dataSnapshot.getValue(User.class);
//                Log.d("readUserData name", user.getName());
//                Log.d("readUserData email", user.getEmail());
//                Log.d("readUserData password", user.getPassword());
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
    }
}
