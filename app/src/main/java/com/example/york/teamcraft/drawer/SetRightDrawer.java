package com.example.york.teamcraft.drawer;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by York on 2017/10/27.
 */

public class SetRightDrawer {
    private RecyclerView recyclerView;
    private MemberItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<TeamMember> memList;

    public void setRightDrawer(FragmentActivity act) {
        recyclerView = (RecyclerView) act.findViewById(R.id.recycler_view_right_drawer);
        memList = new ArrayList<>();
        memList.add(new TeamMember("李侑乘"));
        memList.add(new TeamMember("小球球"));

        adapter = new MemberItemAdapter(memList);
        layoutManager = new LinearLayoutManager(act);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

}
