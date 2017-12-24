package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by York on 2017/12/22.
 */

public class ReadTeamGroup {

    public void getTeamGroups(String teamId, final CallBack<ArrayList<Group>> callback) {
        DatabaseReference teamGroupRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");

        DatabaseReference teamIdRef = teamGroupRef.child(teamId).getRef();

        teamIdRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot teamIdSnapshot) {
                ArrayList<Group> groups = new ArrayList<Group>();
                for(DataSnapshot groupIdSnapShot: teamIdSnapshot.getChildren()) {
                    Group nextGroup = groupIdSnapShot.getValue(Group.class);
                    groups.add(nextGroup);
                }
                callback.update(groups);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("ReadTeamGroup", "onCancelled: " + databaseError.toString());
            }
        });

    }
}
