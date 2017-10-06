package com.example.york.teamcraft.teammanage.view;

import android.app.DatePickerDialog;

import android.support.v4.app.DialogFragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.presenter.AddItemPresenter;
import com.example.york.teamcraft.teammanage.presenter.AddItemPresenterImpl;

public class AddItemActivity extends AppCompatActivity implements AddItemView, View.OnClickListener, DatePickerDialog.OnDateSetListener{
    // UI View
    private EditText edtTitle;
    private EditText edtContent;
    private EditText edtDate;
    private ImageView imgDate;
    private DatePickerDialog datePicker;
    private TextView txtDate;

    // Presenter
    private AddItemPresenter addItemPresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_add_item);

        // findView

        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtContent = (EditText) findViewById(R.id.edt_content);
        imgDate = (ImageView) findViewById(R.id.img_date);
        imgDate.setOnClickListener(this);
        txtDate = (TextView) findViewById(R.id.txt_date);

        // init
        initToolBar();
        addItemPresenter = new AddItemPresenterImpl(this);
    }

    //設置ToolBar
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("新增活動");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊

        Log.d("MainActivity", "init ToolBar");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_content_actbtn, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onClick(View v) {
        Log.d("click", "img");
        switch (v.getId()) {
            case R.id.img_date:
                addItemPresenter.showDatePickerDialog();
        }

    }

    // 點擊右上角的Action button 的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                addItemPresenter.addNewActivity(edtTitle.getText().toString(), txtDate.getText().toString(), edtContent.getText().toString());
        }

        return super.onOptionsItemSelected(item);
    }


    // 顯示DatePickerDialog Fragment
    @Override
    public void showDatePickerDialog() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "datePicker");

    }

    // 利用Callback接收按下確定後的日期資料
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        addItemPresenter.showTextDate(year, month, dayOfMonth);
    }

    // 顯示選擇的日期
    @Override
    public void setTextDate(String[] d) {
        txtDate.setText(d[0] + "/" + d[1] + "/" + d[2]);
    }

}
