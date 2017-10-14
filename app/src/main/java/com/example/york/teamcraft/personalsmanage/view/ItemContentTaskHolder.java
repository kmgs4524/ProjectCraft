package com.example.york.teamcraft.personalsmanage.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/13.
 */

public class ItemContentTaskHolder extends RecyclerView.ViewHolder{
    private CheckBox checkTask;
    private TextView txtTitle, txtDate, txtTime;

    public ItemContentTaskHolder(View itemView) {
        super(itemView);
        checkTask = (CheckBox) itemView.findViewById(R.id.checkbox_personal_contenttask);
        txtTitle = (TextView) itemView.findViewById(R.id.txt_personal_contenttask_topic);
        txtDate = (TextView) itemView.findViewById(R.id.txt_personal_contenttask_date);
        txtTime = (TextView) itemView.findViewById(R.id.txt_personal_contenttask_time);
    }
    public CheckBox getCheckTask() { return checkTask; }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtTime() {
        return txtTime;
    }
}
