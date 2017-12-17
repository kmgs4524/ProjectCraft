package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
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
    private DatabaseReference teamPostsRef;
    private DatabaseReference teamGroRef;
    private DatabaseReference teamsRef;

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

    public void checkTeamPostExist(final CallBack<Boolean> callBack) {
        teamPostsRef = FirebaseDatabase.getInstance().getReference().child("teamPosts");
        readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
            @Override
            public void update(User user) {
                teamPostsRef.child(user.getTeamId()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        callBack.update(dataSnapshot.exists());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    public void getTeamPost(final CallBack<ArrayList<Post>> callback) {
        teamPostsRef = FirebaseDatabase.getInstance().getReference().child("teamPosts");
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User user) {
                DatabaseReference ref = teamPostsRef.child(user.getTeamId()).getRef();
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

    public void getTeamGroup(final CallBack<ArrayList<Group>> callback) {
        teamGroRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");
        groupList = new ArrayList<>();
        readUser.getCurrentLogInUserData(new CallBack<User>() {
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

    public void getTeamGroupByDataChange(String teamId, final CallBack<ArrayList<Group>> callback) {
        groupList = new ArrayList<>();
        teamGroRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");

        DatabaseReference ref = teamGroRef.child(teamId).getRef();

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

    public void checkTeamExist(final String searchId, final CallBackTwoArgs<Boolean, String> callBack) {
        teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");

        teamsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nextTeamId = "";
                boolean exist = false;
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();    // 取得擁有每個team child node的Iterator
                while (iterator.hasNext()) {
                    DataSnapshot nextTeamSnapShot = iterator.next();
                    String nextTeamSearchId = nextTeamSnapShot.child("searchId").getValue(String.class);

                    if (searchId.equals(nextTeamSearchId)) {
                        exist = true;
                        nextTeamId = nextTeamSnapShot.getKey();
                    }
                }
                callBack.update(exist, nextTeamId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("DatabaseError", databaseError.getMessage());
            }
        });
    }

    // 取得團隊名稱
    public void getTeamName(final String teamId, final CallBack<String> callBack) {
        final DatabaseReference teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");
        teamsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String teamName = dataSnapshot.child(teamId).child("name").getValue(String.class);
                callBack.update(teamName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.getDetails());
            }
        });
    }

    public void getSearchId(String teamId, final CallBack<String> callBack) {
        teamsRef = FirebaseDatabase.getInstance().getReference().child("teams");
        teamsRef.child(teamId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String searchId = dataSnapshot.child("searchId").getValue(String.class);
                callBack.update(searchId);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError);
            }
        });
    }

}
