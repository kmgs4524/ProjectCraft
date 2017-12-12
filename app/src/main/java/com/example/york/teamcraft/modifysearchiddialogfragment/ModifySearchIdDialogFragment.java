package com.example.york.teamcraft.modifysearchiddialogfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by York on 2017/12/12.
 */

public class ModifySearchIdDialogFragment extends DialogFragment{
    private EditText edtSearchId;
    private ModifySearchId modifySearchId;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        edtSearchId = new EditText(getActivity());
        modifySearchId = new ModifySearchId();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        AlertDialog dialog = builder.setTitle("Search ID")
                .setView(edtSearchId)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        modifySearchId.modifySearchId(edtSearchId.getText().toString());
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
