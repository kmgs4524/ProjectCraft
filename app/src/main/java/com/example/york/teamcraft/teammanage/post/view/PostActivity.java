package com.example.york.teamcraft.teammanage.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.teammanage.post.model.Comment;
import com.example.york.teamcraft.teammanage.post.presenter.PostPresenter;
import com.example.york.teamcraft.teammanage.post.presenter.PostPresenterImpl;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity implements PostView{
    // view
    private TextView txtTopic;
    private TextView txtDate;
    private TextView txtContent;
    private RecyclerView recyclerComment;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // presenter
    private PostPresenter postPresenter;
    // test data
    private ArrayList<Comment> commList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_post_content);
        // find view
        initTxtView();
        // get bundle
        Bundle bundle = getIntent().getExtras();
        Post post = bundle.getParcelable("Post");
        Log.d("postid", post.getPostId());
        // init presenter
        postPresenter = new PostPresenterImpl(this);
        postPresenter.setRecyclerData(post.getPostId());    // 設定RecyclerView的資料來源

        txtTopic.setText(post.getTopic());
        txtDate.setText(post.getDate());
        txtContent.setText(post.getContent());
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
