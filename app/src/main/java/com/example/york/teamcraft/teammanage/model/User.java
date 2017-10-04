package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/9/22.
 */

public class User {
    private String email;
    private String name;
    private String teamId;

    public User(String n, String e, String tId) {
        name = n;
        email = e;
        teamId = tId;
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

    public String getTeamId() { return teamId; }
}
