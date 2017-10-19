package com.example.york.teamcraft.teammanage.post.presenter;

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

    public void postComment(String postId, String messg) {
        postComment.post(postId, messg);
    }

    public void setRecyclerData(String postId) {
        setCommentData.setData(postView, postId);
    }

    public void setSendMessg() {
        postView.initMessg();
    }
}
