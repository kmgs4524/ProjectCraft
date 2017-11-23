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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class PostActivity extends AppCompatActivity implements PostView{
    // view
    @BindView(R.id.img_poster)
    CircleImageView imgPoster;
    @BindView(R.id.txt_poster_name)
    TextView txtPosterName;
    @BindView(R.id.txt_post_date)
    TextView txtPostDate;
    @BindView(R.id.txt_post_content)
    TextView txtPostContent;
    @BindView(R.id.edt_message_text)
    EditText edtMessg;
    @BindView(R.id.img_send_comment)
    ImageView imgSend;
    @BindView(R.id.recycler_view_post_comment)
    RecyclerView recyclerComment;
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
        ButterKnife.bind(this);
        // get bundle
        Bundle bundle = getIntent().getExtras();
        Post post = bundle.getParcelable("Post");
        postId = post.getPostId();  // 取得post id
        // init presenter
        postPresenter = new PostPresenterImpl(this);
        postPresenter.setPostData(post);    // 設定貼文內容
        postPresenter.setRecyclerData(post.getPostId());    // 設定RecyclerView的資料來源
        postPresenter.setSendMessg();
    }

    public void initRecyclerView(ArrayList<Comment> commList) {
        layoutManager = new LinearLayoutManager(this);
        adapter = new CommentItemAdapter(commList, this);
        recyclerComment.setLayoutManager(layoutManager);
        recyclerComment.setAdapter(adapter);
    }

    public void initMessg() {
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postPresenter.postComment(postId, edtMessg.getText().toString());
            }
        });
    }

    @Override
    public void setPostData(Post post) {
        // 設定大頭照圖片
        Picasso.with(this)
                .load(post.getImageUrl())
                .into(imgPoster);
        txtPosterName.setText(post.getPosterName());
        txtPostDate.setText(post.getDate());
        txtPostContent.setText(post.getContent());
    }
}
