package com.example.york.teamcraft.teammanage.addpost.view;

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
import android.widget.Toast;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.addpost.presenter.AddPostPresenter;
import com.example.york.teamcraft.teammanage.addpost.presenter.AddPostPresenterImpl;
import com.example.york.teamcraft.datepickerfragment.DatePickerFragment;

import java.util.Calendar;

public class AddPostActivity extends AppCompatActivity implements AddPostView, DatePickerDialog.OnDateSetListener{
    // view
    private EditText edtContent;
    // presenter
    private AddPostPresenter addPostPresenter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_add_item);
        // findView
        edtContent = (EditText) findViewById(R.id.edt_content);
        // init
        initToolBar();
        addPostPresenter = new AddPostPresenterImpl(this);
    }

    //設置ToolBar
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("新增貼文");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_content_actbtn, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // 點擊右上角的Action button 的事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                // 傳送目前的日期,時間以及要張貼的內容
                addPostPresenter.addNewPost(Calendar.getInstance().get(Calendar.YEAR) + // 年
                        "/" + (Calendar.getInstance().get(Calendar.MONTH) + 1) +  // 月
                        "/" + Calendar.getInstance().get(Calendar.DATE),    // 日
                        Calendar.getInstance().get(Calendar.HOUR) + // 時
                        ":" + Calendar.getInstance().get(Calendar.MINUTE),  // 分
                        edtContent.getText().toString());
                Toast.makeText(this, "張貼成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
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
        addPostPresenter.showTextDate(year, month, dayOfMonth);
    }

    // 顯示選擇的日期
    @Override
    public void setTextDate(String[] d) {
//        txtDate.setText(d[0] + "/" + d[1] + "/" + d[2]);
    }

}
