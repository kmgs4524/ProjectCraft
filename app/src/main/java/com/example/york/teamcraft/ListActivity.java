package com.example.york.teamcraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ListActivity extends AppCompatActivity {
    private String TAG = "ListActivity";
    private Button btnOK;
    private Button btnRead;
    private EditText edtEmail;

    WriteData writeUser;
    ReadUser readUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        writeUser = new WriteData();
        readUser = new ReadUser();

        btnOK = (Button) findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeUser.pushUser();
                Log.d(TAG, "onClick()");
            }
        });

        edtEmail = (EditText) findViewById(R.id.edt_email);

        btnRead = (Button) findViewById(R.id.btn_read);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser.readUserData();
            }
        });
    }


}
