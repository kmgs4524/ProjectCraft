package com.example.york.teamcraft.teammanage.model;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by York on 2017/9/22.
 */

public class User {
    private String email;
    private String name;
    private String imageUrl;
    private String teamId;
    private ArrayList<String> groupId;
    private String status;

    public User(String n, String e, String url, String tId, ArrayList<String> gId, String status) {
        this.name = n;
        this.email = e;
        this.imageUrl = url;
        this.teamId = tId;
        this.groupId = gId;
        this.status = status;
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

    public ArrayList<String> getGroupIds() { return groupId; }

    public String getStatus() { return status; }

    public void setGroupId(ArrayList<String> groupId) {
        this.groupId = groupId;
    }
}
