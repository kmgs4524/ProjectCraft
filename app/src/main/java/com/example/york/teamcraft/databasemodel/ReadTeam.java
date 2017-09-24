package com.example.york.teamcraft.databasemodel;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.ReadUser;
import com.example.york.teamcraft.Team;
import com.example.york.teamcraft.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by York on 2017/9/24.
 */

// 負責取得個人的團隊資料
public class ReadTeam implements ReadDatabase {
    private String TAG = "ReadTeam";

    // Firebase User Instance
    private FirebaseUser user;

    // Firebase Database
    private DatabaseReference rootRef;
    private DatabaseReference teamsRef;

    private ReadUser readUser;
    private String uId;

    public ReadTeam() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        rootRef = FirebaseDatabase.getInstance().getReference();
        teamsRef = rootRef.child("teams").getRef();
        Log.d("ReadUser", user.getEmail());
        readUser = new ReadUser(user.getEmail());
        readUser.readUserData(user.getEmail(), new CallBack() {
            @Override
            public void update(Object o, String key) {
                Log.d("ReadUser", "callback");
                uId = key;
            }
        });

//        final Task<String> task = Tasks.call(readUser);
//        task.addOnSuccessListener(new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                uId = task.getResult();
//                Log.d(TAG, uId);
//            }
//        });

    }

    @Override
    public void readData() {
        teamsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Team team = dataSnapshot.child(uId).getValue(Team.class);
                Log.d("readData", team.getName());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
