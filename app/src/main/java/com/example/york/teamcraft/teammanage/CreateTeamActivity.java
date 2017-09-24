package com.example.york.teamcraft.teammanage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.R;
import com.example.york.teamcraft.ReadUser;
import com.example.york.teamcraft.User;
import com.example.york.teamcraft.WriteTeam;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateTeamActivity extends AppCompatActivity {
    private static String TAG = "CreateTeamActivity";

    // UI 元件
    private Button btnCreate;

    // Firebase
    private FirebaseAuth auth;  // FirebaseAuth Instance
    private FirebaseUser user;  // 目前登入的使用者

    // Database Model
    private ReadUser readUser;
    private WriteTeam writeTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_create_team);

        // init firebae auth
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        // init ReadUserModel
        readUser = new ReadUser();
        writeTeam = new WriteTeam();

        // init UI View
        initToolBar();
        btnCreate = (Button) findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "BTN click");
//                Log.d(TAG, user.getEmail());
                readUser.readUserData(user.getEmail(), new CallBack() {
                    @Override
                    public void update(User user) {
                        Log.d(TAG, user.getName());
                    }
                });
                writeTeam.pushData();
            }
        });
    }



    //設置ToolBar
    private void initToolBar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("團隊管理");
//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close); // 給定的Activity會連接給定的DrawerLayout, 使用此constructor會設置listener點擊icon便切換drawer
//        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 啟用ActionBar的home button的回到上一層功能並加上回上層的圖標
        getSupportActionBar().setHomeButtonEnabled(true);   // 啟用home button，決定home button是否能點擊
//        drawerToggle.syncState();   // 同步drawer指標狀態到連接的DrawerLayout

        Log.d("MainActivity", "init ToolBar");
    }


}
