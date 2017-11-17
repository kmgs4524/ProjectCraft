package com.example.york.teamcraft.personalsfragment.view;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.personalsfragment.viewmodel.SetPersonalTask;
import com.example.york.teamcraft.personalsfragment.viewmodel.SetProfileData;
import com.example.york.teamcraft.personalsmanage.model.DataPath;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalsFragment extends Fragment implements PersonalsView{
    private static String TAG = "PersonalsFragment";
    // view
    @BindView(R.id.cir_img_personals) CircleImageView cirImgPersonals;
    @BindView(R.id.txt_personal_name) TextView txtName;
    @BindView(R.id.txt_personal_email) TextView txtEmail;
    @BindView(R.id.txt_personal_team) TextView txtTeam;
    @BindView(R.id.txt_personal_group) TextView txtGroup;
    @BindView(R.id.txt_personal_position) TextView txtPosition;
    @BindView(R.id.txt_undo_num) TextView txtUndoNum;
    @BindView(R.id.txt_done_num) TextView txtDoneNum;
    @BindView(R.id.recycler_view_personal_task)
    RecyclerView recyclerViewPersonalTask;
    private RecyclerView.LayoutManager layoutManager;
    private PersonalTaskAdapter adapter;
    // view model
    private SetProfileData setProfileData;
    private SetPersonalTask setPersonalTask;

    public static PersonalsFragment newInstance() {
        Bundle args = new Bundle();

        PersonalsFragment fragment = new PersonalsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public PersonalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_personals, container, false);
        ButterKnife.bind(this, view);
        // 設定個人資料
        setProfileData = new SetProfileData(this);
        setProfileData.setData();
        // 設定被分派細項工作
        Log.d(TAG, "onCreateView: " + "setPersonalTask");
        setPersonalTask = new SetPersonalTask(this);
        setPersonalTask.initData();

        return view;
    }

    public void setCirImgPersonals(String imageUrl) {
        try {
            Picasso.with(getContext())
                    .load(imageUrl)
                    .into(cirImgPersonals);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startSignInActivity() {
        Intent intent = new Intent();
        intent.setClass(getContext(), SignInActivity.class);
        startActivity(intent);
    }

    @Override
    public void showSignOutMesg() {
        Toast.makeText(getContext(), "帳號已登出", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setTaskNum(int undoNum, int doneNum) {
        txtUndoNum.setText(Integer.toString(undoNum));
        txtDoneNum.setText(Integer.toString(doneNum));
    }

    @Override
    public void initRecyclerView(ArrayList<DataPath> pathList, ArrayList<ContentTask> taskList) {
        Log.d(TAG, "initRecyclerView: pathList: " + pathList.size() + " taskList: " + taskList.size());
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new PersonalTaskAdapter(getContext(), recyclerViewPersonalTask, pathList, taskList);
        recyclerViewPersonalTask.setLayoutManager(layoutManager);
        recyclerViewPersonalTask.setAdapter(adapter);
    }

    @Override
    public void setProfile(String name, String email, String team, String group, String position) {
        txtName.setText(name);
        txtEmail.setText(email);
        txtTeam.setText(team);
        txtGroup.setText(group);
        txtPosition.setText(position);
    }
}
