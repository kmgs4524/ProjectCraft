package com.example.york.teamcraft.teammanage.presenter;

import android.util.Log;

import com.example.york.teamcraft.teammanage.model.WriteActivity;
import com.example.york.teamcraft.teammanage.view.AddItemView;
import com.google.firebase.database.DataSnapshot;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by York on 2017/9/30.
 */

public class AddItemPresenterImpl implements AddItemPresenter{
    private static String TAG = "AddItemPresenterImpl";

    private AddItemView addItemView;    // Activity
    private String[] date = new String[3];
    private WriteActivity writeActivity;

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
    public void addNewActivity(String title, String date, String content) {
        Log.d(TAG, "data: " + title + " " + date + " " + content);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", title);
        dataMap.put("date", date);
        dataMap.put("content", content);


    }


}
