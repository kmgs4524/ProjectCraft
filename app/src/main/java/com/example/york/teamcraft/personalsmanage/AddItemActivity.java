package com.example.york.teamcraft.personalsmanage;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.york.teamcraft.R;

public class AddItemActivity extends AppCompatActivity {
    private static String TAG = "AddItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personals_activity_add_item);
        initToolBar();
        initEdtBtn();


    }

    // 設置ToolBar
    public void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("新增事項");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Log.d("MainActivity", "init ToolBar");
    }

    //按左上角的返回，將回到上頁
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void initEdtBtn() {
        final EditText edtTitle = (EditText) findViewById(R.id.edt_item_title);
        final EditText edtContent = (EditText) findViewById(R.id.edt_item_content);
        final EditText edtDate = (EditText) findViewById(R.id.edt_item_date);
        final EditText edtTime = (EditText) findViewById(R.id.edt_item_time);
        Button btnCreate = (Button) findViewById(R.id.btn_create);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("edtTitle",  edtTitle.getText().toString());
                Log.d("edtContent", edtContent.getText().toString());
                Log.d("edtDate", edtDate.getText().toString());
                Log.d("edtTime", edtTime.getText().toString());
            }
        });
    }

    // 點擊重要性選項按鈕回傳對應的值
    public int onRadioButtonClicked(View view) {
        // 是否勾選radio button
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_urgent: //急迫
                if (checked)
                    Log.d("radio_urgent", view.toString());
                    return 1;
            case R.id.radio_normal: //適中
                if (checked)
                    Log.d("radio_normal", view.toString());
                    return 2;
            case R.id.radio_ample:  //充裕
                if (checked)
                    Log.d("radio_ample", view.toString());
                    return 3;
            default:
                return 0;
        }
    }
}
