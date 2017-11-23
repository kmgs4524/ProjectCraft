package com.example.york.teamcraft.teammanage.creategroup.model;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/14.
 */

public class ReadTeamMember {
    private DatabaseReference teamMemRef;

    public ReadTeamMember() {
        teamMemRef = FirebaseDatabase.getInstance().getReference().child("teamMembers");
    }

    public void getGroupMember(String teamId, final CallBack<ArrayList<GroupMember>> callBack) {
        final ArrayList<GroupMember> memList = new ArrayList<>();

        teamMemRef.child(teamId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // 迭代出teamId的所有child
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
//                    Log.d("groupMember data", iterator.next().toString());
                    DataSnapshot nextSnapShot = iterator.next();
                    GroupMember groupMember = nextSnapShot.getValue(GroupMember.class);
                    groupMember.setUserId(nextSnapShot.getKey());
                    memList.add(groupMember);
                }
                callBack.update(memList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getTeamMember(String teamId, final CallBack<ArrayList<TeamMember>> callBack) {
        final ArrayList<TeamMember> memList = new ArrayList<>();

        teamMemRef.child(teamId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                while (iterator.hasNext()) {
                    DataSnapshot nextSnapShot = iterator.next();
                    TeamMember teamMember = nextSnapShot.getValue(TeamMember.class);
                    teamMember.setUserId(nextSnapShot.getKey());
                    memList.add(teamMember);
                }
                callBack.update(memList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
