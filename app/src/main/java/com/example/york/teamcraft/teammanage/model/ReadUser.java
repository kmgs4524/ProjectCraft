package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

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
    private String key;

    public ReadUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

    }

    // 可藉由email找出user的其他資料並放入User object，並利用CallBack與User object互動

    public Task<User> getUserData() {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email

        final TaskCompletionSource<User> dbSource = new TaskCompletionSource<>();
//        final Task dbTask = dbSource.getTask();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    user = snap.getValue(User.class);
                    dbSource.setResult(user);
                    Log.d("getUser", user.getEmail());
                    Log.d("getUser", user.getTeamId());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

        return dbSource.getTask();
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
//                Log.d("exist", Boolean.toString(exist));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return dbSource.getTask();
    }

}
