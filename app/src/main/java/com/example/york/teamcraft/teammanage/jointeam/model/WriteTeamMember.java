package com.example.york.teamcraft.teammanage.jointeam.model;

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

    public void updateTeamMember(String teamId, String userId, String userName) {
        TeamMember teamMember = new TeamMember(userName);
        Map<String, Object> memMap = new HashMap<>();
        memMap.put(userId, teamMember);
        teamsMemRef.child(teamId).updateChildren(memMap);
    }
}
