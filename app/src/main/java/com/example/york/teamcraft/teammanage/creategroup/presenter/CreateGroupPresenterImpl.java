package com.example.york.teamcraft.teammanage.creategroup.presenter;

import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.teammanage.creategroup.model.WriteTeamGroup;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupView;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.CreateNewGroup;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SaveGroupMember;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SetSpinnerData;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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
    public void saveSpinnerData(Member member) {
        if(saveGroupMember  == null) {
            saveGroupMember = new SaveGroupMember();
            saveGroupMember.addMember(member);
        } else {
            saveGroupMember.addMember(member);
        }
    }

    public SaveGroupMember getSaveGroupMember() {
        return saveGroupMember;
    }

    @Override
    public void createGroup(String groupName, ArrayList<Member> memList) {
        createNewGroup = new CreateNewGroup(createGroupView);
        createNewGroup.create(groupName, memList);   // 寫入teamGroup後再寫入groupMember
    }

}
