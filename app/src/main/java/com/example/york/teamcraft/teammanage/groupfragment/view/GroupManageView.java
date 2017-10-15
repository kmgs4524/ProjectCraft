package com.example.york.teamcraft.teammanage.groupfragment.view;

import com.example.york.teamcraft.teammanage.model.Group;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/10.
 */

public interface GroupManageView {
    public abstract void initMyGroup(String groupId);
    public abstract void initGridView(ArrayList<Group> list);
}
