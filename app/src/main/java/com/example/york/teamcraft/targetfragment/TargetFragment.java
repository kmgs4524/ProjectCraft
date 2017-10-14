package com.example.york.teamcraft.targetfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.taskfragment.model.ContentTask;


public class TargetFragment extends Fragment {
    // received data
    private ContentTask contentTask;
    // view
    private TextView txtTopic;
    private TextView txtContent;
    private TextView txtResponsible;
    private TextView txtDate;
    private TextView txtTime;

    // Factory Method: 傳入需要的參數，讓fragment在建構時便擁有contentTask的資料
    public static TargetFragment newInstance(ContentTask contentTask) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("contentTask", contentTask);
        TargetFragment targetFragment = new TargetFragment();
        targetFragment.setArguments(bundle);

        return targetFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_target, container, false);
        // find view
        txtTopic = (TextView) view.findViewById(R.id.txt_card_item_grouptask_name);
        txtContent = (TextView) view.findViewById(R.id.txt_card_item_grouptask_content);
        txtResponsible = (TextView) view.findViewById(R.id.txt_card_item_grouptask_respn_name);
        txtDate = (TextView) view.findViewById(R.id.txt_card_item_grouptask_date);
        txtTime = (TextView) view.findViewById(R.id.txt_card_item_grouptask_time);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        contentTask = (ContentTask) getArguments().get("contentTask");

        if(contentTask != null) {   // 第一次進入活動工作頁面時，contentTask會是null
            txtTopic.setText(contentTask.getTopic());
            txtContent.setText(contentTask.getContent());
            txtResponsible.setText(contentTask.getResponsible());
            txtDate.setText(contentTask.getDate());
            txtTime.setText(contentTask.getTime());
        }

    }
}
