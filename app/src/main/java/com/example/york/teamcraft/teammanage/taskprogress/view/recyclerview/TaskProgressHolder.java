package com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/20.
 */

public class TaskProgressHolder extends RecyclerView.ViewHolder{
    private TextView txtGroupName;
    private ProgressBar progGroup;

    public TaskProgressHolder(View itemView) {
        super(itemView);
        txtGroupName = (TextView) itemView.findViewById(R.id.txt_progress_group_name);
        progGroup = (ProgressBar) itemView.findViewById(R.id.progress_bar_group);
    }

    public TextView getTxtGroupName() {
        return txtGroupName;
    }

    public ProgressBar getProgGroup() {
        return progGroup;
    }
}
