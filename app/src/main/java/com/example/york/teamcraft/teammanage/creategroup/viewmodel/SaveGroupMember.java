package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import com.example.york.teamcraft.data.GroupMember;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/15.
 */

public class SaveGroupMember {
    // 存放被選擇成員的list
    private ArrayList<GroupMember> memList;

    public SaveGroupMember() {
        this.memList = new ArrayList<>();
    }

    // 加入成員
    public void addMember(GroupMember groupMember) {
        if(!memList.contains(groupMember)) {
            memList.add(groupMember);
        }
    }

    public ArrayList<GroupMember> getMemList() {
        return memList;
    }

    public void setMemList(ArrayList<GroupMember> memList) {
        this.memList = memList;
    }
}
