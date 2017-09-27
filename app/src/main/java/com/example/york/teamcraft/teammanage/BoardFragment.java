package com.example.york.teamcraft.teammanage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.ReadUser;
import com.example.york.teamcraft.Team;
import com.example.york.teamcraft.databasemodel.ReadTeam;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

/**
 * Created by user on 2017/7/4.
 */

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";

    // Database Model

    private FirebaseUser user;
    private ReadTeam readTeam;
    private ReadUser readUser;

    // UI View
    private RecyclerView recyclerView;
    private RecyclerView.Adapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<TeamActivities> dataList = new ArrayList<>();  // 存放RecyclerView顯示的資料

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_board, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();
        readUser = new ReadUser(user.getEmail());
        Task<Team> task = readUser.readUserData()
                .continueWith(new ReadTeam());
        task.addOnSuccessListener(new OnSuccessListener<Team>() {
            @Override
            public void onSuccess(Team team) {
                Log.d("getTeam", "onSuccess: " + team.getName());
            }
        });

//        ReadTeam readTeam = new ReadTeam();
//        readTeam.readData();



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
