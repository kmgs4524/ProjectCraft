package com.example.york.teamcraft.databasemodel;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.ReadUser;
import com.example.york.teamcraft.Team;
import com.example.york.teamcraft.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by York on 2017/9/24.
 */

// 負責取得個人的團隊資料
public class ReadTeam implements Continuation<String, Team> {
    private String TAG = "ReadTeam";

    // Firebase User Instance
    private FirebaseUser user;

    // Firebase Database
    private DatabaseReference rootRef;
    private DatabaseReference teamsRef;

    private Team team;

    public ReadTeam() {
        Log.d("getTeam",  "current thread in ReadTeam(): " + Thread.currentThread().getName());
        rootRef = FirebaseDatabase.getInstance().getReference();
        teamsRef = rootRef.child("teams").getRef();
//      readUser.readUserData(user.getEmail());

    }

    @Override
    public Team then(@NonNull final Task<String> task) throws Exception {
        final TaskCompletionSource source = new TaskCompletionSource();
        Log.d("getTeam", "task result in then() = " + task.getResult());

        teamsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                try {
                    team = dataSnapshot.child(task.getResult()).getValue(Team.class);
                    source.setResult(team);
                    Log.d("getTeam", "team name: " + team.getName());

                } catch (Exception e) {
                    Log.d("getTeam", "team name error: " + e.getMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("getTeam", "Error in ReadTeam " + databaseError.getMessage());
            }
        });

        return team;
    }
}
