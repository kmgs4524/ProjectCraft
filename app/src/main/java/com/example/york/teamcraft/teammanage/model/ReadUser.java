package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by York on 2017/9/22.
 */

public class ReadUser {
    private FirebaseUser firebaseUser;
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private String email;
    private User user;

    public ReadUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

    }

    // 可藉由email找出user的其他資料並放入User object，並利用CallBack與User object互動

    public void getUserId(final CallBack<String> c) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        final TaskCompletionSource<String> source = new TaskCompletionSource<>();
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                if (iterator.hasNext()) {
                    snap = iterator.next();
                    String key = snap.getKey();    // user Id
                    c.update(key);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

    }

    public void getUserData(final CallBack<User> callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    user = snap.getValue(User.class);
                    callBack.update(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });
    }

    public void getUserDataByCallBack(final CallBack<User> callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    user = snap.getValue(User.class);
                    callBack.update(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

    }

    public Task<Boolean> checkUserExist() {
        final TaskCompletionSource<Boolean> dbSource = new TaskCompletionSource<>();

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exist = false;
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    if (data.child("email").getValue().equals(email)) {
                        exist = true;
                    } else {

                    }
                }
                dbSource.setResult(exist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return dbSource.getTask();
    }

    public Task<Boolean> checkUserTeam() {
        final TaskCompletionSource<Boolean> idSource = new TaskCompletionSource<>();

        Query childRef = usersRef.orderByChild("email").equalTo(email);
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for(DataSnapshot data: iterable) {
                    String teamId = data.child("teamId").getValue(String.class);
//                    Log.d("data", teamId);
                    if(! teamId.equals("0")) {
                        idSource.setResult(true);
                    } else {
                        idSource.setResult(false);
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("error", databaseError.getMessage());
            }
        });

        return idSource.getTask();
    }

    public void getUserImageUrl(String authorId, final CallBack<String> callBack) {
        usersRef.child(authorId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String imageUrl = dataSnapshot.child("imageUrl").getValue(String.class);
                callBack.update(imageUrl);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
