package com.example.york.teamcraft.accountingitemdialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.accountingitemdialogfragment.viewmodel.AddAccountingItem;
import com.example.york.teamcraft.financefragment.model.AccountingItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by York on 2017/11/6.
 */

public class AddAccountingItemFragment extends DialogFragment{
    private static String TAG = "AddAccountingItem";

    public static AddAccountingItemFragment newInstance(AccountingItem item) {
        Bundle args = new Bundle();
        Log.d(TAG, "newInstance: " + item.getName());
        args.putParcelable("AccountingItem", item);
        AddAccountingItemFragment fragment = new AddAccountingItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @BindView(R.id.btn_accounting_type_cost)    // 支出 button
    Button btnCost;
    @BindView(R.id.btn_accounting_type_income)  // 收入 button
    Button btnIncome;
    @BindView(R.id.edt_accounting_item_name)   // 品項名稱 edittext
    EditText edtName;
    @BindView(R.id.edt_accounting_item_amount)   // 金額 edittext
    EditText edtAmount;
    @BindView(R.id.edt_accounting_item_date)   // 日期 edittext
    EditText edtDate;
    @BindView(R.id.edt_accounting_item_payer)   // 付款人 edittext
    EditText edtPayer;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_add_accounting_item, null);
        ButterKnife.bind(this, view);
        // Builder建立AlertDialog
        AlertDialog dialog = builder.setView(view)
                .create();
        setAccountingTypeBtnColor();
        // 將Dialog的背景色改為透明
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        AddAccountingItem item = getArguments().getParcelable("AccountingItem");

        return  dialog;
    }

    // 設定支出, 收入button的顏色
    public void setAccountingTypeBtnColor() {
        btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
    }

    // 按下支出, 收入button的事件
    @OnClick({R.id.btn_accounting_type_cost, R.id.btn_accounting_type_income})
    public void changeAccountingTypeBtnColor(View view) {
        if(view.getId() == R.id.btn_accounting_type_cost) { // 支出button
            // 改變支出, 收入button的顏色
            btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_chosen_list));
            btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        } else {    // 收入button
            btnIncome.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_chosen_list));
            btnCost.setBackgroundTintList(getContext().getResources().getColorStateList(R.color.accounting_btn_unchosen_list));
        }
    }

    // 按下確認button的事件
    @OnClick(R.id.btn_accounting_item_confirm)
    public void confirmAddItem() {
        String type;
        if(btnCost.getBackgroundTintList().equals(getResources().getColorStateList(R.color.accounting_btn_chosen_list))) {
            type = "cost";
            Log.d(TAG, "confirmAddItem: " + type);
        } else {
            type = "income";
            Log.d(TAG, "confirmAddItem: " + type);
        }

        AddAccountingItem addAccountingItem = new AddAccountingItem();
        addAccountingItem.addItem(edtName.getText().toString(), edtAmount.getText().toString(), edtPayer.getText().toString(), edtDate.getText().toString(), type);
        Toast.makeText(getContext(), "新增" + edtName.getText().toString(), Toast.LENGTH_SHORT).show();
        getDialog().dismiss();
    }
}
