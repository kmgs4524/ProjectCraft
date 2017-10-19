package com.example.york.teamcraft.teammanage.post.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.post.model.Comment;
import com.example.york.teamcraft.teammanage.post.model.ReadPostComments;
import com.example.york.teamcraft.teammanage.post.view.PostView;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/20.
 */

public class SetCommentData {
    private ReadPostComments readPostComments;

    public SetCommentData() {
        this.readPostComments = new ReadPostComments();
    }

    public void setData(final PostView view, String postId) {
        readPostComments.getComment(postId, new CallBack<ArrayList<Comment>>() {
            @Override
            public void update(ArrayList<Comment> data) {
                view.initRecyclerView(data);
            }
        });
    }
}
