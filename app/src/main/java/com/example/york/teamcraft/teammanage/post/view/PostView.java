package com.example.york.teamcraft.teammanage.post.view;

import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.teammanage.post.model.Comment;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/20.
 */

public interface PostView {
    public abstract void initRecyclerView(ArrayList<Comment> commList);
    public void initMessg();
    public void setPostData(Post post);
}
