package com.example.york.teamcraft.addgrouptaskfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/15.
 */

public class AddGroupTaskDialogFragment extends DialogFragment{
//    public static AddGroupTaskDialogFragment newInstance(FragmentActivity act) {
//        Bundle args = new Bundle();
//        AddGroupTaskDialogFragment fragment = new  AddGroupTaskDialogFragment();
//        fragment.setArguments(args);
//        return fragment;
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        builder.setTitle("新增群組工作")
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
