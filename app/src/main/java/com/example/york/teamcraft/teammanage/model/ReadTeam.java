package com.example.york.teamcraft.teammanage.model;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
    private ArrayList<Work> actList;
    private Work act;

    private FragmentActivity fragActivity;

    public ReadTeam(FragmentActivity fa) {
        rootRef = FirebaseDatabase.getInstance().getReference();
        teamRef = rootRef.child("teams").getRef();
        teamActRef = rootRef.child("teamActivities");
        user = FirebaseAuth.getInstance().getCurrentUser();
        readUser = new ReadUser();
        actList = new ArrayList<>();

        fragActivity = fa;

    }

    public void getTeamAct(final CallBack< ArrayList<Work> > callback) {
        final Task<User> userTask = readUser.getUserData(); // 取得擁有User Data的Task
        Log.d("getAct", "getTeamAct");

        userTask.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                DatabaseReference ref = teamActRef.child(user.getTeamId()).getRef();    // 搜尋出想要的email
                Log.d("getAct", ref.getKey());

                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Log.d("getAct", dataSnapshot.getValue().toString());
                        act = dataSnapshot.getValue(Work.class);
                        Log.d("getAct", act.getDate());
                        Log.d("getAct", act.getContent());
                        actList.add(act);
                        Log.d("getAct", "list size: " + Integer.toString(actList.size()));
                        callback.update(actList);

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

            }
        });

    }

}
