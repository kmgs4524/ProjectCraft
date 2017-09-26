package com.example.york.teamcraft.teammanage;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.databasemodel.ReadTeam;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";

    // Database Model
    private ReadTeam readTeam;

    // UI View
    private RecyclerView recyclerView;
    private RecyclerView.Adapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<TeamActivities> dataList = new ArrayList<>();  // 存放RecyclerView顯示的資料

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_board, container, false);

        final TaskCompletionSource<ReadTeam> source = new TaskCompletionSource();
        source.setResult(new ReadTeam());
        Task<ReadTeam> task = source.getTask();
        task.addOnCompleteListener(new OnCompleteListener<ReadTeam>() {
            @Override
            public void onComplete(@NonNull Task<ReadTeam> task) {
                readTeam = task.getResult();
                readTeam.readData();
            }
        });



        addData(); // 將後端資料放入Adapter

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_calendar);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 使用LinearLayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // 建立CalendarItemAdapter
        calendarItemAdapter = new BoardItemAdapter(dataList);
        recyclerView.setAdapter(calendarItemAdapter);

        return view;

    }

    // 將資料放入Adapter
    public void addData() {
        TeamActivities teamActivities1 = new TeamActivities("開檢討會", "9/16");
        TeamActivities teamActivities2 = new TeamActivities("準備器材", "9/20");

        dataList.add(teamActivities1);
        dataList.add(teamActivities2);
    }
}
