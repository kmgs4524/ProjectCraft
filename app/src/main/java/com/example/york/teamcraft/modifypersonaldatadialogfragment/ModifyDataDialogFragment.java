package com.example.york.teamcraft.modifypersonaldatadialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/12/12.
 */

public class ModifyDataDialogFragment extends DialogFragment{
    // view
    private EditText edtInputField;
    // view model
    private ModifyData modifyData;

    public static ModifyDataDialogFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("TITLE", title);
        ModifyDataDialogFragment fragment = new ModifyDataDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String title = getArguments().getString("TITLE");
        modifyData = new ModifyData();
        edtInputField = new EditText(getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        AlertDialog dialog = builder.setTitle(title)
                .setView(edtInputField)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(title.equals("姓名")) {
                            modifyData.modifyName(edtInputField.getText().toString());
                        } else {
                            modifyData.modifyStatus(edtInputField.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                })
                .create();

        return dialog;
    }
}
