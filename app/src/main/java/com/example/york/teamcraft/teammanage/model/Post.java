package com.example.york.teamcraft.teammanage.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by York on 2017/9/28.
 */

public class Post implements Parcelable{
    private String posterName;  // 貼文者姓名
    private String date;    // 貼文日期
    private String time;    // 貼文時間
    private String content; // 內容
    private String postId;  // 貼文id
    private String imageUrl;    // 貼文者的大頭照url
    private int favoriteNum;   // 按喜歡的數量
    private int commentNum;    // 留言的數量

    public Post(String posterName, String date, String content, String postId, int favoriteNum, int commentNum, String time, String imageUrl) {
        this.posterName = posterName;
        this.date = date;
        this.content = content;
        this.postId = postId;
        this.favoriteNum = favoriteNum;
        this.commentNum = commentNum;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public Post() {}

    protected Post(Parcel in) {
        posterName = in.readString();
        date = in.readString();
        time = in.readString();
        content = in.readString();
        postId = in.readString();
        imageUrl = in.readString();
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getFavoriteNum() {
        return favoriteNum;
    }

    public void setFavoriteNum(int favoriteNum) {
        this.favoriteNum = favoriteNum;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
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
        dest.writeString(date);
        dest.writeString(time);
        dest.writeString(content);
        dest.writeString(postId);
        dest.writeString(imageUrl);
        dest.writeInt(favoriteNum);
        dest.writeInt(commentNum);
    }
}
