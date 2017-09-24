package com.example.york.teamcraft.teammanage;

/**
 * Created by York on 2017/9/11.
 */

public class TeamActivities {
    private String title;
    private String date;

    public TeamActivities(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
