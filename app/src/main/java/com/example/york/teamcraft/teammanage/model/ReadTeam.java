package com.example.york.teamcraft.teammanage.model;

import android.provider.ContactsContract;
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
    private DatabaseReference teamActRef;
    private DatabaseReference teamGroRef;
    private DatabaseReference teamRef;

    private ReadUser readUser;

    // 存放資料的object, collection
    private Post post;
    private Group group;
    private ArrayList<Post> postList;
    private ArrayList<Group> groupList;


    public ReadTeam() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        readUser = new ReadUser();
        postList = new ArrayList<>();

    }

    public void getTeamAct(final CallBack< ArrayList<Post> > callback) {
        teamActRef = FirebaseDatabase.getInstance().getReference().child("teamActivities");
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                DatabaseReference ref = teamActRef.child(data.getTeamId()).getRef();    // 搜尋出想要的email
                Log.d("getAct", ref.getKey());

                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        post = dataSnapshot.getValue(Post.class);
                        postList.add(post);

                        callback.update(postList);

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
        }); // 取得擁有User Data的Task

    }

    public void getTeamGroup(final CallBack< ArrayList<Group> > callback) {
        teamGroRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");
        groupList = new ArrayList<>();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                DatabaseReference ref = teamGroRef.child(data.getTeamId()).getRef();

                ref.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        group = dataSnapshot.getValue(Group.class);
                        groupList.add(group);
                        callback.update(groupList);
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
        }); // 取得擁有User Data的Task

    }

    public void getTeamGroupByDataChange(final CallBack< ArrayList<Group> > callback) {
        groupList = new ArrayList<>();
        teamGroRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                DatabaseReference ref = teamGroRef.child(data.getTeamId()).getRef();

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterator<DataSnapshot> childSnapShot = dataSnapshot.getChildren().iterator();
                        while (childSnapShot.hasNext()) {
                            Group group = childSnapShot.next().getValue(Group.class);
                            groupList.add(group);
                        }
                        callback.update(groupList);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void checkTeamExist(final String teamId, final CallBack<Boolean> callBack) {
        teamRef = FirebaseDatabase.getInstance().getReference().child("teams");

        teamRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean exist = false;
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();    // 取得擁有每個team child node的Iterator
                while (iterator.hasNext()) {
                    DataSnapshot nextSnapShot = iterator.next();
                    if(teamId.equals(nextSnapShot.getKey())) {
                        exist = true;
                    }
                }
                callBack.update(exist);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DatabaseError", databaseError.getMessage());
            }
        });
    }

}
