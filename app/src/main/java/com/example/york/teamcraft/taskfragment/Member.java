package com.example.york.teamcraft.taskfragment;

/**
 * Created by York on 2017/10/7.
 */

public class Member {
    private String name;
    private String position;

    public Member(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
