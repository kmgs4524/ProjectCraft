package com.example.york.teamcraft.teammanage.post.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.york.teamcraft.R;
import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.teammanage.post.model.Comment;
import com.example.york.teamcraft.teammanage.post.presenter.PostPresenter;
import com.example.york.teamcraft.teammanage.post.presenter.PostPresenterImpl;

import java.util.ArrayList;

public class PostActivity extends AppCompatActivity implements PostView{
    // view
    private TextView txtDate;
    private TextView txtContent;
    private EditText edtMessg;
    private ImageView imgSend;
    private RecyclerView recyclerComment;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    // presenter
    private PostPresenter postPresenter;
    // received postId
    private String postId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_activity_post_content);
        // find view
        initTxtView();
        // get bundle
        Bundle bundle = getIntent().getExtras();
        Post post = bundle.getParcelable("Post");
        postId = post.getPostId();  // 取得post id
        // init presenter
        postPresenter = new PostPresenterImpl(this);
        postPresenter.setRecyclerData(post.getPostId());    // 設定RecyclerView的資料來源
        postPresenter.setSendMessg();

        txtContent.setText(post.getContent());
        txtDate.setText(post.getDate());
    }

    public void initTxtView() {
        txtContent = (TextView) findViewById(R.id.txt_post_content);
        txtDate = (TextView) findViewById(R.id.txt_post_date);
    }

    public void initRecyclerView(ArrayList<Comment> commList) {
        layoutManager = new LinearLayoutManager(this);
        recyclerComment = (RecyclerView) findViewById(R.id.recycler_view_post_comment);
        adapter = new CommentItemAdapter(commList, this);
        recyclerComment.setLayoutManager(layoutManager);
        recyclerComment.setAdapter(adapter);
    }

    public void initMessg() {
        edtMessg = (EditText) findViewById(R.id.edt_message_text);
        imgSend = (ImageView) findViewById(R.id.img_send_comment);
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPresenter.postComment(postId, edtMessg.getText().toString());
            }
        });
    }

}
