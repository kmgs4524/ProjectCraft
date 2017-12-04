package com.example.york.teamcraft.teammanage.post.viewmodel;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.post.model.Comment;
import com.example.york.teamcraft.teammanage.post.model.WritePostComments;

/**
 * Created by York on 2017/10/20.
 */

public class PostComment {
    // model
    private WritePostComments writePostComments;
    private ReadUser readUser;

    public PostComment() {
        this.readUser = new ReadUser();
        this.writePostComments = new WritePostComments();
    }

    public void post(final String postId, final String messg) {
        readUser.getCurrentLogInUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                final User user = data;
                readUser.getUserId(new CallBack<String>() {
                    @Override
                    public void update(String data) {
                        Comment comment = new Comment(user.getName(), data, messg, null);
                        writePostComments.pushNewMessg(postId, comment);
                    }
                });
            }
        });
    }
}
