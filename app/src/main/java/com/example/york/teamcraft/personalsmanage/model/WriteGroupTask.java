package com.example.york.teamcraft.personalsmanage.model;

import android.util.Log;

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
        if(status) {
            groupTaskRef.child(path.getGroupId()).child(path.getGroupTaskName()).child(path.getTaskId()).child("status").setValue("done");
        } else {
            groupTaskRef.child(path.getGroupId()).child(path.getGroupTaskName()).child(path.getTaskId()).child("status").setValue("undo");
        }
    }

    // 新增群組任務時會呼叫此方法
    public void writeGroupTaskName(String groupId, String groupTaskTitle) {
        String key = groupTaskRef.push().getKey();
        Map<String, Object> map = new HashMap<>();
        map.put(key, new ContentTask(key, "", "", "", "", "", "", "undo"));
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

    // 在TargetFragment中按下刪除button時會呼叫此方法
    public void deleteContentTask(String groupId, String groupTaskName, String taskId) {
        Log.d("delete", "groupId: " + groupId +" groupTaskName: " + groupTaskName + " taskId: " + taskId);
        groupTaskRef.child(groupId).child(groupTaskName).child(taskId).removeValue();
    }

    public void confirmContentTask(String groupId, String groupTaskName, String taskId) {
        Log.d("confirm", "groupId: " + groupId +" groupTaskName: " + groupTaskName + " taskId: " + taskId);
        groupTaskRef.child(groupId).child(groupTaskName).child(taskId).child("status").setValue("checked");
    }
}
