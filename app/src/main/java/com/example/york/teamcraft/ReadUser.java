package com.example.york.teamcraft;

import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

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

    public User getUser() {
        return user;
    }

    public User readUserData(String email, final CallBack callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
//        query.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                Log.d("child", dataSnapshot.toString());
//                user = dataSnapshot.getValue(User.class);
//                Log.d("next", user.getName());
////                user = snap.getValue(User.class);
////                Log.d("snap", user.getName());
////                Log.d("snap", user.getEmail());
////                Log.d("snap", user.getPassword());
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
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
//                if (databaseError != null) {
//                    Log.d("Error", databaseError.toString());
//                }
//            }
//        });
        Log.d("read", "before add");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    String key = snap.getKey();
                    user = snap.getValue(User.class);
                    Log.d("doInBackground", key);
                    Log.d("doInBackground", user.getName());
                    Log.d("doInBackground", user.getEmail());
                    Log.d("doInBackground", user.getPassword());
                    callBack.updateTextView(user);
                    // callback.updateTxtView(user);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });
        return user;
    }

}
