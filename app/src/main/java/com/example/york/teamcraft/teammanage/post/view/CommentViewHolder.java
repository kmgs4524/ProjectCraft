package com.example.york.teamcraft.teammanage.post.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.york.teamcraft.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by York on 2017/10/7.
 */

public class CommentViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView cirView;
    private TextView txtName;
    private TextView txtComm;


    public CommentViewHolder(View itemView) {
        super(itemView);
        cirView = (CircleImageView) itemView.findViewById(R.id.cir_img_member);
        txtName = (TextView) itemView.findViewById(R.id.txt_item_member_name);
        txtComm = (TextView) itemView.findViewById(R.id.txt_item_member_comment);
    }

    public CircleImageView getCirView() {
        return cirView;
    }

    public TextView getTxtName() {
        return txtName;
    }

    public TextView getTxtComm() {
        return txtComm;
    }
}
