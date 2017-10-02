package com.example.york.teamcraft.teammanage.model;

/**
 * Created by York on 2017/9/24.
 */

public class Team {
    private String name;

    public Team(String n) {
        name = n;
    }

    public Team() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
