package com.example.york.teamcraft.teammanage.taskprogress.view;

import com.example.york.teamcraft.teammanage.taskprogress.model.GroupProgress;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/21.
 */

public interface TaskProgressView {
    public abstract void setRecyclerAdapter(ArrayList<GroupProgress> list);
}
