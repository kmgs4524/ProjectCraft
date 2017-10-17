package com.example.york.teamcraft.addcontenttask.view;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addcontenttask.presenter.AddContentTaskPresenter;
import com.example.york.teamcraft.addcontenttask.presenter.AddContentTaskPresenterImpl;
import com.example.york.teamcraft.datepickerfragment.DatePickerFragment;

public class AddContentTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    // view
    private EditText edtTitle;
    private EditText edtContent;
    private ImageView imgDate;
    private ImageView imgTime;
    private TextView txtDate;
    private TextView txtTime;
    // presenter
    private AddContentTaskPresenter addContentTaskPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content_task);
        initImgDate();

        addContentTaskPresenter  = new AddContentTaskPresenterImpl(this);
    }

    public void initImgDate() {
        imgDate = (ImageView) findViewById(R.id.img_add_content_task_date);
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "");
            }
        });
    }

    public void initEdt() {
        imgTime = (ImageView) findViewById(R.id.img_add_content_task_time);
        imgTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DatePickerFragment datePickerFragment = new DatePickerFragment();
//                datePickerFragment.show(getSupportFragmentManager(), "");
            }
        });
    }

    public void setTxtDate(int year, int month, int dayOfMonth) {
        txtDate.setText(year + " 年 " + month + " 月 " + dayOfMonth + " 日 ");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setTxtDate(year, month, dayOfMonth);

    }

}
