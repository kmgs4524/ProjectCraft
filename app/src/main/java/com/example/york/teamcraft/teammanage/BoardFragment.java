package com.example.york.teamcraft.teammanage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.york.teamcraft.Activity;
import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.databasemodel.ReadUser;
import com.example.york.teamcraft.Team;
import com.example.york.teamcraft.databasemodel.ReadTeam;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by user on 2017/7/4.
 */

public class BoardFragment extends Fragment {
    private static final String TAG = "BoardFragment";
    private static final Executor NETWORK_EXECUTOR = Executors.newCachedThreadPool();

    // Database Model

    private FirebaseUser user;
    private ReadTeam readTeam;
    private ReadUser readUser;

    // UI View
    private RecyclerView recyclerView;
    private RecyclerView.Adapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;

    private Team team;
    private Activity activity;
    private ArrayList<Activity> dataList = new ArrayList<>();  // 存放RecyclerView顯示的資料

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_board, container, false);

        user = FirebaseAuth.getInstance().getCurrentUser();

        readTeam = new ReadTeam(getActivity());

//        readTeam.getTeamAct(dataList);

//        final Task< ArrayList<Activity> > task = readTeam.getTeamAct(dataList);
//        task.addOnSuccessListener(new OnSuccessListener<ArrayList<Activity>>() {
//            @Override
//            public void onSuccess(ArrayList<Activity> list) {
//                Log.d("getAct", "list size in board: " + list.size());
//                Log.d("getAct", "list in board " + list.get(0).getTopic());
//                Log.d("getAct", "list size in board: " + list.get(1).getTopic());
//            }
//        });
        progressBar = (ProgressBar) view.findViewById(R.id.progress_act);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_calendar);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 使用LinearLayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        readTeam.execute(new CallBack() {
            @Override
            public void update(ArrayList<Activity> list) {
                progressBar.setVisibility(View.GONE);
                calendarItemAdapter = new BoardItemAdapter(list);
                recyclerView.setAdapter(calendarItemAdapter);
            }
        });

        // 建立CalendarItemAdapter
//        calendarItemAdapter = new BoardItemAdapter(dataList);
//        recyclerView.setAdapter(calendarItemAdapter);

        return view;

    }

}
