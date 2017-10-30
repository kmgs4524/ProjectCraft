package com.example.york.teamcraft.drawer;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.data.GroupMember;
import com.example.york.teamcraft.teammanage.creategroup.model.ReadTeamMember;
import com.example.york.teamcraft.teammanage.jointeam.model.TeamMember;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/27.
 */

public class SetRightDrawer {
    private ReadTeamMember readTeamMember;
    private ReadUser readUser;

    private RecyclerView recyclerView;
    private MemberItemAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
//    private ArrayList<TeamMember> memList;

    public void setRightDrawer(final FragmentActivity act) {
//        recyclerView = (RecyclerView) act.findViewById(R.id.recycler_view_right_drawer);
//
//        readUser = new ReadUser();
//        readUser.getUserData(new CallBack<User>() {
//            @Override
//            public void update(User user) {
//                readTeamMember = new ReadTeamMember();
//                readTeamMember.getTeamMember(user.getTeamId(), new CallBack<ArrayList<TeamMember>>() {
//                    @Override
//                    public void update(ArrayList<TeamMember> memList) {
//                        adapter = new MemberItemAdapter(memList);
//                        layoutManager = new LinearLayoutManager(act);
//                        recyclerView.setAdapter(adapter);
//                        recyclerView.setLayoutManager(layoutManager);
//                    }
//                });
//            }
//        });
    }

}
