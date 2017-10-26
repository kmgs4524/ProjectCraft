package com.example.york.teamcraft.teammanage.taskprogress.model;

/**
 * Created by York on 2017/10/20.
 */

public class GroupMissionProgress {
//    private String groupMissionName;
    private int totalTasksNum;
    private int checkedNum;

    public GroupMissionProgress(int totalTasksNum, int checkedNum) {
//        this.groupMissionName = groupMissionName;
        this.totalTasksNum = totalTasksNum;
        this.checkedNum = checkedNum;
    }

//    public String getGroupMissionName() {
//        return groupMissionName;
//    }
//
//    public void setGroupMissionName(String groupMissionName) {
//        this.groupMissionName = groupMissionName;
//    }

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
