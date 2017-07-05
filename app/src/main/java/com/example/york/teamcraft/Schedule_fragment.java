package com.example.york.teamcraft;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2017/7/4.
 */

public class Schedule_fragment extends Fragment {
    private static final String TAG = "Schedule_fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.schedule_fragment,container,false);

        return view;
    }
}
