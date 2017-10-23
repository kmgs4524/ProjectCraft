package com.example.york.teamcraft.teammanage.creategroup.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.data.GroupMember;
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
    private WriteUser writeUser;

    public CreateNewGroup(CreateGroupView view) {
        this.createGroupView = view;
        this.readUser = new ReadUser();
        this.writeTeamGroup = new WriteTeamGroup();
    }

    // 設定每個成員的職位
    public void setMemberPosition(ArrayList<GroupMember> memList, String userId) {
        for(int i = 0; i < memList.size(); i++) {
            if(memList.get(i).getUserId().equals(userId)) { // 若是該使用者創建團隊，則將他職位設定為director
                memList.get(i).setPosition("director");
            } else {
                memList.get(i).setPosition("normal");   // 若為其他人的話，職位設定為normal
            }
        }
    }

    public void create(final String groupName, final ArrayList<GroupMember> memList) {
        Task<User> task = readUser.getUserData();
        task.addOnSuccessListener(new OnSuccessListener<User>() {
            @Override
            public void onSuccess(User user) {
                final String groupId = writeTeamGroup.pushData(user.getTeamId(), groupName);  // 更新teamGroups節點並回傳groupId
                readUser.getUserId(new CallBack<String>() {
                    @Override
                    public void update(String data) {
                        setMemberPosition(memList, data);
                        writeGroupMember = new WriteGroupMember();
                        writeGroupMember.pushData(memList, groupId);    // 更新groupMembers節點的資料
                        writeUser = new WriteUser();
                        writeUser.updateUserGroup(data, groupId);   // 更新user child node的groupId
                    }
                });
                createGroupView.finishCreate();
            }
        });
    }

}
