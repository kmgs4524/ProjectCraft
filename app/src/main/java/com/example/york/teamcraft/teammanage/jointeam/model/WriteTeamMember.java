package com.example.york.teamcraft.teammanage.jointeam.model;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.OnClick;

/**
 * Created by York on 2017/10/23.
 */

public class WriteTeamMember {
    private DatabaseReference teamsMemRef;

    public WriteTeamMember() {
        this.teamsMemRef = FirebaseDatabase.getInstance().getReference().child("teamMembers");
    }

    public Task<Void> updateTeamMember(String teamId, String teamMemberUserId, String teamMemberName) {
        TeamMember teamMember = new TeamMember(teamMemberName);
        Map<String, Object> memMap = new HashMap<>();
        memMap.put(teamMemberUserId, teamMember);
        Task<Void> taskUpdate = teamsMemRef.child(teamId).updateChildren(memMap);
        return taskUpdate;
    }

    public void changeTeamMemberName(String teamId, String userId, String userName) {
        teamsMemRef.child(teamId).child(userId).child("name").setValue(userName);
    }

}
