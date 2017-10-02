package com.example.york.teamcraft;

/**
 * Created by York on 2017/9/28.
 */

public class Work {
    private String topic;
    private String date;
    private String content;

    public Work(String topic, String date, String content) {
        this.topic = topic;
        this.date = date;
        this.content = content;
    }

    public Work() {}

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }
}
