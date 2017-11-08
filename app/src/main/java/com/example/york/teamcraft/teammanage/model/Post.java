package com.example.york.teamcraft.teammanage.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/9/28.
 */

public class Post implements Parcelable{
    private String posterName;
    private String topic;
    private String date;
    private String time;
    private String content;
    private String postId;
    private String imageUrl;
    private int favorite;

    public Post(String posterName, String topic, String date, String content, String postId, int favorite, String time, String imageUrl) {
        this.posterName = posterName;
        this.topic = topic;
        this.date = date;
        this.content = content;
        this.postId = postId;
        this.favorite = favorite;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public Post() {}

    protected Post(Parcel in) {
        topic = in.readString();
        date = in.readString();
        content = in.readString();
        postId = in.readString();
    }

    public String getPosterName() {
        return posterName;
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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTime() {
        return time;
    }

    public int getFavorite() {
        return favorite;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(posterName);
        dest.writeString(topic);
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(content);
        dest.writeString(postId);
        dest.writeString(imageUrl);
        dest.writeInt(favorite);
    }
}
