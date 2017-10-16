package com.example.york.teamcraft.grouptaskfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.york.teamcraft.R;

/**
 * Created by York on 2017/10/15.
 */

public class GroupTaskDialogFragment extends DialogFragment{
    private ConfirmClickListener listener;

    public static GroupTaskDialogFragment newInstance(ConfirmClickListener listener) {
        Bundle args = new Bundle();
        args.putParcelable("listener", listener);
        GroupTaskDialogFragment fragment = new GroupTaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 取得dialog內容的呈現view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_group_task, null);
        final EditText edtTopic = (EditText) dialogView.findViewById(R.id.edt_group_task_topic);
        builder.setView(dialogView)
                .setPositiveButton("確認", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String groupTopic = edtTopic.getText().toString();
                        listener = getArguments().getParcelable("listener");
                        listener.update(groupTopic);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

}
