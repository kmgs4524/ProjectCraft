package com.example.york.teamcraft.memberfragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addteammember.view.AddTeamMemberActivity;
import com.example.york.teamcraft.memberfragment.data.SectionOrItem;
import com.example.york.teamcraft.memberfragment.presenter.MemberFragmentPresenter;
import com.example.york.teamcraft.memberfragment.presenter.MemberFragmentPresenterImpl;
import com.example.york.teamcraft.modifysearchiddialogfragment.ModifySearchIdDialogFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MemberFragment extends Fragment implements MemberView{
    // presenter
    MemberFragmentPresenter presenter;
    // empty state view
    @BindView(R.id.img_member_empty_state)
    ImageView imgEmptyState;
    @BindView(R.id.divider_member_empty_state)
    View dividerEmptyState;
    @BindView(R.id.txt_member_empty_state)
    TextView txtInstruction;
    @BindView(R.id.btn_member_empty_state)
    Button btnEmptyStateAddMember;
    // view
    @BindView(R.id.txt_search_id)
    TextView txtSearchId;
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
        presenter.initSearchId();
        presenter.checkState(new CallBack<Boolean>() {
            @Override
            public void update(Boolean isExisting) {
                if(isExisting) {
                    hideEmptyView();
                    showMemberList();
                    presenter.initRecyclerViewData();
                } else {
                    showEmptyView();
                }
            }
        });

        return view;
    }

    // 隱藏empty state的畫面元件
    @Override
    public void hideEmptyView() {
        imgEmptyState.setVisibility(View.GONE);
        dividerEmptyState.setVisibility(View.GONE);
        txtInstruction.setVisibility(View.GONE);
        btnEmptyStateAddMember.setVisibility(View.GONE);
    }

    @Override
    public void showMemberList() {
        recyclerViewMemberList.setVisibility(View.VISIBLE);
    }

    // 若為empty state時顯示初始畫面
    @Override
    public void showEmptyView() {
        imgEmptyState.setVisibility(View.VISIBLE);
        dividerEmptyState.setVisibility(View.VISIBLE);
        txtInstruction.setVisibility(View.VISIBLE);
        btnEmptyStateAddMember.setVisibility(View.VISIBLE);
    }

    @Override
    public void initTxtSearchId(String searchId) {
        txtSearchId.setText(searchId);
    }

    @Override
    public void initRecyclerView(ArrayList<SectionOrItem> sectionOrItems) {
        recyclerViewMemberList.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewMemberList.setAdapter(new MemberListAdapter(getContext(), sectionOrItems));
        Log.d("test recycler", "initRecyclerView: " + sectionOrItems.size());
    }

    @OnClick(R.id.btn_modify_search_id)
    public void showModifyDialogFragment() {
        ModifySearchIdDialogFragment modifySearchIdDialogFragment = new ModifySearchIdDialogFragment();
        modifySearchIdDialogFragment.show(getFragmentManager(), "ModifySearchIdDialogFragment");
    }

    @OnClick({R.id.btn_member_empty_state, R.id.fab_add_team_member})
    public void addTeamMember() {
        Intent intent = new Intent();
        intent.setClass(getActivity(), AddTeamMemberActivity.class);
        startActivity(intent);
    }

}
