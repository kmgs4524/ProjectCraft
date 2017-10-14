package com.example.york.teamcraft.personalsmanage.model;

/**
 * Created by York on 2017/10/14.
 */

public class DataPath {
    private String groupId;
    private String groupTaskName;
    private String taskId;

    public DataPath(String groupId, String groupTaskName, String taskId) {
        this.groupId = groupId;
        this.groupTaskName = groupTaskName;
        this.taskId = taskId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupTaskName() {
        return groupTaskName;
    }

    public void setGroupTaskName(String groupTaskName) {
        this.groupTaskName = groupTaskName;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
