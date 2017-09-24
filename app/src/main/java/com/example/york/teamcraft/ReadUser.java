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

    public ReadUser(String e) {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        email = e;

    }

    public User getUser() {
        return user;
    }

    // 可藉由email找出user的其他資料並放入User object，並利用CallBack與User object互動
    public void readUserData(String email, final CallBack callBack) {
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
                    String uId = snap.getKey();
                    user = snap.getValue(User.class);
                    Log.d("doInBackground", uId);
                    Log.d("doInBackground", user.getName());
                    Log.d("doInBackground", user.getEmail());
                    Log.d("doInBackground", user.getPassword());
                    callBack.update(user, uId);
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

    }

    @Override
    public String call() throws Exception {
        Log.d("ReadUser", "call()");
//        final TaskCompletionSource<String> dbSource = new TaskCompletionSource<>();
//        Task<String> task = dbSource.getTask();
        final String[] uId = new String[1];
        Query query = usersRef.orderByChild("email").equalTo(email);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("ReadUser", "onDataChange");
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    uId[0] = snap.getKey();
//                    dbSource.setResult(snap.getKey());
                    user = snap.getValue(User.class);
                    Log.d("doInBackground", user.getName());
                    Log.d("doInBackground", user.getEmail());
                    Log.d("doInBackground", user.getPassword());

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if(databaseError != null) {
//                    dbSource.setException(databaseError.toException());
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

//        task.addOnCompleteListener(new OnCompleteListener() {
//            @Override
//            public void onComplete(@NonNull Task task) {
//                uId[0] = task.getResult();
//            }
//        });

        return uId[0];
    }
}
