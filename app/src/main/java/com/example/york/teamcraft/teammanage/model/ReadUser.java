package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
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
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by York on 2017/9/22.
 */

public class ReadUser {
    private DatabaseReference rootRef;
    private DatabaseReference usersRef;
    private String email;
    private User user;

    public ReadUser() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        usersRef = rootRef.child("users");
        email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

    }

    // 取得目前登入使用者的user id
    public void getUserId(final CallBack<String> callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                if (iterator.hasNext()) {
                    snap = iterator.next();
                    String key = snap.getKey();    // user Id
                    callBack.update(key);
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

    // 取得目前登入使用者的資料
    public void getCurrentLogInUserDataForSingleEvent(final CallBack<User> callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    user = snap.getValue(User.class);
                    GenericTypeIndicator<List<String>> indicator = new GenericTypeIndicator<List<String>>() {};
                    user.setGroupId((ArrayList<String>)snap.child("groupId").getValue(indicator));
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

    public void getCurrentLogInUserData(final CallBack<User> callBack) {
        Query query = usersRef.orderByChild("email").equalTo(email);    // 搜尋出想要的email
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                DataSnapshot snap;
                while (iterator.hasNext()) {
                    snap = iterator.next();
                    user = snap.getValue(User.class);
                    GenericTypeIndicator<List<String>> indicator = new GenericTypeIndicator<List<String>>() {};
                    user.setGroupId((ArrayList<String>)snap.child("groupId").getValue(indicator));
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

    // 藉由user id 取得特定使用者的資料
    public void getUserDataById(String userId, final CallBack<User> callBack) {
        DatabaseReference childRef = usersRef.child(userId);
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                callBack.update(user);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                if (databaseError != null) {
                    Log.d("onCancelled", databaseError.getMessage());
                }
            }

        });

    }
    // 用email取得user的id, data，若找不到符合的user回傳"none"回userId
    public void getUserIdAndDataByEmail(final String email, final CallBackTwoArgs<String, User> callBack) {
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot usersSnapshot) {
                boolean isExisting = false;
                Iterator<DataSnapshot> iterator = usersSnapshot.getChildren().iterator();
                DataSnapshot userIdSnapShot = null;
                while(iterator.hasNext()) {
                    userIdSnapShot = iterator.next();
                    User user = userIdSnapShot.getValue(User.class);
                    if(user.getEmail().equals(email)) {
                        isExisting = true;
                    }
                }
                if(isExisting) {
                    callBack.update(userIdSnapShot.getKey(), user);
                } else {
                    callBack.update("none", null);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

    public void getUserImageUrl(String userId, final CallBack<String> callBack) {
        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
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
