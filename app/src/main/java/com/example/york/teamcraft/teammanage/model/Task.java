package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/10/10.
 */

public class Task {
    private String topic;
    private String responsible;
    private String content;

    public Task(String topic, String responsible, String content) {
        this.topic = topic;
        this.responsible = responsible;
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
