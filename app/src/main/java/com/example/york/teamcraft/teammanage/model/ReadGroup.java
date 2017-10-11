package com.example.york.teamcraft.teammanage.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by York on 2017/10/10.
 */

public class ReadGroup {
    private Group group;
    private DatabaseReference groupTaskRef;


    public ReadGroup() {
        groupTaskRef = FirebaseDatabase.getInstance().getReference().child("groupTasks");
    }

    public void getGroupTasks() {
        DatabaseReference childRef = groupTaskRef.child("");
    }

}
