package com.example.york.teamcraft.teammanage.addpost.presenter;

import android.util.Log;

import com.example.york.teamcraft.CallBack;
import com.example.york.teamcraft.teammanage.addpost.view.AddPostView;
import com.example.york.teamcraft.teammanage.model.WriteActivity;
import com.example.york.teamcraft.teammanage.post.model.WritePostComments;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class AddItemPresenterImpl implements AddItemPresenter{
    private static String TAG = "AddItemPresenterImpl";

    private AddPostView addPostView;    // 新增項目的Activity
    private WriteActivity writeActivity;    // Database Model
    private WritePostComments writePostComments;

    private String[] date = new String[3];

    public AddItemPresenterImpl(AddPostView v) {
        addPostView = v;
        writeActivity = new WriteActivity();
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
    public void addNewActivity(String topic, String date, String content) {
        Map<String, String> dataMap = new HashMap<>();  // 存放Activity的HashMap
        dataMap.put("topic", topic);
        dataMap.put("date", date);
        dataMap.put("content", content);
        writeActivity.pushData(dataMap, new CallBack<String>() {
            @Override
            public void update(String data) {
                writePostComments.pushNewMessg(data, null);
            }
        });    // 將Activity的內容寫入資料庫

    }


}
