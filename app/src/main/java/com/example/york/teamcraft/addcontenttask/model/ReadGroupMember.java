package com.example.york.teamcraft.addcontenttask.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/17.
 */

public class ReadGroupMember {
    private DatabaseReference groupMemRef;

    public ReadGroupMember() {
        groupMemRef = FirebaseDatabase.getInstance().getReference().child("groupMembers");
    }

    public void checkGroupMemberExist(String groupId, final CallBack<Boolean> callBack) {
        groupMemRef.child(groupId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot groupIdSnapshot) {
                callBack.update(groupIdSnapshot.exists());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getGroupMember(String groupId, final CallBack<ArrayList<GroupMember>> callBack) {
        final ArrayList<GroupMember> memList = new ArrayList<>();

        DatabaseReference childRef = groupMemRef.child(groupId).getRef();
        childRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.exists();
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while(iterator.hasNext()) {
                    DataSnapshot memSnapShot =iterator.next();
                    GroupMember groupMember = memSnapShot.getValue(GroupMember.class);
                    memList.add(groupMember);
                }
                Log.d("groupMem", Integer.toString(memList.size()));
                callBack.update(memList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
