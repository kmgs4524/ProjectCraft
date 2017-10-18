package com.example.york.teamcraft.taskfragment.viewmodel;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.addcontenttask.model.ReadGroupMember;
import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;

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
                readGroupMember.getGroupMember(groupId, new CallBack<ArrayList<Member>>() {
                    @Override
                    public void update(ArrayList<Member> data) {
                        Iterator<Member> iterator = data.iterator();
                        while (iterator.hasNext()) {
                            Member nextMem = iterator.next();
                            if (nextMem.getUserId().equals(userId)) {   // 若在群組中找到自己的user id
                                if (nextMem.getPosition().equals("director")) {
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
