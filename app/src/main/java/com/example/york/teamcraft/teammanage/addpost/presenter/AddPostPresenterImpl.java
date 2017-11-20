package com.example.york.teamcraft.teammanage.addpost.presenter;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.addpost.view.AddPostView;
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
    private WritePost writePost;    // Database Model
    private WritePostComments writePostComments;

    private String[] date = new String[3];

    public AddPostPresenterImpl(AddPostView v) {
        addPostView = v;
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
    public void addNewPost(String date, String time, String content) {
        Map<String, String> postMap = new HashMap<>();  // 存放Activity的HashMap
        postMap.put("date", date);
        postMap.put("time", time);
        postMap.put("content", content);
        writePost.pushData(postMap, new CallBack<String>() {
            @Override
            public void update(String data) {
                writePostComments.pushNewMessg(data, null);
            }
        });    // 將Activity的內容寫入資料庫

    }
}
