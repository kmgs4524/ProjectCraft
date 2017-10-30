package com.example.york.teamcraft.schedulefragment.data;

/**
 * Created by York on 2017/10/28.
 */

public class RowData {
    private String groupName;
    private String task;
    private String responsible;
    private String date;

    public RowData(String groupName, String task, String responsible, String date) {
        this.groupName = groupName;
        this.task = task;
        this.responsible = responsible;
        this.date = date;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
