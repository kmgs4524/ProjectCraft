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
        final ArrayList<String> groupList = new ArrayList<>();  // 群組任務的list
        final HashMap<String, ArrayList<ContentTask>> itemMap = new HashMap<>();    // 細項任務的map，key: 群組任務名稱, value: 細項任務的list


        childRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String groupTaskName = dataSnapshot.getKey();
                // ArrayList為傳址，故無法重複使用
                ArrayList<ContentTask> contentTaskList = new ArrayList<>();   // 細項任務的list

                groupList.add(groupTaskName);
                ContentTask task;
                Iterator<DataSnapshot> childSnapShot= dataSnapshot.getChildren().iterator();
                while (childSnapShot.hasNext()) {
                    task = childSnapShot.next().getValue(ContentTask.class);
                    contentTaskList.add(task);
                }

                itemMap.put(groupTaskName, contentTaskList);
                callBack.update(groupList, itemMap);
                Log.d("list before 活動包裝", Integer.toString(itemMap.get("活動包裝").size()));
                Log.d("list after 活動包裝", Integer.toString(itemMap.get("活動包裝").size()));
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
