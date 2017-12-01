package com.example.york.teamcraft.teammanage.creategroup.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/11/29.
 */

public class SelectedMemberView extends View{
    TextView txtName;

    public SelectedMemberView(Context context, View itemView) {
        super(context);
        this.txtName = (TextView) itemView.findViewById(R.id.txt_member_name);
    }

    public void setTxtName(String name) {
        txtName.setText(name);
    }

}
