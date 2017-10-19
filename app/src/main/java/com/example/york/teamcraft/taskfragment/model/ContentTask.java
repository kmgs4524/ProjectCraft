package com.example.york.teamcraft.taskfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/10/11.
 */

public class ContentTask implements Parcelable{
    private String taskId;
    private String topic;
    private String content;
    private String responId;
    private String responsible;
    private String date;
    private String time;
    private boolean status;

    public ContentTask(String taskId,String topic, String content, String responId, String responsible, String date, String time, boolean status) {
        this.taskId = taskId;
        this.topic = topic;
        this.content = content;
        this.date = date;
        this.time = time;
        this.responId = responId;
        this.responsible = responsible;
        this.status = status;
    }

    public ContentTask() { }

    protected ContentTask(Parcel in) {
        topic = in.readString();
        content = in.readString();
        responsible = in.readString();
    }

    public static final Creator<ContentTask> CREATOR = new Creator<ContentTask>() {
        @Override
        public ContentTask createFromParcel(Parcel in) {
            return new ContentTask(in);
        }

        @Override
        public ContentTask[] newArray(int size) {
            return new ContentTask[size];
        }
    };

    // taskId
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    // topic
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    // content
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    //responId
    public String getResponId() { return responId;}

    // responsible
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    // status
    public boolean getStatus() {
        return status;
    }

    // date
    public String getDate() {
        return date;
    }

    // time
    public String getTime() {
        return time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topic);
        dest.writeString(content);
        dest.writeString(responsible);
    }
}
