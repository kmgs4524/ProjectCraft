package com.example.york.teamcraft.personalsfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalsFragment extends Fragment {
    public static PersonalsFragment newInstance() {
        Bundle args = new Bundle();

        PersonalsFragment fragment = new PersonalsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PersonalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_personals, container, false);
    }

}
