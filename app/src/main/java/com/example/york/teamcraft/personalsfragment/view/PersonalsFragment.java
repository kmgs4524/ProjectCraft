package com.example.york.teamcraft.personalsfragment.view;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.personalsfragment.viewmodel.SetPersonalData;
import com.example.york.teamcraft.personalsmanage.view.PersonalTasksActivity;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalsFragment extends Fragment implements PersonalsView{
    @BindView(R.id.cir_img_personals) CircleImageView cirImgPersonals;
    @BindView(R.id.txt_personal_email) TextView txtEmail;
    @BindView(R.id.img_personal_mission) ImageView imgPersonal;
    @BindView(R.id.img_account_management) ImageView imgAccount;
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

        return view;
    }

    public void setCirImgPersonals(String imageUrl) {
        Picasso.with(getContext())
                .load(imageUrl)
                .into(cirImgPersonals);
    }

    @Override
    public void setTxtEmail(String email) {
        txtEmail.setText(email);
    }

    @OnClick(R.id.img_personal_mission)
    public void startPersonTaskAct() {
        Intent intent = new Intent();
        intent.setClass(getContext(), PersonalTasksActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.img_account_management)
    public void startAccountManagAct() {
        Intent intent = new Intent();
        intent.setClass(getContext(), PersonalTasksActivity.class);
        startActivity(intent);
    }

}
