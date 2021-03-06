package com.example.york.teamcraft.teammanage.creategroup.model;

import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.model.Team;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/10/13.
 */

public class WriteTeamGroup {
    private DatabaseReference teamGroupsRef;
    private HashMap<String, Object> groupMap;

    public WriteTeamGroup() {
        this.teamGroupsRef = FirebaseDatabase.getInstance().getReference().child("teamGroups");
    }

    public String pushData(String teamId, String groupName) {
        groupMap = new HashMap<>();
        String groupId = teamGroupsRef.push().getKey();

        groupMap.put(groupId, new Group(groupId, groupName));
        Task<Void> task = teamGroupsRef.child(teamId).updateChildren(groupMap);
        return groupId;
    }
}
