package com.example.york.teamcraft.teammanage.additem.presenter;

import android.util.Log;

import com.example.york.teamcraft.teammanage.additem.view.AddItemView;
import com.example.york.teamcraft.teammanage.model.WriteActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class AddItemPresenterImpl implements AddItemPresenter{
    private static String TAG = "AddItemPresenterImpl";

    private AddItemView addItemView;    // 新增項目的Activity
    private WriteActivity writeActivity;    // Database Model

    private String[] date = new String[3];

    public AddItemPresenterImpl(AddItemView v) {
        addItemView = v;
        writeActivity = new WriteActivity();
    }

    @Override
    public void showDatePickerDialog() {
        addItemView.showDatePickerDialog();
    }

    @Override
    public void showTextDate(int year, int month, int day) {
        date[0] = Integer.toString(year);
        date[1] = Integer.toString(month);
        date[2] = Integer.toString(day);

        addItemView.setTextDate(date);
    }

    @Override
    public void addNewActivity(String topic, String date, String content) {
        Log.d(TAG, "data: " + topic + " " + date + " " + content);
        Map<String, String> dataMap = new HashMap<>();  // 存放Activity的HashMap
        dataMap.put("topic", topic);
        dataMap.put("date", date);
        dataMap.put("content", content);
        writeActivity.pushData(dataMap);    // 將Activity的內容寫入資料庫

    }


}
