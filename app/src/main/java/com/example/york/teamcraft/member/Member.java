package com.example.york.teamcraft.member;

/**
 * Created by York on 2017/10/7.
 */

// 儲存創建群組畫面中spinner項目的資料
public class Member {
    private String name;    // 姓名
    private String userId;    // user id
    private String position; // 職位

    public Member(String name, String id, String pos) {
        this.name = name;
        this.userId = id;
        this.position = pos;
    }

    public Member() {}

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
}
