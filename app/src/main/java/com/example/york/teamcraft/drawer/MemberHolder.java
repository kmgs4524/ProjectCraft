package com.example.york.teamcraft.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/27.
 */

public class MemberHolder extends RecyclerView.ViewHolder{
    private TextView txtName;

    public MemberHolder(View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txt_team_member_name);
    }

    public TextView getTxtName() {
        return txtName;
    }
}
