package com.example.york.teamcraft.teammanage.taskprogress.model;

/**
 * Created by York on 2017/10/20.
 */

public class TaskProgress {
    private String groupName;
    private int totalTasksNum;
    private int checkedNum;

    public TaskProgress(String groupName, int totalTasksNum, int checkedNum) {
        this.groupName = groupName;
        this.totalTasksNum = totalTasksNum;
        this.checkedNum = checkedNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getTotalTasksNum() {
        return totalTasksNum;
    }

    public void setTotalTasksNum(int totalTasksNum) {
        this.totalTasksNum = totalTasksNum;
    }

    public int getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(int checkedNum) {
        this.checkedNum = checkedNum;
    }
}
