package com.example.york.teamcraft.teammanage.jointeam.model;

/**
 * Created by York on 2017/10/23.
 */

public class TeamMember {
    private String name;
    private String userId;
    private String photoUrl;

    public TeamMember(String name) {
        this.name = name;
    }

    public TeamMember() {}

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

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
