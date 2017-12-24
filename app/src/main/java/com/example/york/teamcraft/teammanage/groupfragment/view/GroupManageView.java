package com.example.york.teamcraft.teammanage.groupfragment.view;

import com.example.york.teamcraft.teammanage.model.Group;
import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/10.
 */

public interface GroupManageView {
    public abstract void showActionLayout();
    public abstract void showEmptyState();
    public abstract void hideEmptyState();
    public abstract void initRecyclerProgress(ArrayList<GroupProgress> progList);
    public abstract void initRecyclerGroup(ArrayList<Group> list);
}
