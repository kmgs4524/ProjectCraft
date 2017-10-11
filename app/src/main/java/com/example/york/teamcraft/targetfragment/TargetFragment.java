package com.example.york.teamcraft.targetfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;


public class TargetFragment extends Fragment {

    public static TargetFragment newInstance() {
        TargetFragment targetFragment = new TargetFragment();

        return targetFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_target, container, false);

        return view;
    }
}
