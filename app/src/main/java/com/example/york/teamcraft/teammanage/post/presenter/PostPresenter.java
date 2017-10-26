package com.example.york.teamcraft.teammanage.post.presenter;

/**
 * Created by York on 2017/10/20.
 */

public interface PostPresenter {
    public abstract void setRecyclerData(String postId);
    public abstract void setSendMessg();
    public abstract void postComment(String postId,String messg);
}
