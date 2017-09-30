package com.example.york.teamcraft.teammanage.presenter;

import com.example.york.teamcraft.teammanage.view.AddItemView;

/**
 * Created by York on 2017/9/30.
 */

public class AddItemPresenterImpl implements AddItemPresenter{
    private AddItemView addItemView;
    private String[] date = new String[3];

    public AddItemPresenterImpl(AddItemView v) {
        addItemView = v;
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

    }


}
