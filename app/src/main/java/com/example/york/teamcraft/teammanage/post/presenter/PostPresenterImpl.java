package com.example.york.teamcraft.teammanage.post.presenter;

import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.teammanage.post.model.WritePostComments;
import com.example.york.teamcraft.teammanage.post.view.PostView;
import com.example.york.teamcraft.teammanage.post.viewmodel.PostComment;
import com.example.york.teamcraft.teammanage.post.viewmodel.SetCommentData;

/**
 * Created by York on 2017/10/20.
 */

public class PostPresenterImpl implements PostPresenter{
    private PostView postView;
    private SetCommentData setCommentData;
    private PostComment postComment;

    public PostPresenterImpl(PostView view) {
        this.postView = view;
        this.setCommentData = new SetCommentData();
        this.postComment = new PostComment();
    }
    @Override
    public void setPostData(Post post) {
        postView.setPostData(post);
    }
    // 將留言寫入firebase的postComment node
    public void postComment(String postId, String messg) {
        postComment.post(postId, messg);
    }
    // 設定recycler view的資料來源
    public void setRecyclerData(String postId) {
        setCommentData.setData(postView, postId);
    }
    // 設定imgSend的listener
    public void setSendMessg() {
        postView.initMessg();
    }
}
