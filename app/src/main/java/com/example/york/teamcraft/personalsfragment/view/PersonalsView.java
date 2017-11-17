package com.example.york.teamcraft.personalsfragment.view;

import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.taskfragment.model.ContentTask;

import java.util.ArrayList;

/**
 * Created by York on 2017/11/3.
 */

public interface PersonalsView {
    public abstract void setCirImgPersonals(String imageUrl);
    public abstract void setTxtEmail(String email);
    public abstract void startSignInActivity();
    public abstract void showSignOutMesg();
    public abstract void setTaskNum(int undoNum, int doneNum);
    public abstract void initRecyclerView(ArrayList<DataPath> pathList, ArrayList<ContentTask> taskList);
}
