package com.example.york.teamcraft.data;

/**
 * Created by York on 2017/10/7.
 */

// 儲存創建群組畫面中spinner項目的資料
public class GroupMember {
    private String name;    // 姓名
    private String userId;    // user id
    private String position; // 職位
    private String photoUrl;    // 大頭貼

    public GroupMember(String name, String id, String pos, String url) {
        this.name = name;
        this.userId = id;
        this.position = pos;
        this.photoUrl = url;
    }

    public GroupMember() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
