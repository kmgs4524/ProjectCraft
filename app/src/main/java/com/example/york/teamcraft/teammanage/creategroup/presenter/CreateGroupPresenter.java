package com.example.york.teamcraft.teammanage.creategroup.presenter;

import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SaveGroupMember;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/13.
 */

public interface CreateGroupPresenter {
    public void setSpinMenu();
    public void saveSpinnerData(GroupMember groupMember);
    public SaveGroupMember getSaveGroupMember();
    public void createGroup(String groupName, ArrayList<GroupMember> memList);
}
