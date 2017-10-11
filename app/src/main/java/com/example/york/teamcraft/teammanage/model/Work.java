package com.example.york.teamcraft.teammanage.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/9/28.
 */

public class Work implements Parcelable{
    private String topic;
    private String date;
    private String content;

    public Work(String topic, String date, String content) {
        this.topic = topic;
        this.date = date;
        this.content = content;
    }

    public Work() {}

    protected Work(Parcel in) {
        topic = in.readString();
        date = in.readString();
        content = in.readString();
    }

    public String getTopic() {
        return topic;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public static final Creator<Work> CREATOR = new Creator<Work>() {
        @Override
        public Work createFromParcel(Parcel in) {
            return new Work(in);
        }

        @Override
        public Work[] newArray(int size) {
            return new Work[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(topic);
        dest.writeString(date);
        dest.writeString(content);
    }
}
