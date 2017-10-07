package com.example.york.teamcraft.teammanage.groupmanage.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.groupmanage.model.Member;

import java.util.ArrayList;

public class MemberFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Member> memberList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.team_fragment_member, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_member);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        memberList = new ArrayList<>();
        memberList.add(new Member("吳奇隆", "組長"));
        memberList.add(new Member("李侑乘", "組員"));
        memberList.add(new Member("李聖傑", "組員"));
        memberList.add(new Member("王語嫣", "組員"));

        adapter = new MemberItemAdapter(memberList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
