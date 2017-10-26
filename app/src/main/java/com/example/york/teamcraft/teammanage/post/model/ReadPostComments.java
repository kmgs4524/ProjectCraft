package com.example.york.teamcraft.teammanage.post.model;

import com.example.york.teamcraft.CallBack;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by York on 2017/10/20.
 */

public class ReadPostComments {
    private DatabaseReference postCommRef;

    public ReadPostComments() {
        postCommRef = FirebaseDatabase.getInstance().getReference().child("postComments");
    }

    public void getComment(String postId, final CallBack<ArrayList<Comment>> callBack) {
        final ArrayList<Comment> commList = new ArrayList<>();

        postCommRef.child(postId).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Comment comment;
                comment = dataSnapshot.getValue(Comment.class);
                commList.add(comment);
                callBack.update(commList);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Comment comment;
                comment = dataSnapshot.getValue(Comment.class);
                commList.add(comment);
                callBack.update(commList);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
