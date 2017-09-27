package com.example.york.teamcraft;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.york.teamcraft.databasemodel.ReadDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by York on 2017/9/22.
 */

public class ReadUser implements Callable<String> {
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private Map<String, Object> userMap;
    private String email;
    private User user;
    private String key;

    public ReadUser(String e) {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        email = e;

    }

    // 可藉由email找出user的其他資料並放入User object，並利用CallBack與User object互動
    public Task<String> readUserData() {

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
        final TaskCompletionSource<String> dbSource = new TaskCompletionSource<>();
        Task dbTask = dbSource.getTask();
        Log.d("read", "before add");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d("getTeam",  "current thread in ValueEventListener.onDataChange(): " + Thread.currentThread().getName());
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    key = snap.getKey();
                    dbSource.setResult(key);
                    Log.d("getTeam", "key= " + key);
                    user = snap.getValue(User.class);
//                    Log.d("doInBackground", uId);
                    Log.d("doInBackground", user.getName());
                    Log.d("doInBackground", user.getEmail());
                    Log.d("doInBackground", user.getPassword());
//                    callBack.update(user, uId);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

        return dbTask;
    }

    @Override
    public String call() throws Exception {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        Log.d("getTeam", query.getRef().getKey());
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("getTeam", "in onDataChange "+ dataSnapshot.toString());
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    String key = snap.getKey();
                    Log.d("getTeam", "In ReadUser key= " + key);
                    user = snap.getValue(User.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ReadUser", databaseError.getMessage());
            }
        });
        Log.d("getTeam", "after addListener");

        return key;
    }
}
