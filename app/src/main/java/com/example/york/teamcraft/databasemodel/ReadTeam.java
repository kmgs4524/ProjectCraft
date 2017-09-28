package com.example.york.teamcraft.databasemodel;

import android.support.annotation.NonNull;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.util.Log;

import com.example.york.teamcraft.Activity;
import com.example.york.teamcraft.Team;
import com.example.york.teamcraft.User;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/9/24.
 */

// 負責取得個人的團隊資料
public class ReadTeam {
    private String TAG = "ReadTeam";

    // Firebase User Instance
    private FirebaseUser user;

    // Firebase Database
    private DatabaseReference rootRef;
    private DatabaseReference teamRef;
    private DatabaseReference teamActRef;

    private ReadUser readUser;
    private Team team;
    private ArrayList<Activity> actList;
    private Activity act;

    public ReadTeam() {
        rootRef = FirebaseDatabase.getInstance().getReference();
        teamRef = rootRef.child("teams").getRef();
        teamActRef = rootRef.child("teamActivities");
        user = FirebaseAuth.getInstance().getCurrentUser();
        readUser = new ReadUser(user.getEmail());

    }

    public Task<Team> getTeamData() {
        final Task<String> task = readUser.getUserId();
        final TaskCompletionSource source = new TaskCompletionSource();

        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                try {
                    task.addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull Task<String> task) {
                            team = dataSnapshot.child(task.getResult()).getValue(Team.class);
                            source.setResult(team);
                            Log.d("getTeam", "team name: " + team.getName());
                        }
                    });


                } catch (Exception e) {
                    Log.d("getTeam", "team name error: " + e.getMessage());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("getTeam", "Error in ReadTeam " + databaseError.getMessage());
            }
        });

        return source.getTask();
    }

    public void getTeamAct(final ArrayList<Activity> list) {
        final Task<User> task = readUser.getUserData();
        final TaskCompletionSource< ArrayList<Activity> > source = new TaskCompletionSource();
//        actList = new ArrayList<>();

        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                DatabaseReference ref = teamActRef.child(user.getTeamId()).getRef();    // 搜尋出想要的email
                Log.d("getAct", ref.getKey());

                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.d("getAct", dataSnapshot.getValue().toString());
                        act = dataSnapshot.getValue(Activity.class);
                        Log.d("getAct", act.getDate());
                        Log.d("getAct", act.getContent());
                        list.add(act);
                        Log.d("getAct", "list size: " + Integer.toString(list.size()));

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                source.setResult(actList);

            }
        });


//        return source.getTask();

    }


}
