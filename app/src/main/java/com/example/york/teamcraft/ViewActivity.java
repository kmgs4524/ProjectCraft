package com.example.york.teamcraft;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewActivity extends AppCompatActivity {
    private ListView activitiesView;
    private DatabaseReference reference;
    private FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        activitiesView = (ListView) findViewById(R.id.listView);

        reference = FirebaseDatabase.getInstance().getReference("activities");  //取得Firebase Database的Reference

        adapter = new FirebaseListAdapter<Activities>(this, Activities.class, android.R.layout.two_line_list_item, reference) {
            @Override
            protected void populateView(View view, Activities activities, int position) {
                ((TextView)view.findViewById(android.R.id.text1)).setText(activities.getTime());
                ((TextView)view.findViewById(android.R.id.text2)).setText(activities.getSchedule());

            }
        };
        activitiesView.setAdapter(adapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cleanup();
    }
}

    //ActivityViewHolder包含兩個TextView
//    private static class ActivitiesHolder extends RecyclerView.ViewHolder{
//        private final TextView mTextTime;
//        private final TextView mTextSchedule;
//
//        public ActivitiesHolder(View itemView){
//            super(itemView);
//
//            mTextTime = (TextView) itemView.findViewById(android.R.id.text1);
//            mTextSchedule = (TextView) itemView.findViewById(android.R.id.text2);
//        }
//
//        public void setTime(String time){
//            mTextTime.setText(time);
//        }
//
//        public void setSchedule(String schedule){
//            mTextSchedule.setText(schedule);
//        }
//
//    }

