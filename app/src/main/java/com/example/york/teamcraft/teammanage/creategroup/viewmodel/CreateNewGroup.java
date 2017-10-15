package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.creategroup.model.WriteGroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.WriteTeamGroup;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WriteUser;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/13.
 */

public class CreateNewGroup {
    // view
    private CreateGroupView createGroupView;
    // model
    private ReadUser readUser;
    private WriteTeamGroup writeTeamGroup;
    private WriteGroupMember writeGroupMember;

    public CreateNewGroup(CreateGroupView view) {
        this.createGroupView = view;
        this.readUser = new ReadUser();
        this.writeTeamGroup = new WriteTeamGroup();
    }

    public void create(final String groupName, final ArrayList<Member> memList) {
        Task<User> task = readUser.getUserData();
        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                String groupId = writeTeamGroup.pushData(user.getTeamId(), groupName);  // 更新teamGroups節點並回傳groupId
                WriteGroupMember writeGroupMember = new WriteGroupMember();
                writeGroupMember.pushData(memList, groupId);    // 更新groupMembers節點的資料
                createGroupView.finishCreate();
            }
        });
    }

}
