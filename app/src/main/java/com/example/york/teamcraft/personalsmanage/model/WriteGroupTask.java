package com.example.york.teamcraft.personalsmanage.model;

import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.google.android.gms.tasks.Task;
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

    // 被分派任務的使用者把任務打勾時會呼叫此方法
    public void updateTaskStatus(DataPath path, boolean status) {
//        Map<String, Object> taskMap = new HashMap<>();
        groupTaskRef.child(path.getGroupId()).child(path.getGroupTaskName()).child(path.getTaskId()).child("status").setValue(status);
    }

    // 新增群組任務時會呼叫此方法
    public void writeGroupTaskName(String groupId, String groupTaskTitle) {
        String key = groupTaskRef.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, new ContentTask("", "", "", "", "", "", "", false));
        groupTaskRef.child(groupId).child(groupTaskTitle).updateChildren(map);
    }

    // 在AddContentActivity按下確認時會呼叫此方法
    public Task<Void> writeContentTask(String groupId, String groupTaskName, ContentTask contentTask) {
        Map<String, Object> map = new HashMap<>();
        String key = groupTaskRef.push().getKey();
        contentTask.setTaskId(key);
        map.put(key, contentTask);
        return groupTaskRef.child(groupId).child(groupTaskName).updateChildren(map);
    }

    public void deleteContentTask(String groupId, String groupTaskName, String taskId) {
        groupTaskRef.child(groupId).child(groupTaskName).child(taskId).removeValue();
    }
}
