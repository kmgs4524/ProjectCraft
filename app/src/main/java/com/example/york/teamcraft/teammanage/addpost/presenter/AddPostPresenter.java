package com.example.york.teamcraft.teammanage.addpost.presenter;

/**
 * Created by York on 2017/9/30.
 */

public interface AddPostPresenter {
    public abstract void showDatePickerDialog();
    public abstract void showTextDate(int year, int month, int day);
    public abstract void addNewPost(String date, String time, String content);
}
