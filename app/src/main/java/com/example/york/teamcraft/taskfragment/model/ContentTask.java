package com.example.york.teamcraft.taskfragment.model;

/**
 * Created by York on 2017/10/11.
 */

public class ContentTask {
    private String topic;
    private String content;
    private String responsible;

    public ContentTask(String topic, String content, String responsible) {
        this.topic = topic;
        this.content = content;
        this.responsible = responsible;
    }

    public ContentTask() { }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }
}
