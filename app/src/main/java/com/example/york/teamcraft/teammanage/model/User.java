package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/9/22.
 */

public class User {
    private String email;
    private String name;
    private String imageUrl;
    private String teamId;
    private String groupId;

    public User(String n, String e, String url, String tId, String gId) {
        this.name = n;
        this.email = e;
        this.imageUrl = url;
        this.teamId = tId;
        this.groupId = gId;
    }

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() { return imageUrl; }

    public String getTeamId() { return teamId; }

    public String getGroupId() { return groupId; }
}
