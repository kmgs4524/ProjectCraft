package com.example.york.teamcraft.teammanage.taskprogress.model;

/**
 * Created by York on 2017/10/21.
 */

public class GroupProgress {
    private String groupName;
    private int totalTaskNum;
    private int checkedTaskNum;

    public GroupProgress(String groupName, int totalTaskNum, int checkedTaskNum) {
        this.groupName = groupName;
        this.totalTaskNum = totalTaskNum;
        this.checkedTaskNum = checkedTaskNum;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getTotalTaskNum() {
        return totalTaskNum;
    }

    public void setTotalTaskNum(int totalTaskNum) {
        this.totalTaskNum = totalTaskNum;
    }

    public int getCheckedTaskNum() {
        return checkedTaskNum;
    }

    public void setCheckedTaskNum(int checkedTaskNum) {
        this.checkedTaskNum = checkedTaskNum;
    }
}
