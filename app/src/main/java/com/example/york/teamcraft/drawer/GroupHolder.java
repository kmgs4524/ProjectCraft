package com.example.york.teamcraft.drawer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/27.
 */

public class GroupHolder extends RecyclerView.ViewHolder{
    private TextView txtName;
    private View itemView;

    public GroupHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        txtName = (TextView) itemView.findViewById(R.id.txt_group_name);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setItemViewListener(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
    }
}
