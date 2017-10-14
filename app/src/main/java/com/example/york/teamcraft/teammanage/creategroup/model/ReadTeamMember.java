package com.example.york.teamcraft.teammanage.creategroup.model;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by York on 2017/10/14.
 */

public class ReadTeamMember {
    // Firebase User Instance
    private FirebaseUser user;

    private DatabaseReference teamGroRef;

    public ReadTeamMember() {
        teamGroRef = FirebaseDatabase.getInstance().getReference().child("teamMembers");
    }

    public void getMember() {

    }
}
