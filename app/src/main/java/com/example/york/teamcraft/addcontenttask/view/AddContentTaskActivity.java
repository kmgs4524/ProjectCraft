package com.example.york.teamcraft.addcontenttask.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.addcontenttask.presenter.AddContentTaskPresenter;
import com.example.york.teamcraft.addcontenttask.presenter.AddContentTaskPresenterImpl;
import com.example.york.teamcraft.addcontenttask.viewmodel.AddNewTask;
import com.example.york.teamcraft.addcontenttask.viewmodel.SaveInputData;
import com.example.york.teamcraft.addcontenttask.viewmodel.SetSpinData;
import com.example.york.teamcraft.datepickerfragment.DatePickerFragment;
import com.example.york.teamcraft.member.Member;
import com.example.york.teamcraft.taskfragment.model.ContentTask;
import com.example.york.teamcraft.teammanage.creategroup.view.SpinMenuAdapter;
import com.example.york.teamcraft.teammanage.creategroup.viewmodel.SetSpinnerData;

import java.util.ArrayList;

// 此Activity使用view model與後端互動
public class AddContentTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, AddContentTaskView{
    // 傳進來的group id
    private String groupName;
    // view
    private EditText edtTitle;
    private EditText edtContent;
    private ImageView imgDate;
    private ImageView imgTime;
    private TextView txtDate;
    private TextView txtTime;
    private Spinner spnGroupMember;
    private Button btnConfirm;
    // view model
    private SetSpinData setSpinData;
    private SaveInputData saveInputData;
    private AddNewTask addNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_content_task);
        // find view
        initView();
        // get passed group name
        getPassedGroupName();
        // set listener
        setImgDateListener();
        setBtnListener();
        // init view model
        setSpinData = new SetSpinData(this);
        saveInputData = new SaveInputData();
        addNewTask = new AddNewTask(this);
        // set date source of responsible
        setSpinData.setData();
    }

    // 取得TaskFragment傳來的group name
    public void getPassedGroupName() {
        Intent intent = getIntent();
        this.groupName = intent.getExtras().getString("groupTaskName");
    }

    public void initView() {
        edtTitle = (EditText) findViewById(R.id.edt_add_content_task_topic);
        edtContent = (EditText) findViewById(R.id.edt_add_content_task_content);
        txtDate = (TextView) findViewById(R.id.txt_add_content_task_date);
        imgDate = (ImageView) findViewById(R.id.img_add_content_task_date);
        spnGroupMember = (Spinner) findViewById(R.id.spin_group_member);
        btnConfirm = (Button) findViewById(R.id.btn_add_content_task_confirm);
    }

    // 設定按下日曆圖案後顯示DatePickerFragment
    public void setImgDateListener() {
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePickerFragment = new DatePickerFragment();
                datePickerFragment.show(getSupportFragmentManager(), "");
            }
        });
    }

    // 設定負責人Spinner
    // 參數為選單的資料來源
    public void setSpinMenu(final ArrayList<Member> memList) {
        ArrayAdapter<Member> spinMenuAdapter = new SpinMenuAdapter(this, R.layout.spinner_init, memList);
        // Specify the layout to use when the list of choices appears
        spinMenuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spnGroupMember.setAdapter(spinMenuAdapter);
        spnGroupMember.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                saveInputData.saveResponsible(memList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // 設置按下確認按鈕後的事件
    public void setBtnListener() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewTask.addContentTask(groupName, new ContentTask(
                        null,
                        edtTitle.getText().toString(),
                        edtContent.getText().toString(),
                        saveInputData.getResponsible().getUserId(),
                        saveInputData.getResponsible().getName(),
                        saveInputData.getDate(),
                        null,
                        "undo"
                ));
            }
        });
    }

    public void finishActivity() {
        finish();
    }

    // 顯示選擇的日期
    public void setTxtDate(int year, int month, int dayOfMonth) {
        month = month + 1;
        txtDate.setText(year + " 年 " + month + " 月 " + dayOfMonth + " 日 ");
    }

    // 取得DatePickFragment的年月日資料的callback
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setTxtDate(year, month, dayOfMonth);
        saveInputData.saveDate(year, month + 1, dayOfMonth);
    }

}
