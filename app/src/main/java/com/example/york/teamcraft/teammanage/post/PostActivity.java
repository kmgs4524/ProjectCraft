package com.example.york.teamcraft.teammanage.post;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.Work;
import com.example.york.teamcraft.teammanage.post.model.Comment;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity {
    private TextView txtTopic;
    private TextView txtDate;
    private TextView txtContent;
    private RecyclerView recyclerComment;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private ArrayList<Comment> commList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_post_content);

        // find view
        initTxtView();

        commList = new ArrayList<>();
        commList.add(new Comment("李侑乘", "245", "是否要今天開會呢", "321"));
        commList.add(new Comment("小球球", "452", "我ok", "625"));

        initRecyclerView(commList);

        // get bundle
        Bundle bundle = getIntent().getExtras();
        Work work = bundle.getParcelable("Work");

        txtTopic.setText(work.getTopic());
        txtDate.setText(work.getDate());
        txtContent.setText(work.getContent());
    }

    public void initTxtView() {
        txtTopic = (TextView) findViewById(R.id.txt_topic);
        txtDate = (TextView) findViewById(R.id.txt_date);
        txtContent = (TextView) findViewById(R.id.txt_content);
    }

    public void initRecyclerView(ArrayList<Comment> commList) {
        layoutManager = new LinearLayoutManager(this);
        recyclerComment = (RecyclerView) findViewById(R.id.recycler_view_post_comment);
        adapter = new CommentItemAdapter(commList);
        recyclerComment.setLayoutManager(layoutManager);
        recyclerComment.setAdapter(adapter);
    }

}
