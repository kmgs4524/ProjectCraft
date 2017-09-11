package com.example.york.teamcraft.teammanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsmanage.*;
import com.example.york.teamcraft.personalsmanage.ItemViewAdapter;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<TeamTasks> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.team_fragment_board,container,false);

        // 放入Adapter的資料
        addData();

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_calendar);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 使用LinearLayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // 建立CalendarItemAdapter
        calendarItemAdapter = new CalendarItemAdapter(dataList);
        recyclerView.setAdapter(calendarItemAdapter);

        return view;

    }

    public void addData() {
        TeamTasks teamTasks1 = new TeamTasks("開檢討會", "9/16");
        TeamTasks teamTasks2 = new TeamTasks("準備器材", "9/20");

        dataList.add(teamTasks1);
        dataList.add(teamTasks2);
    }
}
