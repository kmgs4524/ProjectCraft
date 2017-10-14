package com.example.york.teamcraft.personalsmanage.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by York on 2017/10/13.
 */

public class WriteGroupTask {
    private DatabaseReference groupTaskRef;

    public WriteGroupTask() {
        groupTaskRef = FirebaseDatabase.getInstance().getReference().child("groupsTask");
    }

    public void updateTaskStatus(int status) {

    }
}
