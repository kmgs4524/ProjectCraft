package com.example.york.teamcraft.addteammember.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by York on 2017/12/19.
 */

public class SuccessDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle("加入成功")
                .setMessage("已加入該成員到團隊")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dismiss();
                }
        }).create();
        return dialog;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        getActivity().finish();
    }
}
