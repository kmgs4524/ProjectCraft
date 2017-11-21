package com.example.york.teamcraft.teammanage.addpost.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.addpost.view.AddPostView;
import com.example.york.teamcraft.teammanage.model.Post;
import com.example.york.teamcraft.teammanage.model.ReadUser;
import com.example.york.teamcraft.teammanage.model.User;
import com.example.york.teamcraft.teammanage.model.WritePost;
import com.example.york.teamcraft.teammanage.post.model.WritePostComments;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class AddPostPresenterImpl implements AddPostPresenter {
    private static String TAG = "AddPostPresenterImpl";

    private AddPostView addPostView;    // 新增項目的Activity
    // model
    private ReadUser readUser;
    private WritePost writePost;    // Database Model
    private WritePostComments writePostComments;

    private String[] date = new String[3];

    public AddPostPresenterImpl(AddPostView v) {
        addPostView = v;
        readUser = new ReadUser();
        writePost = new WritePost();
        writePostComments = new WritePostComments();
    }

    @Override
    public void showDatePickerDialog() {
        addPostView.showDatePickerDialog();
    }

    @Override
    public void showTextDate(int year, int month, int day) {
        date[0] = Integer.toString(year);
        date[1] = Integer.toString(month);
        date[2] = Integer.toString(day);

        addPostView.setTextDate(date);
    }

    @Override
    public void addNewPost(final String date, final String time, final String content) {
//        final Map<String, String> postMap = new HashMap<>();  // 存放Activity的HashMap
//        postMap.put("date", date);
//        postMap.put("time", time);
//        postMap.put("content", content);
//        postMap.put("")
        readUser.getUserId(new CallBack<String>() {
            @Override
            public void update(String userId) {
                readUser.getUserImageUrl(userId, new CallBack<String>() {
                    @Override
                    public void update(final String url) {
                        readUser.getUserData(new CallBack<User>() {
                            @Override
                            public void update(User user) {
                                Post post = new Post(user.getName(), date, content, "", 0, 0, time, url);
                                writePost.pushData(post, new CallBack<String>() {
                                    @Override
                                    public void update(String data) {
                                        // 將得到的postId寫入postComments
                                        writePostComments.pushNewMessg(data, null);
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}
