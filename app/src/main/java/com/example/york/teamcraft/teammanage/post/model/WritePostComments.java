package com.example.york.teamcraft.teammanage.post.model;

import com.example.york.teamcraft.teammanage.model.Post;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/10/20.
 */

public class WritePostComments {
    private DatabaseReference postCommRef;

    public WritePostComments() {
        this.postCommRef = FirebaseDatabase.getInstance().getReference().child("postComments");
    }

    public void pushNewMessg(String postId, Comment comm) {
        String key = postCommRef.child(postId).push().getKey();
        comm.setCommentId(key);
        Map<String, Object> map = new HashMap<>();
        map.put(key, comm);
        postCommRef.child(postId).updateChildren(map);
    }
}
