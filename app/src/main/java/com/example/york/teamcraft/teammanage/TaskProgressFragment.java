package com.example.york.teamcraft.teammanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;

/**
 * Created by user on 2017/7/4.
 */

//任務進度Fragment
public class TaskProgressFragment extends Fragment {

    private static final String TAG = "TaskProgressFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_taskprogress,container,false);

        return view;
    }
}
