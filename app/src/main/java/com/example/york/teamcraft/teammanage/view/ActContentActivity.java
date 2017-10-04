package com.example.york.teamcraft.teammanage.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.Work;

public class ActContentActivity extends AppCompatActivity {
    private TextView txtTopic;
    private TextView txtDate;
    private TextView txtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teammanage_activity_act_content);

        // find view
        txtTopic = (TextView) findViewById(R.id.txt_topic);
        txtDate = (TextView) findViewById(R.id.txt_date);
        txtContent = (TextView) findViewById(R.id.txt_content);

        // get bundle
        Bundle bundle = getIntent().getExtras();
        Work work = bundle.getParcelable("Work");

        txtTopic.setText(work.getTopic());
        txtDate.setText(work.getDate());
        txtContent.setText(work.getContent());
    }
}
