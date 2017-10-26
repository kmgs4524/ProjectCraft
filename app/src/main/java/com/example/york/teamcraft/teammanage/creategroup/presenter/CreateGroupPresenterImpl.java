package com.example.york.teamcraft.teammanage.creategroup.presenter;

import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.CreateNewGroup;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SaveGroupMember;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SetSpinnerData;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/13.
 */

public class CreateGroupPresenterImpl implements CreateGroupPresenter{
    // view
    private CreateGroupView createGroupView;
    // view-model
    private SetSpinnerData setSpinnerData;
    private SaveGroupMember saveGroupMember;
    private CreateNewGroup createNewGroup;

    public CreateGroupPresenterImpl(CreateGroupView view) {
        createGroupView = view;
    }

    // 設定成員選單的資料來源
    public void setSpinMenu() {
        setSpinnerData = new SetSpinnerData(createGroupView);
        setSpinnerData.setData();
    }

    // 儲存選擇的成員
    public void saveSpinnerData(GroupMember groupMember) {
        if(saveGroupMember  == null) {
            saveGroupMember = new SaveGroupMember();
            saveGroupMember.addMember(groupMember);
        } else {
            saveGroupMember.addMember(groupMember);
        }
    }

    public SaveGroupMember getSaveGroupMember() {
        return saveGroupMember;
    }

    @Override
    public void createGroup(String groupName, ArrayList<GroupMember> memList) {
        createNewGroup = new CreateNewGroup(createGroupView);
        createNewGroup.create(groupName, memList);   // 寫入teamGroup後再寫入groupMember
    }

}
