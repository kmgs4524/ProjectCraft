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

        txtTopic = (TextView) view.findViewById(R.id.txt_card_item_grouptask_name);
        txtContent = (TextView) view.findViewById(R.id.txt_card_item_grouptask_content);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        contentTask = (ContentTask) getArguments().get("contentTask");

        if(contentTask != null) {
            txtTopic.setText(contentTask.getTopic());
            txtContent.setText(contentTask.getContent());
        }

    }
}
