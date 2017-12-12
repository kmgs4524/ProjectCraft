package com.example.york.teamcraft.personaldata.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.modifypersonaldatadialogfragment.ModifyDataDialogFragment;
import com.example.york.teamcraft.personaldata.viewmodel.SetPersonalData;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalDataActivity extends AppCompatActivity implements PersonalDataView{
    // model
    @BindView(R.id.cir_img_portrait)
    CircleImageView cirImgPortrait;
    @BindView(R.id.txt_personal_data_name_field)
    TextView txtName;
    @BindView(R.id.txt_personal_data_email_field)
    TextView txtEmail;
    @BindView(R.id.txt_personal_data_status_field)
    TextView txtStatus;
    // view model
    private SetPersonalData setPersonalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);

        setPersonalData = new SetPersonalData(this);
        setPersonalData.setData();
    }

    @Override
    public void initAllData(String imageUrl, String name, String email, String status) {
        Picasso.with(this)
                .load(imageUrl)
                .into(cirImgPortrait);
        txtName.setText(name);
        txtEmail.setText(email);
        txtStatus.setText(status);
    }

    @OnClick(R.id.linear_layout_personal_data_name)
    public void showModifyNameDialogFragment() {
        ModifyDataDialogFragment modifyDatadialogFragment = ModifyDataDialogFragment.newInstance("姓名");
        modifyDatadialogFragment.show(getSupportFragmentManager(), "ModifyDataDialogFragment");
    }

    @OnClick(R.id.linear_layout_personal_data_status)
    public void showModifyStatusDialogFragment() {
        ModifyDataDialogFragment modifyDatadialogFragment = ModifyDataDialogFragment.newInstance("狀態");
        modifyDatadialogFragment.show(getSupportFragmentManager(), "ModifyDataDialogFragment");
    }
}
