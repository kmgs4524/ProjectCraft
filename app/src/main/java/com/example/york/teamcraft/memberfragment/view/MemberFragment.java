package com.example.york.teamcraft.memberfragment.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;
import com.example.york.teamcraft.memberfragment.presenter.MemberFragmentPresenter;
import com.example.york.teamcraft.memberfragment.presenter.MemberFragmentPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberFragment extends Fragment implements MemberView{
    // presenter
    MemberFragmentPresenter presenter;
    // view
    @BindView(R.id.recycler_view_member_list)
    RecyclerView recyclerViewMemberList;

    public MemberFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        ButterKnife.bind(this, view);

        presenter = new MemberFragmentPresenterImpl(this);
        presenter.initRecyclerViewData();

        return view;
    }

    @Override
    public void initRecyclerView(ArrayList<SectionOrItem> sectionOrItems) {
        recyclerViewMemberList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMemberList.setAdapter(new MemberListAdapter(getContext(), sectionOrItems));
    }

}
