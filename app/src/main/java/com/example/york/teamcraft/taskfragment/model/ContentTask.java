package com.example.york.teamcraft.taskfragment.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/10/11.
 */

public class ContentTask implements Parcelable{
    private String topic;
    private String content;
    private String responsible;
    private String date;
    private String time;
    private int status;

    public ContentTask(String topic, String content, String responsible, String date, String time, int status) {
        this.topic = topic;
        this.content = content;
        this.date = date;
        this.time = time;
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

    // responsible
    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible;
    }

    // status
    public int getStatus() {
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
