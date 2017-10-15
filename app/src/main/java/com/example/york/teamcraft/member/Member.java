package com.example.york.teamcraft.member;

/**
 * Created by York on 2017/10/7.
 */

// 儲存創建群組畫面中spinner項目的資料
public class Member {
    private String name;    // 姓名
    private String userId;    // 職位

    public Member(String name, String id) {
        this.name = name;
        this.userId = id;
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
}
