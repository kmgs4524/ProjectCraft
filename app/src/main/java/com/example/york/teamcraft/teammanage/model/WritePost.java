package com.example.york.teamcraft.teammanage.model;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class WritePost {
    private static String TAG = "WritePost";

    private DatabaseReference teamActRef;
    private ReadUser readUser;

    public WritePost() {
        teamActRef= FirebaseDatabase.getInstance().getReference().child("teamPosts");
        readUser = new ReadUser();
    }

    public void pushData(final Post post, final CallBack<String> callBack) {
        final HashMap<String, Object> postMap = new HashMap<>();
        readUser.getUserData(new CallBack<User>() {
            @Override
            public void update(User data) {
                DatabaseReference childRef = teamActRef.child(data.getTeamId()).getRef();   // 先取得user的teamId，再用此teamId到teamActivities建立新的teamId child
                String postId = childRef.push().getKey();
                post.setPostId(postId);
                postMap.put(postId, post);
                childRef.updateChildren(postMap);    // 將從AddItemPresenterImpl.addNewActivity傳來Map寫入teamId node的child
                callBack.update(postId);
            }
        });
    }

}
