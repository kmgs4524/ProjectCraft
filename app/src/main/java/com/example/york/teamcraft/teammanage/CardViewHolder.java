package com.example.york.teamcraft.teammanage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/6.
 */

public class CardViewHolder extends RecyclerView.ViewHolder{
    private TextView txtGroupName;
    private TextView txtMemberNum;
    private TextView txtDetail;

    public CardViewHolder(View itemView) {
        super(itemView);
        txtGroupName = (TextView) itemView.findViewById(R.id.txt_card_item_group_name);
        txtMemberNum = (TextView) itemView.findViewById(R.id.txt_card_item_member_num);
        txtDetail = (TextView) itemView.findViewById(R.id.txt_card_item_group_detail);
    }

    public TextView getTxtGroupName() {
        return txtGroupName;
    }

    public TextView getTxtMemberNum() {
        return txtMemberNum;
    }

    public TextView getTxtDetail() {
        return txtDetail;
    }
}
