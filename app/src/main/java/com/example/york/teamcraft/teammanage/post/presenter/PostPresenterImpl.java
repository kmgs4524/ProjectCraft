package com.example.york.teamcraft.teammanage.post.presenter;

import com.example.york.teamcraft.teammanage.post.view.PostView;
import com.example.york.teamcraft.teammanage.post.viewmodel.SetCommentData;

/**
 * Created by York on 2017/10/20.
 */

public class PostPresenterImpl implements PostPresenter{
    private PostView postView;
    private SetCommentData setCommentData;

    public PostPresenterImpl(PostView view) {
        this.postView = view;
        this.setCommentData = new SetCommentData();
    }

    public void setRecyclerData(String postId) {
        setCommentData.setData(postView, postId);
    }
}
