package com.example.york.teamcraft.taskfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by York on 2017/10/18.
 */

// 會在ExpandableListAdapter呼叫的view model
// 負責判斷使用者的身分是否為組長(director)
public class CheckUserPosition {
    private ReadUser readUser;
    private ReadGroupMember readGroupMember;
    private String groupId;

    public CheckUserPosition() {
        this.readUser = new ReadUser();
        this.readGroupMember = new ReadGroupMember();
    }

    public void checkPosition(final String groupId, final CallBack<Boolean> callBack) {
        Log.d("checktrue", groupId);
        readUser.getUserId(new CallBack<String>() {
            @Override
            public void update(String data) {
                final String userId = data; // 使用者的user id
                Log.d("checktrue", data);
                readGroupMember.getGroupMember(groupId, new CallBack<ArrayList<GroupMember>>() {
                    @Override
                    public void update(ArrayList<GroupMember> data) {
                        Iterator<GroupMember> iterator = data.iterator();
                        while (iterator.hasNext()) {
                            GroupMember nextMem = iterator.next();
                            if (nextMem.getUserId().equals(userId)) {   // 若在群組中找到自己的user id
                                if (nextMem.getPosition().equals("組長")) {
                                    callBack.update(true);
                                } else {
                                    callBack.update(false);
                                }
                            }
                        }
                    }
                });
            }
        });

    }
}
