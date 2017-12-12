package com.example.york.teamcraft.modifypersonaldatadialogfragment;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.creategroup.model.WriteGroupMember;
import com.example.york.teamcraft.teammanage.jointeam.model.WriteTeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WriteUser;

/**
 * Created by York on 2017/12/12.
 */

public class ModifyData {
    // model
    WriteUser writeUser;
    WriteTeamMember writeTeamMember;
    WriteGroupMember writeGroupMember;
    ReadUser readUser;

    public void modifyName(final String name) {
        writeUser = new WriteUser();
        writeTeamMember = new WriteTeamMember();
        writeGroupMember = new WriteGroupMember();
        readUser = new ReadUser();
        readUser.getUserId(new CallBack<String>() {
            @Override
            public void update(final String userId) {
                readUser.getCurrentLogInUserDataForSingleEvent(new CallBack<User>() {
                    @Override
                    public void update(User user) {
                        writeUser.updateUserName(userId, name);
                        writeTeamMember.changeTeamMember(user.getTeamId(), userId, name);
                        writeGroupMember.changeGroupMemberName(user.getGroupId(), userId, name);
                    }
                });
            }
        });
    }

    public void modifyStatus(final String status) {
        writeUser = new WriteUser();
        readUser = new ReadUser();
        readUser.getUserId(new CallBack<String>() {
            @Override
            public void update(String userId) {
                writeUser.updateUserStatus(userId, status);
            }
        });
    }
}
