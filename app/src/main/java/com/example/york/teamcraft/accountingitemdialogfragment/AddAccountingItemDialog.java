package com.example.york.teamcraft.accountingitemdialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.example.york.teamcraft.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by York on 2017/11/6.
 */

public class AddAccountingItemDialog extends DialogFragment{
    @BindView(R.id.btn_accounting_type_cost)
    Button btnCost;
    @BindView(R.id.btn_accounting_type_income)
    Button btnIncome;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_accounting_item, null);
        ButterKnife.bind(this, view);
        builder.setView(view)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        setAccountingTypeBtnColor();
        Dialog dialog = builder.create();
        // 將Dialog的背景色改為透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return  dialog;
    }

    public void setAccountingTypeBtnColor() {
        btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
    }

    @OnClick({R.id.btn_accounting_type_cost, R.id.btn_accounting_type_income})
    public void changeAccountingTypeBtnColor(View view) {
        if(view.getId() == R.id.btn_accounting_type_cost) {
            // 改變支出, 收入button的顏色
            btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_chosen_list));
            btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        } else {
            btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_chosen_list));
            btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        }
    }
}
