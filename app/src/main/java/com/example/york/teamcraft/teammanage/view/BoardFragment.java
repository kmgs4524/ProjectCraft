package com.example.york.teamcraft.teammanage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.york.teamcraft.Work;
import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.Team;
import com.example.york.teamcraft.teammanage.model.ReadTeam;
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
    private BoardItemAdapter calendarItemAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;
    private FloatingActionButton fabAdd;

    private Team team;
    private Work work;
    private ArrayList<Work> dataList = new ArrayList<>();  // 存放RecyclerView顯示的資料

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.team_fragment_board, container, false);

        // init FirebaseUser
        user = FirebaseAuth.getInstance().getCurrentUser();

        // init Database Model
        readTeam = new ReadTeam(getActivity());

        // find view
        progressBar = (ProgressBar) view.findViewById(R.id.progress_act);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_calendar);
        fabAdd = (FloatingActionButton) view.findViewById(R.id.fab_add);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // 使用LinearLayoutManager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        readTeam.getTeamAct(new CallBack<ArrayList<Work>>() {
            @Override
            public void update(final ArrayList<Work> list) {
                progressBar.setVisibility(View.GONE);
                Log.d("getAct", Integer.toString(list.size()));
                // 設定CalendarItemAdapter
                calendarItemAdapter = new BoardItemAdapter(list, new View.OnClickListener() {   // 傳入listener callback
                    @Override
                    public void onClick(View v) {
                        int pos = recyclerView.indexOfChild(v);
                        Log.d("pos", Integer.toString(pos));
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("Work", list.get(pos));    // Work Parcelable
                        intent.putExtras(bundle);
                        intent.setClass(getActivity(), ActContentActivity.class);
                        startActivity(intent);

                    }
                });
                recyclerView.setAdapter(calendarItemAdapter);
            }
        });

        // set listener
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), AddItemActivity.class);
                startActivity(intent);
            }
        });

        // 建立CalendarItemAdapter
//        calendarItemAdapter = new BoardItemAdapter(dataList);
//        recyclerView.setAdapter(calendarItemAdapter);

        return view;

    }

}
