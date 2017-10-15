package com.example.york.teamcraft.personalsmanage.model;

import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by York on 2017/10/13.
 */

public class WriteGroupTask {
    private DatabaseReference groupTaskRef;

    public WriteGroupTask() {
        groupTaskRef = FirebaseDatabase.getInstance().getReference().child("groupTasks");
    }

    public void updateTaskStatus(DataPath path, boolean status) {
//        Map<String, Object> taskMap = new HashMap<>();
        groupTaskRef.child(path.getGroupId()).child(path.getGroupTaskName()).child(path.getTaskId()).child("status").setValue(status);
    }

    public void writeGroupTaskName(String groupId, String groupTaskTitle) {
        String key = groupTaskRef.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, new ContentTask());
        groupTaskRef.child(groupId).child(groupTaskTitle).updateChildren(map);
    }
}
