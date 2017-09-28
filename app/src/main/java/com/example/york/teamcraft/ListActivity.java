package com.example.york.teamcraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.york.teamcraft.databasemodel.ReadUser;

public class ListActivity extends AppCompatActivity {
    private String TAG = "ListActivity";
    private Button btnOK;
    private Button btnRead;
    private EditText edtEmail;
    private TextView txtName;

    private WriteUser writeUser;
    private ReadUser readUser;

//    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        writeUser = new WriteUser();
        readUser = new ReadUser("");

        btnOK = (Button) findViewById(R.id.btn_ok);
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeUser.pushUser();
                Log.d(TAG, "onClick()");
            }
        });

        edtEmail = (EditText) findViewById(R.id.edt_email);

        txtName = (TextView) findViewById(R.id.txt_name);
        btnRead = (Button) findViewById(R.id.btn_read);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().equals("")) {
                    Toast.makeText(ListActivity.this, "請輸入要查詢的信箱", Toast.LENGTH_SHORT).show();
                } else {
                    String email = edtEmail.getText().toString();

//                    readUser.getUserId(email, new CallBack<User>() {
//                        @Override
//                        public void update(User user, String key) {
//                            txtName.setText(user.getName());
//                        }
//                    });

                }

            }
        });
    }


}
