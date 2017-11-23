package com.example.york.teamcraft.schedulefragment;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by York on 2017/11/19.
 */

public class OutputDialogFragment extends DialogFragment{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getContext(), ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("請稍等...");
        dialog.setMessage("匯出事前工作表...");
        return  dialog;
    }
}
