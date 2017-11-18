package com.example.york.teamcraft.teammanage.actionfragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.creategroup.view.CreateGroupActivity;
import com.example.york.teamcraft.teammanage.tasktable.TaskTableActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActionFragment extends Fragment {
    private static String TAG = "ActionFragment";
    @BindView(R.id.linear_layout_add_member) LinearLayout layoutAddMember;
    @BindView(R.id.linear_layout_create_group) LinearLayout layoutCreateGroup;
    @BindView(R.id.linear_layout_task_table) LinearLayout layoutTaskTable;

    public static ActionFragment newInstance() {
        Bundle args = new Bundle();

        ActionFragment fragment = new ActionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ActionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.team_fragment_action, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.linear_layout_add_member, R.id.linear_layout_create_group, R.id.linear_layout_task_table})
    public void startAction(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.linear_layout_add_member:
                Log.d(TAG, "startAction: " + v.getId());
                break;
            case R.id.linear_layout_create_group:
                intent.setClass(getActivity(), CreateGroupActivity.class);
                startActivity(intent);
                break;
            case R.id.linear_layout_task_table:
                intent.setClass(getActivity(), TaskTableActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
