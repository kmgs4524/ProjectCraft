package com.example.york.teamcraft.teammanage.creategroup.view;

import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/13.
 */

public interface CreateGroupView {
    public void finishCreate();
    public void setSpinMenu(ArrayList<GroupMember> memList);
}
