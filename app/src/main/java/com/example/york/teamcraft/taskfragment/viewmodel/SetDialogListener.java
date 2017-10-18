package com.example.york.teamcraft.taskfragment.viewmodel;

import android.os.Parcel;

import com.example.york.teamcraft.grouptaskfragment.ConfirmClickListener;
import com.example.york.teamcraft.personalsmanage.model.WriteGroupTask;
import com.example.york.teamcraft.taskfragment.view.TaskFragmentView;

/**
 * Created by York on 2017/10/15.
 */

// view model
// 負責處理按下群組選單後檢討
public class SetDialogListener implements ConfirmClickListener{
    // model
    private WriteGroupTask writeGroupTask;
    private String groupId; // 從presenter傳來的groupId

    public SetDialogListener(String id) {
        this.groupId = id;
    }

    protected SetDialogListener(Parcel in) {}

    public void showAddGroupTaskDialog(TaskFragmentView taskFragmentView) {
        taskFragmentView.showAddGroupTaskDialog(this);
    }

    // Callback method
    @Override
    public void update(String groupTaskTitle) {
        writeGroupTask = new WriteGroupTask();
        writeGroupTask.writeGroupTaskName(groupId, groupTaskTitle);
    }

    public static final Creator<SetDialogListener> CREATOR = new Creator<SetDialogListener>() {
        @Override
        public SetDialogListener createFromParcel(Parcel in) {
            return new SetDialogListener(in);
        }

        @Override
        public SetDialogListener[] newArray(int size) {
            return new SetDialogListener[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

}
