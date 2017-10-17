package com.example.york.teamcraft.taskfragment.view;

import android.view.View;

import com.example.york.teamcraft.grouptaskfragment.ConfirmClickListener;

/**
 * Created by York on 2017/10/11.
 */

public interface TaskFragmentView {
    public void setImgAddGroupTaskListener();
    public void showAddGroupTaskDialog(ConfirmClickListener listener);
}
