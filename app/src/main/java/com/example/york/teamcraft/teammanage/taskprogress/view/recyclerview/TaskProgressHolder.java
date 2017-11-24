package com.example.york.teamcraft.teammanage.taskprogress.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.IconRoundCornerProgressBar;
import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/20.
 */

public class TaskProgressHolder extends RecyclerView.ViewHolder{
    private TextView txtGroupName;
    private TextView txtProgPercent;
    private IconRoundCornerProgressBar progGroup;
//    private ProgressBar progGroup;

    public TaskProgressHolder(View itemView) {
        super(itemView);
        txtGroupName = (TextView) itemView.findViewById(R.id.txt_progress_group_name);
        progGroup = (IconRoundCornerProgressBar) itemView.findViewById(R.id.progress_bar_group);
        txtProgPercent = (TextView) itemView.findViewById(R.id.txt_progress_percent);
    }

    public TextView getTxtGroupName() {
        return txtGroupName;
    }

    public IconRoundCornerProgressBar getProgGroup() {
        return progGroup;
    }

//    public ProgressBar getProgGroup() {
//        return progGroup;
//    }

    public TextView getTxtProgPercent() {
        return txtProgPercent;
    }
}
