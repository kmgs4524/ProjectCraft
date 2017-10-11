package com.example.york.teamcraft.taskfragment.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.CallBackTwoArgs;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by York on 2017/10/11.
 */

public class ReadGroupTasks {
    private DatabaseReference groupRef;

//    private String groupId;

    public ReadGroupTasks() {
        groupRef = FirebaseDatabase.getInstance().getReference().child("groupTasks");
    }

    public void getGroupTaskName(String groupId, final CallBackTwoArgs<ArrayList<String>, HashMap<String, ArrayList<ContentTask>>> callBack) {
        DatabaseReference childRef = groupRef.child(groupId);
        final ArrayList<String> groupList = new ArrayList<>();
        final HashMap<String, ArrayList<ContentTask>> itemMap = new HashMap<>();
        final ArrayList<ContentTask> contentTaskList = new ArrayList<>();

        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupTaskName = dataSnapshot.getKey();
                groupList.add(groupTaskName);
                ContentTask task;
                Iterator<DataSnapshot> childSnapShot= dataSnapshot.getChildren().iterator();
                while (childSnapShot.hasNext()) {
//                    Log.d("snap", childSnapShot.next().getValue().toString());
                    task = childSnapShot.next().getValue(ContentTask.class);
                    contentTaskList.add(task);
                }
                itemMap.put(groupTaskName, contentTaskList);
                Log.d("key", Integer.toString(itemMap.size()));
                callBack.update(groupList, itemMap);
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
}
