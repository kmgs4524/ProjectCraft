package com.example.york.teamcraft.finance.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/11/5.
 */

public class AccountingItemViewHolder extends RecyclerView.ViewHolder{
    private TextView txtName;
    private TextView txtAmount;

    public AccountingItemViewHolder(View itemView) {
        super(itemView);
        txtName = (TextView) itemView.findViewById(R.id.txt_account_item_name);
        txtAmount = (TextView) itemView.findViewById(R.id.txt_account_item_amount);
    }

    public TextView getTxtName() {
        return txtName;
    }

    public void setTxtName(TextView txtName) {
        this.txtName = txtName;
    }

    public TextView getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(TextView txtAmount) {
        this.txtAmount = txtAmount;
    }
}
