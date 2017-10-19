package com.example.york.teamcraft.addcontenttask.viewmodel;

import com.example.york.teamcraft.data.GroupMember;

/**
 * Created by York on 2017/10/17.
 */

public class SaveInputData {
    private String date;
    private GroupMember groupMember;

    public void saveDate(int y, int m, int d) {
        date = y + "/" + m + "/" + d;
    }

    public String getDate() {
        return date;
    }

    public void saveResponsible(GroupMember groupMember) {
        this.groupMember = groupMember;
    }

    public GroupMember getResponsible() {
        return groupMember;
    }

}
