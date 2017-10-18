package com.example.york.teamcraft.addcontenttask.data;

/**
 * Created by York on 2017/10/17.
 */

public class Date {
    private int year;
    private int month;
    private int dayOfMonth;

    public Date(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.dayOfMonth = d;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }
}
