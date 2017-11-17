package com.example.york.teamcraft.personalsfragment.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.login.view.SignInActivity;
import com.example.york.teamcraft.personalsfragment.viewmodel.SetPersonalData;
import com.example.york.teamcraft.personalsfragment.viewmodel.SignIn;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksActivity;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalsFragment extends Fragment implements PersonalsView{
    // view
    @BindView(R.id.cir_img_personals) CircleImageView cirImgPersonals;
    @BindView(R.id.txt_personal_email) TextView txtEmail;
    @BindView(R.id.recycler_view_personal_task)
    RecyclerView recyclerViewPersonalTask;
    private RecyclerView.LayoutManager layoutManager;
    private PersonalTaskAdapter adapter;
    // view model
    private SetPersonalData setPersonalData;

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

        setPersonalData = new SetPersonalData(this);
        setPersonalData.setData();

        initRecyclerView();
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
    public void initRecyclerView() {
        ArrayList<ContentTask> list = new ArrayList<>();
        list.add(new ContentTask("1", "帶音響", "記得帶音響", "123", "李侑乘", "10/3", "2:30", "undo"));
        list.add(new ContentTask("1", "帶音響", "記得帶音響", "123", "李侑乘", "10/3", "2:30", "undo"));
        list.add(new ContentTask("1", "帶音響", "記得帶音響", "123", "李侑乘", "10/3", "2:30", "undo"));

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new PersonalTaskAdapter(getContext(), recyclerViewPersonalTask, list);
        recyclerViewPersonalTask.setLayoutManager(layoutManager);
        recyclerViewPersonalTask.setAdapter(adapter);
    }

    @Override
    public void setTxtEmail(String email) {
        txtEmail.setText(email);
    }

}
