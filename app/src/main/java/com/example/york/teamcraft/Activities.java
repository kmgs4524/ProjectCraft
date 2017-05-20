package com.example.york.teamcraft;

/**
 * Created by York on 2017/4/28.
 */

public class Activities {
    private String content;
    private String schedule;
    private String time;

    public Activities() {
        //Needed for Firebase
    }

    public Activities(String content, String schedule, String time) {
        this.content = content;
        this.schedule = schedule;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
