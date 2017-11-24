package com.example.york.teamcraft.teammanage.creategroup.model;

import com.example.york.teamcraft.data.GroupMember;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/10/15.
 */

public class WriteGroupMember {
    // model
    private DatabaseReference groupMemRef;
    private Map<String, Object> groupMebMap;

    public WriteGroupMember() {
        this.groupMemRef = FirebaseDatabase.getInstance().getReference().child("groupMembers");
    }

    public void pushData(ArrayList<GroupMember> memList, String groupId) {
        String key = groupMemRef.child(groupId).push().getKey();
        groupMebMap = new HashMap<>();

        for(GroupMember mem: memList) {
            groupMebMap.put(mem.getUserId(), new GroupMember(mem.getName(), mem.getUserId(), mem.getPosition(), "0"));
        }
        groupMemRef.child(groupId).updateChildren(groupMebMap);
    }
}
