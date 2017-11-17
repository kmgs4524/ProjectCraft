package com.example.york.teamcraft.personalsfragment.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/11/17.
 */

public class PersonalTaskHolder extends RecyclerView.ViewHolder{
    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtContent;
    private CheckBox checkStatus;

    public PersonalTaskHolder(View itemView) {
        super(itemView);
        txtTitle = (TextView)itemView.findViewById(R.id.txt_personal_task_title);
        txtDate = (TextView) itemView.findViewById(R.id.txt_personal_task_date);
        txtContent = (TextView) itemView.findViewById(R.id.txt_personal_task_content);
        checkStatus = (CheckBox) itemView.findViewById(R.id.checkbox_personal_task);
    }

    public void setTxtTitle(String title) {
        this.txtTitle.setText(title);
    }

    public void setTxtDate(String date) {
        this.txtDate.setText(date);
    }

    public void setTxtContent(String content) {
        this.txtContent.setText(content);
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public TextView getTxtDate() {
        return txtDate;
    }

    public TextView getTxtContent() {
        return txtContent;
    }

    public CheckBox getCheckBox() {
        return checkStatus;
    }
}
