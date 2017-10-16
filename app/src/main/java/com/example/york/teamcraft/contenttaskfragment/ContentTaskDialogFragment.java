package com.example.york.teamcraft.contenttaskfragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.datepickerfragment.DatePickerFragment;
import com.example.york.teamcraft.grouptaskfragment.ConfirmClickListener;
import com.example.york.teamcraft.grouptaskfragment.GroupTaskDialogFragment;

/**
 * Created by York on 2017/10/16.
 */

public class ContentTaskDialogFragment extends DialogFragment{
    private ConfirmClickListener listener;

    public static ContentTaskDialogFragment newInstance(ConfirmClickListener listener) {
        Bundle args = new Bundle();
        args.putParcelable("listener", listener);
        ContentTaskDialogFragment fragment = new ContentTaskDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 取得dialog內容的呈現view
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_content_task, null);   // 細項工作選單xml檔來源
        final EditText edtTopic = (EditText) dialogView.findViewById(R.id.edt_group_task_topic);
        ImageView imgDate = (ImageView) dialogView.findViewById(R.id.img_dialog_content_task_date);
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getFragmentManager(), "datePickerFragment");
            }
        });

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

